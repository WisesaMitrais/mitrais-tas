import { Component, ElementRef, ViewChild, Inject, OnInit } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MdPaginator, MdSort, SelectionModel } from '@angular/material';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { PeriodEligibleParticipantComponent } from './period-eligibleparticipant.component';

import { AddUserEligibleParticipant } from '../services/period-eligibleparticipant-add';
import { User } from '../services/user';
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
    selector: 'tas-period-eligibleparticipant-add',
    templateUrl: 'period-eligibleparticipant-add.component.html',
    styleUrls: ['./period-eligibleparticipant-add.component.css']
})
export class PeriodEligibleParticipantAddComponent implements OnInit {
    finalData;
    result;
    idTrainingSelected: number;
    displayedColumns = ['action', 'name', 'jobFamilyStream', 'grade', 'email', 'accountName'];
    user: User[];
    exampleDatabase;
    dataSource: ExampleDataSource | null;
    selection = new SelectionModel<string>(true, []);
  
    @ViewChild(MdPaginator) paginator: MdPaginator;
    @ViewChild(MdSort) sort: MdSort;
    @ViewChild('filter') filter: ElementRef;
  
    constructor(private periodService: PeriodService, 
        public dialogRef: MdDialogRef<PeriodEligibleParticipantComponent>,
        private notificationService: NotificationService,
        @Inject(MD_DIALOG_DATA) public data: any) {
    }

    ngOnInit(){
        this.periodService.getUserEligibleParticipant(this.idTrainingSelected).subscribe(((user) => {
            this.user = user;
            this.exampleDatabase = new ExampleDatabase(this.user);
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
        this.dataSource.renderedData.forEach(data => this.selection.select(data.idUser));
        } else {
        this.exampleDatabase.data.forEach(data => this.selection.select(data.idUser));
        }
    }

    sendEligibleParticipant(){
        this.finalData = new AddUserEligibleParticipant(this.idTrainingSelected, this.selection.selected);
        this.periodService.AddEligibleParticipant(this.finalData).subscribe(((res) => {
            this.result = res;
            if(this.result == true){
                this.notificationService.setNotificationInfo('Eligible participant success to add');
            }else{
                this.notificationService.setNotificationError('Eligible participant failed to add !');
            }
        }));
        this.closeDialog();
    }
}

export class ExampleDatabase {
    dataChange: BehaviorSubject<User[]> = new BehaviorSubject<User[]>([]);
    get data(): User[] { 
        return this.dataChange.value; 
    }

    constructor(private dataUser: User[]) {
        for (let i = 0; i < dataUser.length; i++) { 
        const copiedData = this.data.slice();
        copiedData.push({
            idUser: this.dataUser[i].idUser,
            name: this.dataUser[i].name,
            email: this.dataUser[i].email,
            jobFamilyStream: this.dataUser[i].jobFamilyStream,
            accountName: this.dataUser[i].accountName,
            active: this.dataUser[i].active,
            role: this.dataUser[i].role,
            grade: this.dataUser[i].grade
        });
        this.dataChange.next(copiedData);
        }
    }
}

export class ExampleDataSource extends DataSource<any> {
    _filterChange = new BehaviorSubject('');
    get filter(): string { return this._filterChange.value; }
    set filter(filter: string) { this._filterChange.next(filter); }

    filteredData: User[] = [];
    renderedData: User[] = [];

    constructor(private _exampleDatabase: ExampleDatabase,
                private _paginator: MdPaginator,
                private _sort: MdSort) {
        super();
        
        this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
    }

    connect(): Observable<User[]> {
        const displayDataChanges = [
        this._exampleDatabase.dataChange,
        this._sort.mdSortChange, 
        this._filterChange,
        this._paginator.page,
        ];

        return Observable.merge(...displayDataChanges).map(() => {
        this.filteredData = this._exampleDatabase.data.slice().filter((item: User) => {
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

    sortData(data: User[]): User[] {
        if (!this._sort.active || this._sort.direction == '') { return data; }
        
        return data.sort((a, b) => {
        let propertyA: number|string|boolean = '';
        let propertyB: number|string|boolean = '';
        
        switch (this._sort.active) {
            case 'idUser': [propertyA, propertyB] = [a.idUser, b.idUser]; break;
            case 'name': [propertyA, propertyB] = [a.name, b.name]; break;
            case 'email': [propertyA, propertyB] = [a.email, b.email]; break;
            case 'jobFamilyStream': [propertyA, propertyB] = [a.jobFamilyStream, b.jobFamilyStream]; break;
            case 'accountName': [propertyA, propertyB] = [a.accountName, b.accountName]; break;
            case 'active': [propertyA, propertyB] = [a.active, b.active]; break;
            case 'role': [propertyA, propertyB] = [a.role, b.role]; break;
            case 'grade': [propertyA, propertyB] = [a.grade, b.grade]; break;
        }
        
        let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
        let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

        return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
        });
    }
}