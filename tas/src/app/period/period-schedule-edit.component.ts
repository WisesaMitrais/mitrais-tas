import { Component, Inject, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { CookieService } from 'angular2-cookie/core';

import { PeriodComponent } from './period.component';

import { Room } from '../services/room';
import { Course } from '../services/course';
import { Trainer } from '../services/trainer';
import { SchedulePeriod } from '../services/period-schedule';
import { AddNewSchedule } from '../services/period-schedule-add';
import { PeriodService } from '../services/period.service';
import { NotificationService } from '../services/notification.service';

@Component({
    selector: 'tas-period-schedule-edit',
    templateUrl: 'period-schedule-edit.component.html',
    styleUrls: ['./period-schedule-edit.component.css']
})
export class PeriodScheduleEditComponent implements OnInit{
    scheduleSelected: SchedulePeriod;
    bccTrainingSelected;
    courseData: Course[];
    trainerData: Trainer[];
    roomData: Room[];
    updateScheduleData: any = {};
    course: Course;
    trainer1: Trainer;
    trainer2: Trainer;
    room: Room;
    periodic;
    isPeriodic:boolean;
    finalData;
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
        if(this.bccTrainingSelected === 'true'){
            this.periodService.getBCCCourseData().subscribe(((courseData) => {
                this.courseData = courseData;
                this.course = this.courseData[this.scheduleSelected._Course - 1];
            }));
        }else{
            this.periodService.getNotBCCCourseData().subscribe(((courseData) => {
                this.courseData = courseData;
                this.course = this.courseData[this.scheduleSelected._Course - 1];
            }));
        }
        if(this.scheduleSelected.periodic === true){
            this.periodic = 'periodic';
        }else{
            this.periodic = 'fixed';
        } 
        this.updateScheduleData.startDate = new Date(this.scheduleSelected._startDate);
        this.updateScheduleData.endDate = new Date(this.scheduleSelected._endDate);
        this.periodService.getTrainerData().subscribe(((trainerData) => {
            this.trainerData = trainerData;
            this.trainer1 = this.trainerData[this.scheduleSelected._MainTrainer - 1];
            this.trainer2 = this.trainerData[this.scheduleSelected._BackupTrainer - 1];
        }));
        this.periodService.getRoomData().subscribe(((roomData) => {
            this.roomData = roomData;
            this.room = this.roomData[this.scheduleSelected._Room - 1];
        }));
        this.updateScheduleData.daytime = this.scheduleSelected.day;
        this.updateScheduleData.capacity = this.scheduleSelected.capacity;
        if (this.cookieService.get('currentUser')){
            this.currentUserModel = JSON.parse(this.cookieService.get('currentUser'));
        }
    }

    updateSchedule(){
        if(this.periodic === 'periodic'){
            this.isPeriodic = true;
        }else{
            this.isPeriodic = false; //HARUSNYA START DATE DAN END DATE MENJADI STATIS !!!
        }
        this.finalData = new AddNewSchedule(this.course.idCourse,
                                       this.room.idRoom,
                                       this.scheduleSelected._Training,
                                       this.trainer1.idTrainer,
                                       this.trainer2.idTrainer,
                                       this.updateScheduleData.startDate,
                                       this.updateScheduleData.endDate,
                                       this.updateScheduleData.capacity,
                                       this.isPeriodic,
                                       this.updateScheduleData.daytime,
                                       this.currentUserModel.id,
                                       this.currentUserModel.id,);
        console.log(this.finalData);
        this.periodService.updateSchedule(this.finalData, this.scheduleSelected.idSchedule).subscribe(((res) => {
            this.result = res;
            if(this.result == true){
                this.notificationService.setNotificationInfo('Period success to edited');
            }else{
                this.notificationService.setNotificationError('Period failed to edited !');
            }
        }));
        this.closeDialog();
        // window.location.reload();
    }

    closeDialog(){
        this.dialogRef.close();
    }
}