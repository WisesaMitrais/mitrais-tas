import { Component, Inject, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { UserComponent } from './user.component';

import { Office } from '../services/office'
import { AddUser } from '../services/user-add';
import { UserService } from '../services/user.service';
import { NotificationService } from '../services/notification.service';

@Component({
    selector: 'tas-user-add',
    templateUrl: 'user-add.component.html',
    styleUrls: ['./user-add.component.css']
})
export class AddUserComponent implements OnInit{
    finalData;
    office: Office;
    officeData: Office[];
    roles: Array<String> = [];  
    newUser: any = {};
    result: boolean;

    constructor(
      public dialogRef: MdDialogRef<UserComponent>,
        private userService: UserService,
        private notificationService: NotificationService,
      @Inject(MD_DIALOG_DATA) public data: any) {
    }

    ngOnInit(){
        this.userService.getOfficeData().subscribe(((officeData) => {
            this.officeData = officeData;
        }));
        this.newUser.isAdmin = false;
        this.newUser.isManager = false;
        this.newUser.isTrainer = false;
        this.newUser.isStaff = false;
    }

    createNewUser(){
        if(this.newUser.isAdmin === true) this.roles.push('Admin');
        if(this.newUser.isManager === true) this.roles.push('Manager');
        if(this.newUser.isTrainer === true) this.roles.push('Trainer');
        if(this.newUser.isStaff === true) this.roles.push('Staff');
        if(this.newUser.password === this.newUser.repeatPassword){
            this.finalData = new AddUser(this.newUser.fullName,
                                       this.newUser.username,
                                       this.newUser.password,
                                       this.newUser.email,
                                       this.newUser.jobFamilyStream,
                                       this.newUser.grade,
                                       this.office.idOffice,
                                       true,
                                       this.roles);
            this.userService.createNewUser(this.finalData).subscribe(((res) => {
                this.result = res;
                if(this.result == true){
                    this.notificationService.setNotificationInfo('User success to created');
                }else{
                    this.notificationService.setNotificationError('User failed to created !');
                }
            }));
            this.closeDialog();
            //window.location.reload();
        }else{
            this.notificationService.setNotificationWarning('Password not same, please check again !');
        }
    }

    closeDialog(){
        this.dialogRef.close();
    }
}