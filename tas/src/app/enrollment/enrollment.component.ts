import { Component, ElementRef, ViewChild, Inject } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel } from '@angular/material';
import { CookieService } from 'angular2-cookie/core';

import { Enrollment } from '../services/enrollment';
import { EnrollmentService } from '../services/enrollment.service';

import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/debounceTime';

@Component({
  selector: 'tas-enrollment',
  templateUrl: './enrollment.component.html',
  styleUrls: ['./enrollment.component.css']
})
export class EnrollementComponent {
  currentUserModel: any = {};
  displayedColumns = ['periodName', 'courseName', 'trainer', 'startAt', 'endAt', 'status'];
  enrollment: Enrollment[];
  exampleDatabase;
  dataSource: ExampleDataSource | null;
  selection = new SelectionModel<string>(true, []);

  @ViewChild(MdPaginator) paginator: MdPaginator;
  @ViewChild(MdSort) sort: MdSort;
  @ViewChild('filter') filter: ElementRef;

  constructor(private enrollmentService: EnrollmentService, public cookieService: CookieService) {  
    if (this.cookieService.get('currentUser')){
        this.currentUserModel = JSON.parse(this.cookieService.get('currentUser'));
    }
    this.enrollmentService.getEnrollmentData(this.currentUserModel.id).subscribe(((enrollment) => {
      this.enrollment = enrollment;
      this.exampleDatabase = new ExampleDatabase(this.enrollment);
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
}

export class ExampleDatabase {
  dataChange: BehaviorSubject<Enrollment[]> = new BehaviorSubject<Enrollment[]>([]);
  get data(): Enrollment[] { 
    return this.dataChange.value; 
  }

  constructor(private dataEnrollment: Enrollment[]) {
    for (let i = 0; i < dataEnrollment.length; i++) { 
      const copiedData = this.data.slice();
      copiedData.push({
        idEnrollment: this.dataEnrollment[i].idEnrollment,
        periodName: this.dataEnrollment[i].periodName,
        courseName: this.dataEnrollment[i].courseName,
        trainer: this.dataEnrollment[i].trainer,
        startTime: this.dataEnrollment[i].startTime,
        endTime: this.dataEnrollment[i].endTime,
        status: this.dataEnrollment[i].status,
        userName: this.dataEnrollment[i].userName,
        userNumber: this.dataEnrollment[i].userNumber
      });
      this.dataChange.next(copiedData);
    }
  }
}

export class ExampleDataSource extends DataSource<any> {
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) { this._filterChange.next(filter); }

  filteredData: Enrollment[] = [];
  renderedData: Enrollment[] = [];

  constructor(private _exampleDatabase: ExampleDatabase,
              private _paginator: MdPaginator,
              private _sort: MdSort) {
    super();
    
    this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
  }

  connect(): Observable<Enrollment[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange, 
      this._filterChange,
      this._paginator.page,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      this.filteredData = this._exampleDatabase.data.slice().filter((item: Enrollment) => {
        let searchStr = (item.periodName).toLowerCase();
        return searchStr.indexOf(this.filter.toLowerCase()) != -1;
      });
      
      const sortedData = this.sortData(this.filteredData.slice());
      const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
      this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);
      return this.renderedData;
    });
  }

  disconnect() {}

  sortData(data: Enrollment[]): Enrollment[] {
    if (!this._sort.active || this._sort.direction == '') { return data; }
    
    return data.sort((a, b) => {
      let propertyA: number|string|boolean = '';
      let propertyB: number|string|boolean = '';

      switch (this._sort.active) {
        case 'periodName': [propertyA, propertyB] = [a.periodName, b.periodName]; break;
        case 'courseName': [propertyA, propertyB] = [a.courseName, b.courseName]; break;
        case 'trainer': [propertyA, propertyB] = [a.trainer, b.trainer]; break;
        case 'startAt': [propertyA, propertyB] = [a.startTime, b.startTime]; break;
        case 'endAt': [propertyA, propertyB] = [a.endTime, b.endTime]; break;
        case 'status': [propertyA, propertyB] = [a.status, b.status]; break;
      }
      
      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}