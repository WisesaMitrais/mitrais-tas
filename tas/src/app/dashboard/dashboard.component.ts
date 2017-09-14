import { Component, OnInit, ViewChild } from '@angular/core';
import {DataSource} from '@angular/cdk/collections';
import {MdSort} from '@angular/material';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';

@Component({
  selector: 'tas-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  activeTrainingColumns = ['courseName', 'mainTrainer', 'backupTrainer', 'startDate', 'endDate', 'office'];
  exampleDatabase = new ExampleDatabase();
  dataSource: ExampleDataSource | null;

  bccScheduleColumns = ['trainer', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday'];
  exampleDatabase2 = new ExampleDatabase2();
  dataSource2: ExampleDataSource2 | null;

  @ViewChild(MdSort) sort: MdSort
  @ViewChild(MdSort) sort2: MdSort

  ngOnInit() {
    this.dataSource = new ExampleDataSource(this.exampleDatabase, this.sort);
    this.dataSource2 = new ExampleDataSource2(this.exampleDatabase2, this.sort2);
  }
}

export interface ActiveTrainingData {
  courseName: string;
  mainTrainer: string;
  backupTrainer: string;
  startDate: string;
  endDate: string;
  office: string;
}

export class ExampleDatabase {
  dataChange: BehaviorSubject<ActiveTrainingData[]> = new BehaviorSubject<ActiveTrainingData[]>([]);
  get data(): ActiveTrainingData[] { return this.dataChange.value; }

  constructor() {
    for (let i = 0; i < 10; i++) { this.addRow(); }
  }

  addRow() {
    const copiedData = this.data.slice();
    copiedData.push(this.createNewRow());
    this.dataChange.next(copiedData);
  }

  private createNewRow() {
    return {
      courseName: 'Business Writing 1 #1',
      mainTrainer: 'Facsi Pramujuwono',
      backupTrainer: 'Herdiawan Fajar',
      startDate: '07-08-2017',
      endDate: '07-09-2017',
      office: 'Bali'
    };
  }
}

export class ExampleDataSource extends DataSource<any> {
  constructor(private _exampleDatabase: ExampleDatabase, private _sort: MdSort) {
    super();
  }

  connect(): Observable<ActiveTrainingData[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      return this.getSortedData();
    });
  }

  disconnect() {}

  getSortedData(): ActiveTrainingData[] {
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


export interface BCCScheduleData {
  trainer: string;
  monday: string;
  tuesday: string;
  wednesday: string;
  thursday: string;
  friday: string;
}

export class ExampleDatabase2 {
  dataChange: BehaviorSubject<BCCScheduleData[]> = new BehaviorSubject<BCCScheduleData[]>([]);
  get data(): BCCScheduleData[] { return this.dataChange.value; }

  constructor() {
    for (let i = 0; i < 10; i++) { this.addRow(); }
  }

  addRow() {
    const copiedData = this.data.slice();
    copiedData.push(this.createNewRow());
    this.dataChange.next(copiedData);
  }

  private createNewRow() {
    return {
      trainer: 'Reny Mulyaningsih',
      monday: 'Communication Effectively 2 #2, Jakarta A',
      tuesday: 'Beginning #2, Bandung A',
      wednesday: 'Intermediate 1 #2, Bandung B',
      thursday: 'Presentation Skills 2 #3, Saraswasti',
      friday: 'Low Intermediate 2 #2, Kresna'
    };
  }
}

export class ExampleDataSource2 extends DataSource<any> {
  constructor(private _exampleDatabase: ExampleDatabase2, private _sort: MdSort) {
    super();
  }

  connect(): Observable<BCCScheduleData[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      return this.getSortedData();
    });
  }

  disconnect() {}

  getSortedData(): BCCScheduleData[] {
    const data = this._exampleDatabase.data.slice();
    if (!this._sort.active || this._sort.direction == '') { return data; }

    return data.sort((a, b) => {
      let propertyA: number|string = '';
      let propertyB: number|string = '';

      switch (this._sort.active) {
        case 'trainer': [propertyA, propertyB] = [a.trainer, b.trainer]; break;
        case 'monday': [propertyA, propertyB] = [a.monday, b.monday]; break;
        case 'tuesday': [propertyA, propertyB] = [a.tuesday, b.tuesday]; break;
        case 'wednesday': [propertyA, propertyB] = [a.wednesday, b.wednesday]; break;
        case 'thursday': [propertyA, propertyB] = [a.thursday, b.thursday]; break;
        case 'friday': [propertyA, propertyB] = [a.friday, b.friday]; break;
      }

      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}