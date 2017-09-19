import { Component, ViewChild } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdSort } from '@angular/material';

import { DashboardAT } from '../services/dashboard-at';
import { DashboardBCCS } from '../services/dashboard-bccs';
import { DashboardService } from '../services/dashboard.service';

import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';

@Component({
  selector: 'tas-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  activeTrainingColumns = ['courseName', 'mainTrainer', 'backupTrainer', 'startDate', 'endDate', 'office'];
  dashboardAT: DashboardAT[];
  exampleDatabase;
  dataSource: ExampleDataSource | null;
  totalDataAT: number;

  bccScheduleColumns = ['trainer', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday'];
  dashboardBCCS: DashboardBCCS[];
  exampleDatabase2;
  dataSource2: ExampleDataSource2 | null;
  totalDataBCCS: number;

  constructor(private dashboardService: DashboardService) { 
    this.dashboardService.getDataAT().subscribe(((dashboardAT) => {
      this.dashboardAT = dashboardAT;
      this.totalDataAT = this.dashboardAT.length;
      this.exampleDatabase = new ExampleDatabase(this.dashboardAT); 
      this.dataSource = new ExampleDataSource(this.exampleDatabase, this.sort);
    }));

    this.dashboardService.getDataBCCS().subscribe(((dashboardBCCS) => {
      this.dashboardBCCS = dashboardBCCS;
      this.totalDataBCCS = this.dashboardBCCS.length;
      this.exampleDatabase2 = new ExampleDatabase2(this.dashboardBCCS);
      this.dataSource2 = new ExampleDataSource2(this.exampleDatabase2, this.sort2);
    }));
  }

  @ViewChild(MdSort) sort: MdSort
  @ViewChild(MdSort) sort2: MdSort
}

export class ExampleDatabase {
  dataChange: BehaviorSubject<DashboardAT[]> = new BehaviorSubject<DashboardAT[]>([]);
  get data(): DashboardAT[] { 
    return this.dataChange.value; 
  }

  constructor(private dataAT: DashboardAT[]) {
    for (let i = 0; i < dataAT.length; i++) { 
      const copiedData = this.data.slice();
      copiedData.push({
        courseName: this.dataAT[i].courseName,
        mainTrainer: this.dataAT[i].mainTrainer,
        backupTrainer: this.dataAT[i].backupTrainer,
        startDate: this.dataAT[i].startDate,
        endDate: this.dataAT[i].endDate,
        office: this.dataAT[i].office
      });
      this.dataChange.next(copiedData);
    }
  }
}

export class ExampleDataSource extends DataSource<any> {
  constructor(private _exampleDatabase: ExampleDatabase, private _sort: MdSort) {
    super();
  }

  connect(): Observable<DashboardAT[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      return this.getSortedData();
    });
  }

  disconnect() {}

  getSortedData(): DashboardAT[] {
    const data = this._exampleDatabase.data.slice();
    if (!this._sort.active || this._sort.direction == '') { return data; }

    return data.sort((a, b) => {
      let propertyA: number|string = '';
      let propertyB: number|string = '';

      switch (this._sort.active) {
        case 'courseName': [propertyA, propertyB] = [a.courseName, b.courseName]; break;
        case 'mainTrainer': [propertyA, propertyB] = [a.mainTrainer, b.mainTrainer]; break;
        case 'backupTrainer': [propertyA, propertyB] = [a.backupTrainer, b.backupTrainer]; break;
        case 'startDate': [propertyA, propertyB] = [a.startDate, b.startDate]; break;
        case 'endDate': [propertyA, propertyB] = [a.endDate, b.endDate]; break;
        case 'office': [propertyA, propertyB] = [a.office, b.office]; break;
      }

      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}


export class ExampleDatabase2 {
  dataChange: BehaviorSubject<DashboardBCCS[]> = new BehaviorSubject<DashboardBCCS[]>([]);
  get data(): DashboardBCCS[] { 
    return this.dataChange.value; 
  }

  constructor(private dataBCCS: DashboardBCCS[]) {
    for (let i = 0; i < dataBCCS.length; i++) { 
      const copiedData = this.data.slice();
      copiedData.push({
        trainer: this.dataBCCS[i].trainer,
        mon: this.dataBCCS[i].mon,
        tue: this.dataBCCS[i].tue,
        wed: this.dataBCCS[i].wed,
        thu: this.dataBCCS[i].thu,
        fri: this.dataBCCS[i].fri
      });
      this.dataChange.next(copiedData);
    }
  }
}

export class ExampleDataSource2 extends DataSource<any> {
  constructor(private _exampleDatabase: ExampleDatabase2, private _sort: MdSort) {
    super();
  }

  connect(): Observable<DashboardBCCS[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      return this.getSortedData();
    });
  }

  disconnect() {}

  getSortedData(): DashboardBCCS[] {
    const data = this._exampleDatabase.data.slice();
    if (!this._sort.active || this._sort.direction == '') { return data; }

    return data.sort((a, b) => {
      let propertyA: number|string = '';
      let propertyB: number|string = '';

      switch (this._sort.active) {
        case 'trainer': [propertyA, propertyB] = [a.trainer, b.trainer]; break;
        case 'monday': [propertyA, propertyB] = [a.mon, b.mon]; break;
        case 'tuesday': [propertyA, propertyB] = [a.tue, b.tue]; break;
        case 'wednesday': [propertyA, propertyB] = [a.wed, b.wed]; break;
        case 'thursday': [propertyA, propertyB] = [a.thu, b.thu]; break;
        case 'friday': [propertyA, propertyB] = [a.fri, b.fri]; break;
      }

      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}