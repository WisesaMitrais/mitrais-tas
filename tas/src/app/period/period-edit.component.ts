import { Component, Inject, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { CookieService } from 'angular2-cookie/core';

import { Period } from '../services/period';
import { PeriodComponent } from './period.component';

import { AddPeriod } from '../services/period-add';
import { PeriodService } from '../services/period.service';
import { NotificationService } from '../services/notification.service';

@Component({
    selector: 'tas-period-edit',
    templateUrl: 'period-edit.component.html',
    styleUrls: ['./period-edit.component.css']
})
export class EditPeriodComponent implements OnInit{
    periodSelected: Period;
    finalData;
    updatePeriodData: any = {};
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
        this.updatePeriodData.trainingName = this.periodSelected.name;
        this.updatePeriodData.startDate = new Date(this.periodSelected._startDate);
        this.updatePeriodData.endDate = new Date(this.periodSelected._endDate);
        this.updatePeriodData.openEnrollment = this.periodSelected.openEnrollment;
        this.updatePeriodData.bccTraining = this.periodSelected.bccTraining;
        if (this.cookieService.get('currentUser')){
            this.currentUserModel = JSON.parse(this.cookieService.get('currentUser'));
        }
    }

    updatePeriod(){
        console.log(this.periodSelected.idTraining);
        this.finalData = new AddPeriod(this.updatePeriodData.trainingName,
                                       this.updatePeriodData.startDate,
                                       this.updatePeriodData.endDate,
                                       this.updatePeriodData.openEnrollment,
                                       this.updatePeriodData.bccTraining,
                                       this.currentUserModel.id,
                                       this.currentUserModel.id,
                                       true);
        this.periodService.updatePeriod(this.finalData, this.periodSelected.idTraining).subscribe(((res) => {
            this.result = res;
            if(this.result == true){
                this.notificationService.setNotificationInfo('Period success to edited');
            }else{
                this.notificationService.setNotificationError('Period failed to edited !');
            }
        }));
        this.closeDialog();
        //window.location.reload();
    }

    closeDialog(){
        this.dialogRef.close();
    }
}