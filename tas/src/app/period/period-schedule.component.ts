import { Component, ElementRef, ViewChild, Inject } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel, MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { ActivatedRoute } from '@angular/router';

import { PeriodScheduleAddComponent } from './period-schedule-add.component';
import { PeriodScheduleDetailsComponent } from './period-schedule-details.component';
import { PeriodEnrollParticipantComponent } from './period-schedule-enrollparticipant.component';

import { SchedulePeriod } from '../services/period-schedule';
import { PeriodService } from '../services/period.service';
import { NotificationService } from '../services/notification.service';

import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/debounceTime';

@Component({
  selector: 'tas-period-schedule',
  templateUrl: './period-schedule.component.html',
  styleUrls: ['./period-schedule.component.css']
})
export class PeriodScheduleComponent {
  idTrainingChoosed;
  nameTrainingChoosed;
  bccTrainingChoosed;
  startTrainingChoosed;
  endTrainingChoosed;
  sub;
  displayedColumns = ['name', 'mainTrainer', 'backupTrainer', 'classroom', 'scheduleType', 'day', 'startTime', 'endTime', 'capacity', 'allParticipantList', 'action'];
  schedule: SchedulePeriod[];
  exampleDatabase;
  dataSource: ExampleDataSource | null;
  selection = new SelectionModel<string>(true, []);
  
  @ViewChild(MdPaginator) paginator: MdPaginator;
  @ViewChild(MdSort) sort: MdSort;
  @ViewChild('filter') filter: ElementRef;
  
  constructor(private periodService: PeriodService, 
    public dialog: MdDialog, 
    private route: ActivatedRoute,
    private notificationService: NotificationService) {
    this.sub = this.route
      .queryParams
      .subscribe(params => {
        this.idTrainingChoosed = params['id2'];
        this.nameTrainingChoosed = params['name2'];
        this.bccTrainingChoosed = params['bcc2'];
        this.startTrainingChoosed = params['start2'];
        this.endTrainingChoosed = params['end2'];
    });

    this.periodService.getDataSchedule(this.idTrainingChoosed).subscribe(((schedule) => {
    this.schedule = schedule;
    this.exampleDatabase = new ExampleDatabase(this.schedule);
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

  openDialog(){
    let dialogRef = this.dialog.open(PeriodScheduleAddComponent, {
      width: '520px',
      height: '585px'
    });
    dialogRef.componentInstance.idTrainingSelected = this.idTrainingChoosed;
    dialogRef.componentInstance.bccTrainingSelected = this.bccTrainingChoosed;
    dialogRef.componentInstance.startTrainingSelected = this.startTrainingChoosed;
    dialogRef.componentInstance.endTrainingSelected = this.endTrainingChoosed;
  }

  openDetailsDialog(schedule: SchedulePeriod){
    let dialogRef = this.dialog.open(PeriodScheduleDetailsComponent, {
      width: '700px',
      height: '600px'
    });
    dialogRef.componentInstance.scheduleSelected = schedule;
  }

  openEnrollParticipantDialog(schedule: SchedulePeriod){
    let dialogRef = this.dialog.open(PeriodEnrollParticipantComponent, {
      width: '800px',
      height: '585px'
    });
    dialogRef.componentInstance.idTrainingSelected = +schedule.idSchedule;
  }
}
    
export class ExampleDatabase {
  dataChange: BehaviorSubject<SchedulePeriod[]> = new BehaviorSubject<SchedulePeriod[]>([]);
  get data(): SchedulePeriod[] { 
    return this.dataChange.value; 
  }

  constructor(private dataS: SchedulePeriod[]) {
    for (let i = 0; i < dataS.length; i++) { 
      const copiedData = this.data.slice();
      copiedData.push({
        idSchedule: this.dataS[i].idSchedule,
        name: this.dataS[i].name,
        mainTrainer: this.dataS[i].mainTrainer,
        backupTrainer: this.dataS[i].backupTrainer,
        room: this.dataS[i].room,
        day: this.dataS[i].day,
        startTime: this.dataS[i].startTime,
        endTime: this.dataS[i].endTime,
        capacity: this.dataS[i].capacity,
        participantNumber: this.dataS[i].participantNumber,
        scheduleType: this.dataS[i].scheduleType,
        periodic: this.dataS[i].periodic,
        createdBy: this.dataS[i].createdBy,
        createdAt: this.dataS[i].createdAt,
        updatedBy: this.dataS[i].updatedBy,
        updatedAt: this.dataS[i].updatedAt,
        city: this.dataS[i].city
      });
      this.dataChange.next(copiedData);
    }
  }
}
    
export class ExampleDataSource extends DataSource<any> {
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) { this._filterChange.next(filter); }

  filteredData: SchedulePeriod[] = [];
  renderedData: SchedulePeriod[] = [];

  constructor(private _exampleDatabase: ExampleDatabase,
              private _paginator: MdPaginator,
              private _sort: MdSort) {
    super();
    
    this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
  }

  connect(): Observable<SchedulePeriod[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange, 
      this._filterChange,
      this._paginator.page,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      this.filteredData = this._exampleDatabase.data.slice().filter((item: SchedulePeriod) => {
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

  sortData(data: SchedulePeriod[]): SchedulePeriod[] {
    if (!this._sort.active || this._sort.direction == '') { return data; }

    return data.sort((a, b) => {
      let propertyA: number|string = '';
      let propertyB: number|string = '';

      switch (this._sort.active) {
        case 'name': [propertyA, propertyB] = [a.name, b.name]; break;
        case 'mainTrainer': [propertyA, propertyB] = [a.mainTrainer, b.mainTrainer]; break;
        case 'backupTrainer': [propertyA, propertyB] = [a.backupTrainer, b.backupTrainer]; break;
        case 'classroom': [propertyA, propertyB] = [a.room, b.room]; break;
        case 'scheduleType': [propertyA, propertyB] = [a.scheduleType, b.scheduleType]; break;
        case 'day': [propertyA, propertyB] = [a.day, b.day]; break;
        case 'startTime': [propertyA, propertyB] = [a.startTime, b.startTime]; break;
        case 'endTime': [propertyA, propertyB] = [a.endTime, b.endTime]; break;
        case 'capacity': [propertyA, propertyB] = [a.capacity, b.capacity]; break;
        case 'allParticipantList': [propertyA, propertyB] = [a.participantNumber, b.participantNumber]; break;
      }

      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}