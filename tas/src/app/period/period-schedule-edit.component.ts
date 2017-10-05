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
    dayData = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
    updateScheduleData: any = {};
    course: Course;
    trainer1;
    trainer2;
    room: Room;
    day: string;
    dayNumber: number;
    periodic;
    isPeriodic:boolean;
    finalData;
    currentUserModel: any = {};
    result: boolean;
    tempDayNumber = 0;

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
        this.updateScheduleData.hour = this.scheduleSelected.hour;
        if(this.bccTrainingSelected === 'true'){
            this.updateScheduleData.startDate = new Date(this.scheduleSelected._startDate);
            this.updateScheduleData.endDate = new Date(this.scheduleSelected._endDate);
        }else{
            this.updateScheduleData.startDate = new Date();
            this.updateScheduleData.endDate = new Date();
        }
        if(this.MonCheck() >= 0){
            this.tempDayNumber = 1;
        }else if(this.TueCheck() >= 0){
            this.tempDayNumber = 2;
        }else if(this.WedCheck() >= 0){
            this.tempDayNumber = 3;
        }else if(this.ThuCheck() >= 0){
            this.tempDayNumber = 4;
        }else if(this.FriCheck() >= 0){
            this.tempDayNumber = 5;
        }else if(this.SatCheck() >= 0){
            this.tempDayNumber = 6;
        }else if(this.SunCheck() >= 0){
            this.tempDayNumber = 7;
        }
        switch (this.tempDayNumber) {
            case 1:
                this.day = 'Monday';
                break;
            case 2:
                this.day = 'Tuesday';
                break;
            case 3:
                this.day = 'Wednesday';
                break;
            case 4:
                this.day = 'Thursday';
                break;
            case 5:
                this.day = 'Friday';
                break;
            case 6:
                this.day = 'Saturday';
                break;
            case 7:
                this.day = 'Sunday';
                break;  
            default:
                break;
        }
        this.periodService.getTrainerData().subscribe(((trainerData) => {
            this.trainerData = trainerData;
            this.trainer1 = this.scheduleSelected._MainTrainer;
            this.trainer2 = this.scheduleSelected._BackupTrainer;
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
            this.isPeriodic = false;
        }
        switch (this.day) {
            case 'Monday':
                this.dayNumber = 1;
                break;
            case 'Tuesday':
                this.dayNumber = 2;
                break;
            case 'Wednesday':
                this.dayNumber = 3;
                break;
            case 'Thursday':
                this.dayNumber = 4;
                break;
            case 'Friday':
                this.dayNumber = 5;
                break;
            case 'Saturday':
                this.dayNumber = 6;
                break;
            case 'Sunday':
                this.dayNumber = 7;
                break;  
            default:
                break;
        }
        this.finalData = new AddNewSchedule(this.course.idCourse,
                                       this.room.idRoom,
                                       this.scheduleSelected._Training,
                                       this.trainer1,
                                       this.trainer2,
                                       this.updateScheduleData.startDate,
                                       this.updateScheduleData.endDate,
                                       this.updateScheduleData.capacity,
                                       this.isPeriodic,
                                       this.dayNumber,
                                       this.updateScheduleData.hour,
                                       this.currentUserModel.id,
                                       this.currentUserModel.id,);
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

    MonCheck(): number{
        var text = this.scheduleSelected.day, regex = /Monday/;
        return text.search(regex);
    }

    TueCheck(): number{
        var text = this.scheduleSelected.day, regex = /Tuesday/;
        return text.search(regex);
    }

    WedCheck(): number{
        var text = this.scheduleSelected.day, regex = /Wednesday/;
        return text.search(regex);
    }

    ThuCheck(): number{
        var text = this.scheduleSelected.day, regex = /Thursday/;
        return text.search(regex);
    }

    FriCheck(): number{
        var text = this.scheduleSelected.day, regex = /Friday/;
        return text.search(regex);
    }

    SatCheck(): number{
        var text = this.scheduleSelected.day, regex = /Saturday/;
        return text.search(regex);
    }

    SunCheck(): number{
        var text = this.scheduleSelected.day, regex = /Sunday/;
        return text.search(regex);
    }

    closeDialog(){
        this.dialogRef.close();
    }
}