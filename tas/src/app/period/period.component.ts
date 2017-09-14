import {Component, ElementRef, ViewChild} from '@angular/core';
import {DataSource} from '@angular/cdk/collections';
import {MdPaginator, MdSort, SelectionModel} from '@angular/material';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/debounceTime';


/* Temp popup create period */
import {Inject} from '@angular/core';
import {MdDialog, MdDialogRef, MD_DIALOG_DATA} from '@angular/material';
/**
 * @title Dialog Overview
 */
@Component({
  selector: 'tas-period-add',
  templateUrl: 'period-add.component.html',
  styleUrls: ['./period-add.component.css']
})
export class AddPeriodComponent {

  constructor(
    public dialogRef: MdDialogRef<PeriodComponent>,
    @Inject(MD_DIALOG_DATA) public data: any) { }

}

@Component({
  selector: 'tas-period',
  templateUrl: './period.component.html',
  styleUrls: ['./period.component.css']
})
export class PeriodComponent {
constructor(public dialog: MdDialog) {}

  openDialog(): void {
    let dialogRef = this.dialog.open(AddPeriodComponent, {
      width: '600px',
      height: '430px'
    });
  }

displayedColumns = ['userId', 'name', 'active', 'courses', 'startDate', 'endDate', 'createdBy', 'updateBy', 'action'];
  exampleDatabase = new ExampleDatabase();
  selection = new SelectionModel<string>(true, []);
  dataSource: ExampleDataSource | null;

  @ViewChild(MdPaginator) paginator: MdPaginator;
  @ViewChild(MdSort) sort: MdSort;
  @ViewChild('filter') filter: ElementRef;

  ngOnInit() {
    this.dataSource = new ExampleDataSource(this.exampleDatabase, this.paginator, this.sort);
    Observable.fromEvent(this.filter.nativeElement, 'keyup')
        .debounceTime(150)
        .distinctUntilChanged()
        .subscribe(() => {
          if (!this.dataSource) { return; }
          this.dataSource.filter = this.filter.nativeElement.value;
        });
  }

  isAllSelected(): boolean {
    if (!this.dataSource) { return false; }
    if (this.selection.isEmpty()) { return false; }

    if (this.filter.nativeElement.value) {
      return this.selection.selected.length == this.dataSource.renderedData.length;
    } else {
      return this.selection.selected.length == this.exampleDatabase.data.length;
    }
  }

  masterToggle() {
    if (!this.dataSource) { return; }

    if (this.isAllSelected()) {
      this.selection.clear();
    } else if (this.filter.nativeElement.value) {
      this.dataSource.renderedData.forEach(data => this.selection.select(data.id));
    } else {
      this.exampleDatabase.data.forEach(data => this.selection.select(data.id));
    }
  }
}

const NAMES = ['aaa', 'bbb', 'ccc', 'ddd', 'eee'];
const ACTIVES = ['yes', 'no'];
const COURSES = ['ffff', 'gggg', 'hhhh'];
const STARTDATES = ['01-January-2017', '02-March-2017'];
const ENDDATES = ['30-July-2017', '31-December-2017'];
const CREATEDBY = ['vv', 'xx'];
const UPDATEBY = ['yyyyy', 'zzzzz'];

export interface UserData {
  id: string;
  name: string;
  active: string;
  courses: string;
  startDate: string;
  endDate: string;
  createdBy: string;
  updateBy: string;
}

export class ExampleDatabase {
  dataChange: BehaviorSubject<UserData[]> = new BehaviorSubject<UserData[]>([]);
  get data(): UserData[] { return this.dataChange.value; }

  constructor() {
    for (let i = 0; i < 100; i++) { this.addUser(); }
  }

  addUser() {
    const copiedData = this.data.slice();
    copiedData.push(this.createNewUser());
    this.dataChange.next(copiedData);
  }

  private createNewUser() {
    return {
      id: (this.data.length + 1).toString(),
      name: NAMES[Math.round(Math.random() * (NAMES.length - 1))],
      active: ACTIVES[Math.round(Math.random() * (ACTIVES.length - 1))],
      courses: COURSES[Math.round(Math.random() * (COURSES.length - 1))],
      startDate: STARTDATES[Math.round(Math.random() * (STARTDATES.length - 1))],
      endDate: ENDDATES[Math.round(Math.random() * (ENDDATES.length - 1))],
      createdBy: CREATEDBY[Math.round(Math.random() * (CREATEDBY.length - 1))],
      updateBy: UPDATEBY[Math.round(Math.random() * (UPDATEBY.length - 1))]
    };
  }
}

export class ExampleDataSource extends DataSource<any> {
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) { this._filterChange.next(filter); }

  filteredData: UserData[] = [];
  renderedData: UserData[] = [];

  constructor(private _exampleDatabase: ExampleDatabase,
              private _paginator: MdPaginator,
              private _sort: MdSort) {
    super();
    
    this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
  }

  connect(): Observable<UserData[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange, 
      this._filterChange,
      this._paginator.page,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      this.filteredData = this._exampleDatabase.data.slice().filter((item: UserData) => {
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

  sortData(data: UserData[]): UserData[] {
    if (!this._sort.active || this._sort.direction == '') { return data; }

    return data.sort((a, b) => {
      let propertyA: number|string = '';
      let propertyB: number|string = '';

      switch (this._sort.active) {
        case 'userId': [propertyA, propertyB] = [a.id, b.id]; break;
        case 'name': [propertyA, propertyB] = [a.name, b.name]; break;
        case 'active': [propertyA, propertyB] = [a.active, b.active]; break;
        case 'courses': [propertyA, propertyB] = [a.courses, b.courses]; break;
        case 'startDate': [propertyA, propertyB] = [a.startDate, b.startDate]; break;
        case 'endDate': [propertyA, propertyB] = [a.endDate, b.endDate]; break;
        case 'createdBy': [propertyA, propertyB] = [a.createdBy, b.createdBy]; break;
        case 'updateBy': [propertyA, propertyB] = [a.updateBy, b.updateBy]; break;
      }

      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}