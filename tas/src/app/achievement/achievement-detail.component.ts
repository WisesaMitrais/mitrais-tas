import { Component, Inject, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { AchievementComponent } from './achievement.component';

import { Achievement } from '../services/achievement';
import { UpdateAchievementData } from '../services/achievement-update';
import { AchievementService } from '../services/achievement.service';
import { Period } from '../services/period';
import { NotificationService } from '../services/notification.service';

@Component({
    selector: 'tas-achievement-detail',
    templateUrl: 'achievement-detail.component.html',
    styleUrls: ['./achievement-detail.component.css']
})
export class AchievementDetailComponent implements OnInit{
    achievementSelected: Achievement;
    statusData: string[] = ['Not Required', 'Term'];
    trainingData: Period[];
    course;
    status;
    statusFinal1;
    statusFinal2;
    statusFinal3;
    statusFinal4;
    statusFinal5;
    statusFinal6;
    statusFinal7;
    statusFinal8;
    statusFinal9;
    statusFinal10;
    training;
    trainingFinal1;
    trainingFinal2;
    trainingFinal3;
    trainingFinal4;
    trainingFinal5;
    trainingFinal6;
    trainingFinal7;
    trainingFinal8;
    trainingFinal9;
    trainingFinal10;
    statusDataIsEnabled: boolean;
    trainingDataIsEnabled: boolean;
    finalData;
    result;

    constructor(
      public dialogRef: MdDialogRef<AchievementComponent>,
        private notificationService: NotificationService,
        private achievementService: AchievementService,
      @Inject(MD_DIALOG_DATA) public data: any) {
    }

    ngOnInit(){
        this.achievementService.getTrainingBCCData().subscribe(((trainingData) => {
            this.trainingData = trainingData; this.trainingData
        }));
        this.trainingDataIsEnabled = true;

        this.statusFinal1 = this.achievementSelected.beginningStatus;
        this.statusFinal2 = this.achievementSelected.li1Status;
        this.statusFinal3 = this.achievementSelected.li2Status;
        this.statusFinal4 = this.achievementSelected.int1Status;
        this.statusFinal5 = this.achievementSelected.int2Status;
        this.statusFinal6 = this.achievementSelected.bw1Status;
        this.statusFinal7 = this.achievementSelected.ce1Status;
        this.statusFinal8 = this.achievementSelected.bw2Status;
        this.statusFinal9 = this.achievementSelected.ce2Status;
        this.statusFinal10 = this.achievementSelected.presentationSkill2Status;

        this.trainingFinal1 = this.achievementSelected._beginning;
        this.trainingFinal2 = this.achievementSelected._LI1;
        this.trainingFinal3 = this.achievementSelected._LI2;
        this.trainingFinal4 = this.achievementSelected._Int1;
        this.trainingFinal5 = this.achievementSelected._Int2;
        this.trainingFinal6 = this.achievementSelected._BW1;
        this.trainingFinal7 = this.achievementSelected._CE1;
        this.trainingFinal8 = this.achievementSelected._BW2;
        this.trainingFinal9 = this.achievementSelected._CE2;
        this.trainingFinal10 = this.achievementSelected._presentationSkill2;
    }

    isEnabledTrainingData(){
        if(this.status === 'Not Required'){
            this.trainingDataIsEnabled = false;
            this.training = null;
        }else{
            this.trainingDataIsEnabled = true;
        }

        if(this.course === 'be'){
            if(this.status === 'Not Required'){
                this.statusFinal1 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal1 = 'Term'; 
            }else{
                this.statusFinal1 = '-';
            }
        }else if(this.course === 'li1'){
            if(this.status === 'Not Required'){
                this.statusFinal2 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal1 = 'Term'; 
            }else{
                this.statusFinal1 = '-';
            }
        }else if(this.course === 'li2'){
            if(this.status === 'Not Required'){
                this.statusFinal3 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal3 = 'Term'; 
            }else{
                this.statusFinal3 = '-';
            }
        }else if(this.course === 'int1'){
            if(this.status === 'Not Required'){
                this.statusFinal4 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal4 = 'Term'; 
            }else{
                this.statusFinal4 = '-';
            }
        }else if(this.course === 'int2'){
            if(this.status === 'Not Required'){
                this.statusFinal5 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal5 = 'Term'; 
            }else{
                this.statusFinal5 = '-';
            }
        }else if(this.course === 'bw1'){
            if(this.status === 'Not Required'){
                this.statusFinal6 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal6 = 'Term'; 
            }else{
                this.statusFinal6 = '-';
            }
        }else if(this.course === 'ce1'){
            if(this.status === 'Not Required'){
                this.statusFinal7 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal7 = 'Term'; 
            }else{
                this.statusFinal7 = '-';
            }
        }else if(this.course === 'bw2'){
            if(this.status === 'Not Required'){
                this.statusFinal8 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal8 = 'Term'; 
            }else{
                this.statusFinal8 = '-';
            }
        }else if(this.course === 'ce2'){
            if(this.status === 'Not Required'){
                this.statusFinal9 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal9 = 'Term'; 
            }else{
                this.statusFinal9 = '-';
            }
        }else if(this.course === 'ps2'){
            if(this.status === 'Not Required'){
                this.statusFinal10 = 'Not Required';
            }else if(this.status === 'Term'){
                this.statusFinal10 = 'Term'; 
            }else{
                this.statusFinal10 = '-';
            }
        }
    }

    changeTrainingData(){
        if(this.course === 'be'){
            this.trainingFinal1 = this.training;
        }else if(this.course === 'li1'){
            this.trainingFinal2 = this.training;
        }else if(this.course === 'li2'){
            this.trainingFinal3 = this.training;
        }else if(this.course === 'int1'){
            this.trainingFinal4 = this.training;
        }else if(this.course === 'int2'){
            this.trainingFinal5 = this.training;
        }else if(this.course === 'bw1'){
            this.trainingFinal6 = this.training;
        }else if(this.course === 'ce1'){
            this.trainingFinal7 = this.training;
        }else if(this.course === 'bw2'){
            this.trainingFinal8 = this.training;
        }else if(this.course === 'ce2'){
            this.trainingFinal9 = this.training;
        }else if(this.course === 'ps2'){
            this.trainingFinal10 = this.training;
        }
    }

    statusCheck(){
        if(this.status === 'Not Required'){
            this.training = null;
        }
        if(this.course === 'be'){
            if(this.achievementSelected.beginningStatus === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.beginningStatus === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.beginningStatus === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._beginning;
                this.trainingDataIsEnabled = true;
            }
        }else if(this.course === 'li1'){
            if(this.achievementSelected.li1Status === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.li1Status === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.li1Status === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._LI1;
                this.trainingDataIsEnabled = true;
            }
        }else if(this.course === 'li2'){
            if(this.achievementSelected.li2Status === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.li2Status === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.li2Status === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._LI2;
                this.trainingDataIsEnabled = true;
            }
        }else if(this.course === 'int1'){
            if(this.achievementSelected.int1Status === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.int1Status === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.int1Status === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._Int1;
                this.trainingDataIsEnabled = true;
            }
        }else if(this.course === 'int2'){
            if(this.achievementSelected.int2Status === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.int2Status === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.int2Status === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._Int2;
                this.trainingDataIsEnabled = true;
            }
        }else if(this.course === 'bw1'){
            if(this.achievementSelected.bw1Status === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.bw1Status === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.bw1Status === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._BW1;
                this.trainingDataIsEnabled = true;
            }
        }else if(this.course === 'ce1'){
            if(this.achievementSelected.ce1Status === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.ce1Status === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.ce1Status === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._CE1;
                this.trainingDataIsEnabled = true;
            }
        }else if(this.course === 'bw2'){
            if(this.achievementSelected.bw2Status === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.bw2Status === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.bw2Status === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._BW2;
                this.trainingDataIsEnabled = true;
            }
        }else if(this.course === 'ce2'){
            if(this.achievementSelected.ce2Status === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.ce2Status === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.ce2Status === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._CE2;
                this.trainingDataIsEnabled = true;
            }
        }else if(this.course === 'ps2'){
            if(this.achievementSelected.presentationSkill2Status === '-'){
                this.status = null;
                this.training = null;
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.presentationSkill2Status === 'Not Required'){
                this.status = this.statusData[0];
                this.trainingDataIsEnabled = false;
            }else if(this.achievementSelected.presentationSkill2Status === 'Term'){
                this.status = this.statusData[1];
                this.training = this.achievementSelected._presentationSkill2;
                this.trainingDataIsEnabled = true;
            }
        }
    }

    updateAchievement(){
        this.finalData = new UpdateAchievementData(
                    this.trainingFinal1,
                    this.trainingFinal2,
                    this.trainingFinal3,
                    this.trainingFinal4,
                    this.trainingFinal5,
                    this.trainingFinal6,
                    this.trainingFinal7,
                    this.trainingFinal8,
                    this.trainingFinal9,
                    this.trainingFinal10,
                    this.statusFinal1,
                    this.statusFinal2,
                    this.statusFinal3,
                    this.statusFinal4,
                    this.statusFinal5,
                    this.statusFinal6,
                    this.statusFinal7,
                    this.statusFinal8,
                    this.statusFinal9,
                    this.statusFinal10);
        console.log(this.finalData);
        this.achievementService.postUpdateData(this.finalData, this.achievementSelected.idEmployee).subscribe(((res) => {
        this.result = res;
        if(this.result == true){
        this.notificationService.setNotificationInfo('Achievement success to edited');
        }else{
        this.notificationService.setNotificationError('Achievement failed to edited !');
        }
        }));
        this.closeDialog();
        // window.location.reload();
    }

    closeDialog(){
        this.dialogRef.close();
    }
}