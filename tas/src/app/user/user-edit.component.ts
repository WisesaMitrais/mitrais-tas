import { Component, Inject, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { UserComponent } from './user.component';

import { Office } from '../services/office'
import { User } from '../services/user';
import { AddUser } from '../services/user-add';
import { UserService } from '../services/user.service';
import { NotificationService } from '../services/notification.service';

@Component({
    selector: 'tas-user-edit',
    templateUrl: 'user-edit.component.html',
    styleUrls: ['./user-edit.component.css']
})
export class EditUserComponent implements OnInit{
    userSelected: User;
    active: string;
    isActive: boolean;
    finalData;
    roles: Array<String> = [];  
    userData: any = {};
    result: boolean;

    constructor(
      public dialogRef: MdDialogRef<UserComponent>,
        private userService: UserService,
        private notificationService: NotificationService,
      @Inject(MD_DIALOG_DATA) public data: any) {
    }

    ngOnInit(){
        if(this.userSelected.active === true){
            this.active = 'active';
        }else{
            this.active = 'notActive';
        }
        if(this.AdminRoleCheck() >= 0) {
            this.userData.isAdmin = true;
        }else{
            this.userData.isAdmin = false;
        }
        if(this.ManagerRoleCheck() >= 0) {
            this.userData.isManager = true;
        }else{
            this.userData.isManager = false;
        }
        if(this.TrainerRoleCheck() >= 0) {
            this.userData.isTrainer = true;
        }else{
            this.userData.isTrainer = false;
        } 
        if(this.StaffRoleCheck() >= 0) {
            this.userData.isStaff = true;
        }else{
            this.userData.isStaff = false;
        }
    }

    AdminRoleCheck(): number{
        var text = this.userSelected.role, regex = /Admin/;
        return text.search(regex);
    }

    ManagerRoleCheck(): number{
        var text = this.userSelected.role, regex = /Manager/;
        return text.search(regex);
    }

    TrainerRoleCheck(): number{
        var text = this.userSelected.role, regex = /Trainer/;
        return text.search(regex);
    }

    StaffRoleCheck(): number{
        var text = this.userSelected.role, regex = /Staff/;
        return text.search(regex);
    }

    updateUser(){
        if(this.active === 'active'){
            this.isActive = true;
        }else{
            this.isActive = false;
        }
        if(this.userData.isAdmin === true) this.roles.push('Admin');
        if(this.userData.isManager === true) this.roles.push('Manager');
        if(this.userData.isTrainer === true) this.roles.push('Trainer');
        if(this.userData.isStaff === true) this.roles.push('Staff');
        this.finalData = new AddUser(null,
                null,
                null,
                null,
                null,
                null,
                null,
                this.isActive,
                this.roles);
    this.userService.updateUser(this.finalData, this.userSelected.idUser).subscribe(((res) => {
        this.result = res;
        if(this.result == true){
            this.notificationService.setNotificationInfo('User success to updated');
        }else{
            this.notificationService.setNotificationError('User failed to updated !');
        }
    }));
    this.closeDialog();
    //window.location.reload();
    }

    closeDialog(){
        this.dialogRef.close();
    }
}