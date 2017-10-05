import { Component, ElementRef, ViewChild, Inject, OnInit } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel } from '@angular/material';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { TrainingMaintenanceComponent } from './training-maintenance.component';

import { SchedulePeriod } from '../services/period-schedule';
import { TrainingMaintenanceAssessment } from '../services/training-maintenance-assessment';
import { TrainingMaintenanceService } from '../services/training-maintenance.service';
import { NotificationService } from '../services/notification.service';
import { TrainingMaintenanceAssessmentUpdate } from '../services/training-maintenance-assessment-update';

import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/debounceTime';

@Component({
    selector: 'tas-training-maintenance-assessment',
    templateUrl: 'training-maintenance-assessment.component.html',
    styleUrls: ['./training-maintenance-assessment.component.css']
})
export class TrainingMaintenanceAssessmentComponent implements OnInit {
    finalData: TrainingMaintenanceAssessmentUpdate[] = [];
    result;
    scheduleSelected: SchedulePeriod;
    displayedColumns = ['number', 'name', 'result'];
    assessmentData: TrainingMaintenanceAssessment[] = [];
    listIDEnrollment: number[] = [];
    exampleDatabase;
    selection = new SelectionModel<string>(true, []);
    dataSource: ExampleDataSource | null;
    statusResult: string[] = [];
  
    @ViewChild(MdPaginator) paginator: MdPaginator;
    @ViewChild(MdSort) sort: MdSort;
    @ViewChild('filter') filter: ElementRef;
  
    constructor(private trainingMaintenanceService: TrainingMaintenanceService, 
        public dialog: MdDialog, 
        private notificationService: NotificationService,
        public dialogRef: MdDialogRef<TrainingMaintenanceComponent>) {
    }

    ngOnInit(){
        this.trainingMaintenanceService.getAllAssessmentData(this.scheduleSelected.idSchedule).subscribe(((assessmentData) => {
        this.assessmentData = assessmentData;
        this.statusResult.length = this.assessmentData.length;
        for(let i = 0; i < this.assessmentData.length; i++){
            this.statusResult[i] = this.assessmentData[i].status;
            this.listIDEnrollment[i] = parseInt(this.assessmentData[i].idEnrollment);
        }

        this.exampleDatabase = new ExampleDatabase(this.assessmentData);
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

    closeDialog(){
        this.dialogRef.close();
    }

    updateAssessmentData(){
        for(let i = 0; i < this.statusResult.length; i++){
            this.finalData[i] = new TrainingMaintenanceAssessmentUpdate(this.listIDEnrollment[i], this.statusResult[i]);
        }
        this.trainingMaintenanceService.updateAssessmentData(this.scheduleSelected.idSchedule, this.finalData).subscribe(((res) => {
        this.result = res;
        if(this.result == true){
            this.notificationService.setNotificationInfo('Assessment success to updated');
        }else{
            this.notificationService.setNotificationError('Assessment failed to updated !');
        }
        }));
        this.closeDialog();
    }
}

export class ExampleDatabase {
    dataChange: BehaviorSubject<TrainingMaintenanceAssessment[]> = new BehaviorSubject<TrainingMaintenanceAssessment[]>([]);
    get data(): TrainingMaintenanceAssessment[] { 
      return this.dataChange.value; 
    }
  
    constructor(private dataTMA: TrainingMaintenanceAssessment[]) {
      for (let i = 0; i < dataTMA.length; i++) { 
        const copiedData = this.data.slice();
        copiedData.push({
          number: this.dataTMA[i].number,
          idEnrollment: this.dataTMA[i].idEnrollment,
          name: this.dataTMA[i].name,
          status: this.dataTMA[i].status
        });
        this.dataChange.next(copiedData);
      }
    }
}

export class ExampleDataSource extends DataSource<any> {
    _filterChange = new BehaviorSubject('');
    get filter(): string { return this._filterChange.value; }
    set filter(filter: string) { this._filterChange.next(filter); }
  
    filteredData: TrainingMaintenanceAssessment[] = [];
    renderedData: TrainingMaintenanceAssessment[] = [];
  
    constructor(private _exampleDatabase: ExampleDatabase,
                private _paginator: MdPaginator,
                private _sort: MdSort) {
      super();
      
      this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
    }
    
    connect(): Observable<TrainingMaintenanceAssessment[]> {
      const displayDataChanges = [
        this._exampleDatabase.dataChange,
        this._sort.mdSortChange, 
        this._filterChange,
        this._paginator.page,
      ];
  
      return Observable.merge(...displayDataChanges).map(() => {
        this.filteredData = this._exampleDatabase.data.slice().filter((item: TrainingMaintenanceAssessment) => {
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
  
    sortData(data: TrainingMaintenanceAssessment[]): TrainingMaintenanceAssessment[] {
      if (!this._sort.active || this._sort.direction == '') { return data; }
  
      return data.sort((a, b) => {
        let propertyA: number|string = '';
        let propertyB: number|string = '';
  
        switch (this._sort.active) {
          case 'number':  [propertyA, propertyB] = [a.name, b.name]; break;
          case 'name':  [propertyA, propertyB] = [a.name, b.name]; break;
        }
  
        let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
        let valueB = isNaN(+propertyB) ? propertyB : +propertyB;
  
        return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
      });
    }
}