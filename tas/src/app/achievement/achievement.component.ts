import { Component, ElementRef, ViewChild, Inject } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel } from '@angular/material';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { AchievementDetailComponent } from './achievement-detail.component';
import { AchievementRepeatComponent } from './achievement-repeat.component';

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
  selector: 'tas-achievement',
  templateUrl: './achievement.component.html',
  styleUrls: ['./achievement.component.css']
})
export class AchievementComponent {
  result;
  displayedColumns = ['employeeName', 'jobFamily', 'grade', 'office', 'b', 'li1', 'li2', 'int1', 'int2', 'bw1', 'ce1', 'bw2', 'ce2', 'ps2', 'action'];
  achievement: Achievement[];
  exampleDatabase;
  dataSource: ExampleDataSource | null;
  selection = new SelectionModel<string>(true, []);

  @ViewChild(MdPaginator) paginator: MdPaginator;
  @ViewChild(MdSort) sort: MdSort;
  @ViewChild('filter') filter: ElementRef;

  constructor(private achievementService: AchievementService, 
    public dialog: MdDialog, 
    private notificationService: NotificationService) {  
    this.achievementService.getAllAchievementData().subscribe(((achievement) => {
      this.achievement = achievement;
      this.exampleDatabase = new ExampleDatabase(this.achievement);
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

  openDetailDialog(achievement: Achievement): void{
    let dialogRef = this.dialog.open(AchievementDetailComponent, {
      width: '900px',
      height: '520px'
    });
    dialogRef.componentInstance.achievementSelected = achievement;
  }

  openRepeatHistoryDialog(achievement: Achievement): void{
    let dialogRef = this.dialog.open(AchievementRepeatComponent, {
      width: '900px',
      height: '588px'
    });
    dialogRef.componentInstance.achievementSelected = achievement;
  }

  ExportToExcel(){
    window.open('http://172.19.14.152:8080/achievement/download');
  }
}

export class ExampleDatabase {
  dataChange: BehaviorSubject<Achievement[]> = new BehaviorSubject<Achievement[]>([]);
  get data(): Achievement[] { 
    return this.dataChange.value; 
  }

  constructor(private dataAchievement: Achievement[]) {
    for (let i = 0; i < dataAchievement.length; i++) { 
      const copiedData = this.data.slice();
      copiedData.push({
        idEmployee: this.dataAchievement[i].idEmployee,
        employeeName: this.dataAchievement[i].employeeName,
        jobFamily: this.dataAchievement[i].jobFamily,
        grade: this.dataAchievement[i].grade,
        office: this.dataAchievement[i].office,
        beginning: this.dataAchievement[i].beginning,
        presentationSkill2: this.dataAchievement[i].presentationSkill2,
        li1: this.dataAchievement[i].li1,
        li2: this.dataAchievement[i].li2,
        bw1: this.dataAchievement[i].bw1,
        bw2: this.dataAchievement[i].bw2,
        int1: this.dataAchievement[i].int1,
        int2: this.dataAchievement[i].int2,
        ce1: this.dataAchievement[i].ce1,
        ce2: this.dataAchievement[i].ce2,
        beginningStatus: this.dataAchievement[i].beginningStatus,
        presentationSkill2Status: this.dataAchievement[i].presentationSkill2Status,
        li1Status: this.dataAchievement[i].li1Status,
        li2Status: this.dataAchievement[i].li2Status,
        bw1Status: this.dataAchievement[i].bw1Status,
        bw2Status: this.dataAchievement[i].bw2Status,
        int1Status: this.dataAchievement[i].int1Status,
        int2Status: this.dataAchievement[i].int2Status,
        ce1Status: this.dataAchievement[i].ce1Status,
        ce2Status: this.dataAchievement[i].ce2Status,
        _beginning: this.dataAchievement[i]._beginning,
        _presentationSkill2: this.dataAchievement[i]._presentationSkill2,
        _LI1: this.dataAchievement[i]._LI1,
        _LI2: this.dataAchievement[i]._LI2,
        _BW1: this.dataAchievement[i]._BW1,
        _BW2: this.dataAchievement[i]._BW2,
        _Int1: this.dataAchievement[i]._Int1,
        _Int2: this.dataAchievement[i]._Int2,
        _CE1: this.dataAchievement[i]._CE1,
        _CE2: this.dataAchievement[i]._CE2
      });
      this.dataChange.next(copiedData);
    }
  }
}

export class ExampleDataSource extends DataSource<any> {
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) { this._filterChange.next(filter); }

  filteredData: Achievement[] = [];
  renderedData: Achievement[] = [];

  constructor(private _exampleDatabase: ExampleDatabase,
              private _paginator: MdPaginator,
              private _sort: MdSort) {
    super();
    
    this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
  }

  connect(): Observable<Achievement[]> {
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.mdSortChange, 
      this._filterChange,
      this._paginator.page,
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      this.filteredData = this._exampleDatabase.data.slice().filter((item: Achievement) => {
        let searchStr = (item.employeeName).toLowerCase();
        return searchStr.indexOf(this.filter.toLowerCase()) != -1;
      });
      
      const sortedData = this.sortData(this.filteredData.slice());
      const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
      this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);
      return this.renderedData;
    });
  }

  disconnect() {}

  sortData(data: Achievement[]): Achievement[] {
    if (!this._sort.active || this._sort.direction == '') { return data; }
    
    return data.sort((a, b) => {
      let propertyA: number|string|boolean = '';
      let propertyB: number|string|boolean = '';
    
      switch (this._sort.active) {
        case 'employeeName': [propertyA, propertyB] = [a.employeeName, b.employeeName]; break;
        case 'jobFamily': [propertyA, propertyB] = [a.jobFamily, b.jobFamily]; break;
        case 'grade': [propertyA, propertyB] = [a.grade, b.grade]; break;
        case 'office': [propertyA, propertyB] = [a.office, b.office]; break;
        case 'b': [propertyA, propertyB] = [a.beginning, b.beginning]; break;
        case 'li1': [propertyA, propertyB] = [a.li1, b.li1]; break;
        case 'li2': [propertyA, propertyB] = [a.li2, b.li2]; break;
        case 'bw1': [propertyA, propertyB] = [a.bw1, b.bw1]; break;
        case 'bw2': [propertyA, propertyB] = [a.bw2, b.bw2]; break;
        case 'int1': [propertyA, propertyB] = [a.int1, b.int1]; break;
        case 'int2': [propertyA, propertyB] = [a.int2, b.int2]; break;
        case 'ce1': [propertyA, propertyB] = [a.ce1, b.ce1]; break;
        case 'ce2': [propertyA, propertyB] = [a.ce2, b.ce2]; break;
        case 'ps2': [propertyA, propertyB] = [a.presentationSkill2, b.presentationSkill2]; break;
      }
      
      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}