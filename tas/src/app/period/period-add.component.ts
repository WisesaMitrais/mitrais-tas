import { Component, Inject } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { PeriodComponent } from './period.component';

import { AddPeriod } from '../services/period-add';
import { PeriodService } from '../services/period.service'


@Component({
    selector: 'tas-period-add',
    templateUrl: 'period-add.component.html',
    styleUrls: ['./period-add.component.css']
})
export class AddPeriodComponent {  
    newPeriod: AddPeriod;

    constructor(
      public dialogRef: MdDialogRef<PeriodComponent>,
        periodService: PeriodService,
      @Inject(MD_DIALOG_DATA) public data: any) {
    }

    createNewPeriod(){
        alert(this.newPeriod.trainingName);
    }

    closeDialog(){
        this.dialogRef.close();
    }
}