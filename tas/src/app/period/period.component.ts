import { Component, ElementRef, ViewChild, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel, MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { AddPeriodComponent } from './period-add.component';

import { Period } from '../services/period';
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
  selector: 'tas-period',
  templateUrl: './period.component.html',
  styleUrls: ['./period.component.css']
})
export class PeriodComponent {
  displayedColumns = ['idTraining', 'name', 'active', 'courses', 'startDate', 'endDate', 'createdBy', 'updatedBy', 'action'];
  period: Period[];
  exampleDatabase;
  dataSource: ExampleDataSource | null;
  selection = new SelectionModel<string>(true, []);

  @ViewChild(MdPaginator) paginator: MdPaginator;
  @ViewChild(MdSort) sort: MdSort;
  @ViewChild('filter') filter: ElementRef;

  constructor(private periodService: PeriodService, public dialog: MdDialog, private router: Router) {  
      this.periodService.getDataPeriod().subscribe(((period) => {
      this.period = period;
      this.exampleDatabase = new ExampleDatabase(this.period);
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
    let dialogRef = this.dialog.open(AddPeriodComponent, {
      width: '600px',
      height: '430px'
    });
  }

  toEligibleParticipant(id: number, name: string){
    //console.log(name);
    window.location.reload();
    this.router.navigate(['home/period/eligible-participant'], { queryParams: { id: id } });
  }

  toScheduleList(){
    this.router.navigate(['home/period/schedule-list']);
  }
}

export class ExampleDatabase {
  dataChange: BehaviorSubject<Period[]> = new BehaviorSubject<Period[]>([]);
  get data(): Period[] { 
    return this.dataChange.value; 
  }

  constructor(private dataPeriod: Period[]) {
    for (let i = 0; i < dataPeriod.length; i++) { 
      const copiedData = this.data.slice();
      copiedData.push({
        idTraining: this.dataPeriod[i].idTraining,
        name: this.dataPeriod[i].name,
        active: this.dataPeriod[i].active,
        startDate: this.dataPeriod[i].startDate,
        endDate: this.dataPeriod[i].endDate,
        createdBy: this.dataPeriod[i].createdBy,
        updatedBy: this.dataPeriod[i].updatedBy,
        courses: this.dataPeriod[i].courses
      });
      this.dataChange.next(copiedData);
    }
  }
}

export class ExampleDataSource extends DataSource<any> {
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) { this._filterChange.next(filter); }

  filteredData: Period[] = [];
  renderedData: Period[] = [];

  constructor(private _exampleDatabase: ExampleDatabase,
              private _paginator: MdPaginator,
              private _sort: MdSort) {
    super();
  
    this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
  }

  connect(): Observable<Period[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange, 
      this._filterChange,
      this._paginator.page,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      this.filteredData = this._exampleDatabase.data.slice().filter((item: Period) => {
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

  sortData(data: Period[]): Period[] {
    if (!this._sort.active || this._sort.direction == '') { return data; }

    return data.sort((a, b) => {
      let propertyA: number|string|boolean = '';
      let propertyB: number|string|boolean = '';

      switch (this._sort.active) {
        case 'idTraining': [propertyA, propertyB] = [a.idTraining, b.idTraining]; break;
        case 'name': [propertyA, propertyB] = [a.name, b.name]; break;
        case 'active': [propertyA, propertyB] = [a.active, b.active]; break;
        case 'startDate': [propertyA, propertyB] = [a.startDate, b.startDate]; break;
        case 'endDate': [propertyA, propertyB] = [a.endDate, b.endDate]; break;
        case 'createdBy': [propertyA, propertyB] = [a.createdBy, b.createdBy]; break;
        case 'updateBy': [propertyA, propertyB] = [a.updatedBy, b.updatedBy]; break;
        case 'courses': [propertyA, propertyB] = [a.courses, b.courses]; break;
      }

      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}