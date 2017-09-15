import { Component, Inject } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { PeriodComponent } from './period.component';

@Component({
    selector: 'tas-period-add',
    templateUrl: 'period-add.component.html',
    styleUrls: ['./period-add.component.css']
})
export class AddPeriodComponent {  
    constructor(
      public dialogRef: MdDialogRef<PeriodComponent>,
      @Inject(MD_DIALOG_DATA) public data: any) {
    }
}