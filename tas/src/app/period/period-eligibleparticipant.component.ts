import { Component, ElementRef, ViewChild, Inject, Input } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel, MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { ActivatedRoute } from '@angular/router';

import { PeriodEligibleParticipantAddComponent } from './period-eligibleparticipant-add.component';

import { EligibleParticipantPeriod } from '../services/period-eligibleparticipant';
import { PeriodService } from '../services/period.service';

import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/debounceTime';

@Component({
  selector: 'tas-period-eligibleparticipant',
  templateUrl: './period-eligibleparticipant.component.html',
  styleUrls: ['./period-eligibleparticipant.component.css']
})
export class PeriodEligibleParticipantComponent {
  idTrainingChoosed: number;
  sub;
  displayedColumns = ['idUser', 'name', 'action'];  
  eligibleParticipant: EligibleParticipantPeriod[];
  exampleDatabase;
  selection = new SelectionModel<string>(true, []);
  dataSource: ExampleDataSource | null;
    
  @ViewChild(MdPaginator) paginator: MdPaginator;
  @ViewChild(MdSort) sort: MdSort;
  @ViewChild('filter') filter: ElementRef;

  constructor(private periodService: PeriodService, public dialog: MdDialog, private route: ActivatedRoute) {
    this.sub = this.route
      .queryParams
      .subscribe(params => {
        this.idTrainingChoosed = +params['id'];
    });

    this.periodService.getDataEligibleParticipant(this.idTrainingChoosed).subscribe(((eligibleParticipant) => {
    this.eligibleParticipant = eligibleParticipant;
    this.exampleDatabase = new ExampleDatabase(this.eligibleParticipant);
    this.paginator.length = this.exampleDatabase.data.length;
    this.paginator.pageSize = 10;
    this.paginator._pageIndex = 0; 
      
    this.dataSource = new ExampleDataSource(this.exampleDatabase, this.paginator, this.sort);
    Observable.fromEvent(this.filter.nativeElement, 'keyup')
    .debounceTime(150)
    .distinctUntilChanged()
    .subscribe(() => {
      if (!this.dataSource) { return; }
      this.dataSource.filter = this.filter.nativeElement.value;
      });
    }));
  }

  openDialog(): void {
    let dialogRef = this.dialog.open(PeriodEligibleParticipantAddComponent, {
      width: '1100px',
      height: '585px'
    });
    dialogRef.componentInstance.idTrainingSelected = this.idTrainingChoosed;
  }
}
    
  export class ExampleDatabase {
    dataChange: BehaviorSubject<EligibleParticipantPeriod[]> = new BehaviorSubject<EligibleParticipantPeriod[]>([]);
    get data(): EligibleParticipantPeriod[] { 
      return this.dataChange.value; 
    }
  
    constructor(private dataEP: EligibleParticipantPeriod[]) {
      for (let i = 0; i < dataEP.length; i++) { 
        const copiedData = this.data.slice();
        copiedData.push({
          idUser: this.dataEP[i].idUser,
          eligibleNumber: this.dataEP[i].eligibleNumber,
          name: this.dataEP[i].name
        });
        this.dataChange.next(copiedData);
      }
    }
  }
    
  export class ExampleDataSource extends DataSource<any> {
    _filterChange = new BehaviorSubject('');
    get filter(): string { return this._filterChange.value; }
    set filter(filter: string) { this._filterChange.next(filter); }
  
    filteredData: EligibleParticipantPeriod[] = [];
    renderedData: EligibleParticipantPeriod[] = [];
  
    constructor(private _exampleDatabase: ExampleDatabase,
                private _paginator: MdPaginator,
                private _sort: MdSort) {
      super();
      
      this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
    }
    
    connect(): Observable<EligibleParticipantPeriod[]> {
      const displayDataChanges = [
        this._exampleDatabase.dataChange,
        this._sort.mdSortChange, 
        this._filterChange,
        this._paginator.page,
      ];
  
      return Observable.merge(...displayDataChanges).map(() => {
        this.filteredData = this._exampleDatabase.data.slice().filter((item: EligibleParticipantPeriod) => {
          let searchStr = (item.name).toLowerCase();
          return searchStr.indexOf(this.filter.toLowerCase()) != -1;
        });
  
        const sortedData = this.sortData(this.filteredData.slice());
  
        const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
        this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);
        return this.renderedData;
      });
    }
    
    disconnect() {}
  
    sortData(data: EligibleParticipantPeriod[]): EligibleParticipantPeriod[] {
      if (!this._sort.active || this._sort.direction == '') { return data; }
  
      return data.sort((a, b) => {
        let propertyA: number|string = '';
        let propertyB: number|string = '';
  
        switch (this._sort.active) {
          case 'idUser': [propertyA, propertyB] = [a.idUser, b.idUser]; break;
          case 'name':  [propertyA, propertyB] = [a.name, b.name]; break;
        }
  
        let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
        let valueB = isNaN(+propertyB) ? propertyB : +propertyB;
  
        return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
      });
    }
  }