import { Component, Inject, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { CookieService } from 'angular2-cookie/core';

import { PeriodComponent } from './period.component';

import { Room } from '../services/room';
import { Course } from '../services/course';
import { Trainer } from '../services/trainer';
import { AddNewSchedule } from '../services/period-schedule-add';
import { PeriodService } from '../services/period.service';
import { NotificationService } from '../services/notification.service';

@Component({
    selector: 'tas-period-schedule-add',
    templateUrl: 'period-schedule-add.component.html',
    styleUrls: ['./period-schedule-add.component.css']
})
export class PeriodScheduleAddComponent implements OnInit{
    idTrainingSelected;
    bccTrainingSelected;
    startTrainingSelected;
    endTrainingSelected;
    startTraining2: string;
    endTraining2: string;
    courseData: Course[];
    trainerData: Trainer[];
    roomData: Room[];
    dayData = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
    newSchedule: any = {};
    course: Course;
    trainer1: Trainer;
    trainer2: Trainer;
    room: Room;
    day: string;
    dayNumber: number;
    periodic: string;
    isPeriodic: boolean;
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
            }));
        }else{
            this.periodService.getNotBCCCourseData().subscribe(((courseData) => {
                this.courseData = courseData;
            }));
        }
        this.periodService.getTrainerData().subscribe(((trainerData) => {
            this.trainerData = trainerData;
        }));
        this.periodService.getRoomData().subscribe(((roomData) => {
            this.roomData = roomData;
        }));
        if(this.bccTrainingSelected === 'true'){
            this.newSchedule.startDate = new Date(parseInt(this.startTraining2));
            this.newSchedule.endDate = new Date(parseInt(this.endTraining2));
        }else{
            this.newSchedule.startDate = new Date();
            this.newSchedule.endDate = new Date();
        }
        if (this.cookieService.get('currentUser')){
            this.currentUserModel = JSON.parse(this.cookieService.get('currentUser'));
        }
    }

    createNewSchedule(){
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
                                       this.idTrainingSelected,
                                       this.trainer1.idTrainer,
                                       this.trainer2.idTrainer,
                                       this.newSchedule.startDate,
                                       this.newSchedule.endDate,
                                       this.newSchedule.capacity,
                                       this.isPeriodic,
                                       this.dayNumber,
                                       this.newSchedule.hour,
                                       this.currentUserModel.id,
                                       this.currentUserModel.id,);
        console.log(this.finalData);
        this.periodService.AddNewSchedule(this.finalData).subscribe(((res) => {
            this.result = res;
            if(this.result == true){
                this.notificationService.setNotificationInfo('Period success to created');
            }else{
                this.notificationService.setNotificationError('Period failed to created !');
            }
        }));
        this.closeDialog();
        // window.location.reload();
    }

    closeDialog(){
        this.dialogRef.close();
    }
}