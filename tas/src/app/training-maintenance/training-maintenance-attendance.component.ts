import { Component, Inject, ViewChild, ElementRef, OnInit } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel } from '@angular/material';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { TrainingMaintenanceComponent } from './training-maintenance.component';

import { AchievementRepeatHistory } from '../services/achievement-repeat';
import { Achievement } from '../services/achievement';
import { AchievementService } from '../services/achievement.service';
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
    selector: 'tas-training-maintenance-attendance',
    templateUrl: 'training-maintenance-attendance.component.html',
    styleUrls: ['./training-maintenance-attendance.component.css']
})
export class TrainingMaintenanceAttendanceComponent implements OnInit {
    achievementSelected: Achievement;
    displayedColumns = ['trainingName', 'courseName', 'startDate', 'endDate', 'status'];
    repeatHistoryData: AchievementRepeatHistory[];
    exampleDatabase;
    dataSource: ExampleDataSource | null;
    
    @ViewChild(MdPaginator) paginator: MdPaginator;
    @ViewChild(MdSort) sort: MdSort;
    @ViewChild('filter') filter: ElementRef;
    
    constructor(private achievementService: AchievementService, 
        public dialog: MdDialog, 
        private notificationService: NotificationService,
        public dialogRef: MdDialogRef<TrainingMaintenanceComponent>,) {  
    }

    ngOnInit(){
        this.achievementService.getRepeatHistoryData(this.achievementSelected.idEmployee).subscribe(((repeatHistoryData) => {
            this.repeatHistoryData = repeatHistoryData;
            this.exampleDatabase = new ExampleDatabase(this.repeatHistoryData);
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
}
    
export class ExampleDatabase {
    dataChange: BehaviorSubject<AchievementRepeatHistory[]> = new BehaviorSubject<AchievementRepeatHistory[]>([]);
    get data(): AchievementRepeatHistory[] { 
    return this.dataChange.value; 
    }

    constructor(private dataRH: AchievementRepeatHistory[]) {
        for (let i = 0; i < dataRH.length; i++) { 
            const copiedData = this.data.slice();
            copiedData.push({
            trainingName: this.dataRH[i].trainingName,
            courseName: this.dataRH[i].courseName,
            startDate: this.dataRH[i].startDate,
            endDate: this.dataRH[i].endDate,
            status: this.dataRH[i].status
            });
            this.dataChange.next(copiedData);
        }
    }
}

export class ExampleDataSource extends DataSource<any> {
    _filterChange = new BehaviorSubject('');
    get filter(): string { return this._filterChange.value; }
    set filter(filter: string) { this._filterChange.next(filter); }

    filteredData: AchievementRepeatHistory[] = [];
    renderedData: AchievementRepeatHistory[] = [];

    constructor(private _exampleDatabase: ExampleDatabase,
                private _paginator: MdPaginator,
                private _sort: MdSort) {
    super();
    
    this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
    }

    connect(): Observable<AchievementRepeatHistory[]> {
    const displayDataChanges = [
        this._exampleDatabase.dataChange,
        this._sort.mdSortChange, 
        this._filterChange,
        this._paginator.page,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
        this.filteredData = this._exampleDatabase.data.slice().filter((item: AchievementRepeatHistory) => {
        let searchStr = (item.courseName).toLowerCase();
        return searchStr.indexOf(this.filter.toLowerCase()) != -1;
        });
        
        const sortedData = this.sortData(this.filteredData.slice());
        const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
        this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);
        return this.renderedData;
    });
    }

    disconnect() {}

    sortData(data: AchievementRepeatHistory[]): AchievementRepeatHistory[] {
    if (!this._sort.active || this._sort.direction == '') { return data; }
    
    return data.sort((a, b) => {
        let propertyA: number|string|boolean = '';
        let propertyB: number|string|boolean = '';
    
        switch (this._sort.active) {
        case 'trainingName': [propertyA, propertyB] = [a.trainingName, b.trainingName]; break;
        case 'courseName': [propertyA, propertyB] = [a.courseName, b.courseName]; break;
        case 'startDate': [propertyA, propertyB] = [a.startDate, b.startDate]; break;
        case 'endDate': [propertyA, propertyB] = [a.endDate, b.endDate]; break;
        case 'status': [propertyA, propertyB] = [a.status, b.status]; break;
        }
        
        let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
        let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

        return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
    }
}