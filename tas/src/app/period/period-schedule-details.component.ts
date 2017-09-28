import { Component, Inject, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { PeriodComponent } from './period.component';
import { SchedulePeriod } from '../services/period-schedule';

import { PeriodService } from '../services/period.service';

@Component({
    selector: 'tas-period-schedule-details',
    templateUrl: 'period-schedule-details.component.html',
    styleUrls: ['./period-schedule-details.component.css']
})
export class PeriodScheduleDetailsComponent implements OnInit{
    scheduleSelected: SchedulePeriod;

    constructor(
      public dialogRef: MdDialogRef<PeriodComponent>,
        private periodService: PeriodService,
      @Inject(MD_DIALOG_DATA) public data: any) {
    }

    ngOnInit(){
        console.log(this.scheduleSelected);
    }

    closeDialog(){
        this.dialogRef.close();
    }
}