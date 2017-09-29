import { Component, ElementRef, ViewChild, Inject, Input, OnInit } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel, MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { PeriodScheduleComponent } from './period-schedule.component';

import { ShowEnrollParticipant } from '../services/period-enrollparticipant-show';
import { NotificationService } from '../services/notification.service';
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
  selector: 'tas-period-schedule-enrollparticipant-show',
  templateUrl: './period-schedule-enrollparticipant-show.component.html',
  styleUrls: ['./period-schedule-enrollparticipant-show.component.css']
})
export class PeriodEnrollParticipantShowComponent implements OnInit{
  result;
  idScheduleSelected: number;
  nameScheduleSelected: string;
  displayedColumns = ['idUser', 'name', 'action'];  
  enrollParticipant: ShowEnrollParticipant[];
  exampleDatabase;
  selection = new SelectionModel<string>(true, []);
  dataSource: ExampleDataSource | null;
    
  @ViewChild(MdPaginator) paginator: MdPaginator;
  @ViewChild(MdSort) sort: MdSort;
  @ViewChild('filter') filter: ElementRef;

  constructor(private periodService: PeriodService,
    public dialogRef: MdDialogRef<PeriodScheduleComponent>,
    private notificationService: NotificationService) {
  }

  ngOnInit(){
    this.periodService.getEnrollParticipantData(this.idScheduleSelected).subscribe(((enrollParticipant) => {
        this.enrollParticipant = enrollParticipant;
        this.exampleDatabase = new ExampleDatabase(this.enrollParticipant);
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

  deleteEnrollParticipant(enrollParticipantData: ShowEnrollParticipant){
    this.periodService.deleteEnrollParticipant(enrollParticipantData.idEnrollment).subscribe(((res) => {
      this.result = res;
      if(this.result == true){
          this.notificationService.setNotificationInfo('Success to deleted');
      }else{
          this.notificationService.setNotificationError('Failed to deleted !');
      }
    }));
    this.closeDialog();
    //window.location.reload();
  }

  closeDialog(){
    this.dialogRef.close();
  }
}
    
export class ExampleDatabase {
  dataChange: BehaviorSubject<ShowEnrollParticipant[]> = new BehaviorSubject<ShowEnrollParticipant[]>([]);
  get data(): ShowEnrollParticipant[] { 
    return this.dataChange.value; 
  }

  constructor(private dataEP: ShowEnrollParticipant[]) {
    for (let i = 0; i < dataEP.length; i++) { 
      const copiedData = this.data.slice();
      copiedData.push({
        idEnrollment: this.dataEP[i].idEnrollment,
        periodName: this.dataEP[i].periodName,
        courseName: this.dataEP[i].courseName,
        trainer: this.dataEP[i].trainer,
        startTime: this.dataEP[i].startTime,
        endTime: this.dataEP[i].endTime,
        status: this.dataEP[i].status,
        userName: this.dataEP[i].userName,
        userNumber: this.dataEP[i].userNumber
      });
      this.dataChange.next(copiedData);
    }
  }
}
    
export class ExampleDataSource extends DataSource<any> {
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) { this._filterChange.next(filter); }

  filteredData: ShowEnrollParticipant[] = [];
  renderedData: ShowEnrollParticipant[] = [];

  constructor(private _exampleDatabase: ExampleDatabase,
              private _paginator: MdPaginator,
              private _sort: MdSort) {
    super();
    
    this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
  }
  
  connect(): Observable<ShowEnrollParticipant[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange, 
      this._filterChange,
      this._paginator.page,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      this.filteredData = this._exampleDatabase.data.slice().filter((item: ShowEnrollParticipant) => {
        let searchStr = (item.userName).toLowerCase();
        return searchStr.indexOf(this.filter.toLowerCase()) != -1;
      });

      const sortedData = this.sortData(this.filteredData.slice());

      const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
      this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);
      return this.renderedData;
    });
  }
  
  disconnect() {}

  sortData(data: ShowEnrollParticipant[]): ShowEnrollParticipant[] {
    if (!this._sort.active || this._sort.direction == '') { return data; }

    return data.sort((a, b) => {
      let propertyA: number|string = '';
      let propertyB: number|string = '';

      switch (this._sort.active) {
        case 'idUser': [propertyA, propertyB] = [a.userNumber, b.userNumber]; break;
        case 'name':  [propertyA, propertyB] = [a.userName, b.userName]; break;
      }

      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}