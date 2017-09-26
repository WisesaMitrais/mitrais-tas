import { Component, Inject, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { CookieService } from 'angular2-cookie/core';

import { PeriodComponent } from './period.component';

import { AddPeriod } from '../services/period-add';
import { PeriodService } from '../services/period.service';
import { NotificationService } from '../services/notification.service';

@Component({
    selector: 'tas-period-add',
    templateUrl: 'period-add.component.html',
    styleUrls: ['./period-add.component.css']
})
export class AddPeriodComponent implements OnInit{
    finalData;  
    newPeriod: any = {};
    currentUserModel: any = {};
    result: boolean;

    constructor(
      public dialogRef: MdDialogRef<PeriodComponent>,
        private periodService: PeriodService,
        private cookieService: CookieService,
        private notificationService: NotificationService,
      @Inject(MD_DIALOG_DATA) public data: any) {
    }

    ngOnInit(){
        this.newPeriod.startDate = new Date();
        this.newPeriod.endDate = new Date();
        this.newPeriod.openEnrollment = false;
        this.newPeriod.bccTraining = false;
        if (this.cookieService.get('currentUser')){
            this.currentUserModel = JSON.parse(this.cookieService.get('currentUser'));
        }
    }

    createNewPeriod(){
        this.finalData = new AddPeriod(this.newPeriod.trainingName,
                                       this.newPeriod.startDate,
                                       this.newPeriod.endDate,
                                       this.newPeriod.openEnrollment,
                                       this.newPeriod.bccTraining,
                                       this.currentUserModel.id,
                                       this.currentUserModel.id,
                                       true);
        this.periodService.createDataPeriod(this.finalData).subscribe(((res) => {
            this.result = res;
            if(this.result == true){
                this.notificationService.setNotificationInfo('Period success to created');
            }else{
                this.notificationService.setNotificationError('Period failed to created !');
            }
        }));
        this.closeDialog();
        //window.location.reload();
    }

    closeDialog(){
        this.dialogRef.close();
    }
}