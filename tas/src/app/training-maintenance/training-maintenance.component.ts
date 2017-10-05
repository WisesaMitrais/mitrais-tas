import { Component, ElementRef, ViewChild, Inject } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel } from '@angular/material';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { CookieService } from 'angular2-cookie/core';

import { TrainingMaintenanceAssessmentComponent } from './training-maintenance-assessment.component';
import { TrainingMaintenanceAttendanceComponent } from './training-maintenance-attendance.component';

import { SchedulePeriod } from '../services/period-schedule';
import { TrainingMaintenanceService } from '../services/training-maintenance.service';
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
  selector: 'tas-training-maintenance',
  templateUrl: './training-maintenance.component.html',
  styleUrls: ['./training-maintenance.component.css']
})
export class TrainingMaintenanceComponent {
    userRole;

    idCurrentUser;
    displayedColumns = ['name', 'mainTrainer', 'backupTrainer', 'classroom', 'scheduleType', 'day', 'startTime', 'endTime', 'capacity', 'allParticipantList', 'action'];
    schedule: SchedulePeriod[];
    exampleDatabase;
    dataSource: ExampleDataSource | null;
    selection = new SelectionModel<string>(true, []);
    
    @ViewChild(MdPaginator) paginator: MdPaginator;
    @ViewChild(MdSort) sort: MdSort;
    @ViewChild('filter') filter: ElementRef;
    
    constructor(private trainingMaintenanceService: TrainingMaintenanceService, 
      public dialog: MdDialog,
      private cookieService: CookieService, 
      private notificationService: NotificationService) {
      this.userRole = JSON.parse(this.cookieService.get('currentUser'));
      this.idCurrentUser = this.userRole.id;
      this.trainingMaintenanceService.getTrainingMaintenanceData(this.idCurrentUser).subscribe(((schedule) => {
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
  
    openAttendanceDialog(schedule: SchedulePeriod){
      let dialogRef = this.dialog.open(TrainingMaintenanceAttendanceComponent, {
        width: '1000px',
        height: '640px'
      });
      // dialogRef.componentInstance.scheduleSelected = schedule;
    }
  
    openAssessmentDialog(schedule: SchedulePeriod){
      let dialogRef = this.dialog.open(TrainingMaintenanceAssessmentComponent, {
        width: '800px',
        height: '540px'
      });
      dialogRef.componentInstance.scheduleSelected = schedule;
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
          hour: this.dataS[i].hour,
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
          city: this.dataS[i].city,
          _startDate: this.dataS[i]._startDate,
          _endDate: this.dataS[i]._endDate,
          _Course: this.dataS[i]._Course,
          _Room: this.dataS[i]._Room,
          _Training: this.dataS[i]._Training,
          _MainTrainer: this.dataS[i]._MainTrainer,
          _BackupTrainer: this.dataS[i]._BackupTrainer
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