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
    courseData: Course[];
    trainerData: Trainer[];
    roomData: Room[];
    newSchedule: any = {};
    course: Course;
    trainer1: Trainer;
    trainer2: Trainer;
    room: Room;
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
        this.newSchedule.startDate = new Date();
        this.newSchedule.endDate = new Date();
        if (this.cookieService.get('currentUser')){
            this.currentUserModel = JSON.parse(this.cookieService.get('currentUser'));
        }
    }

    createNewSchedule(){
        if(this.periodic === 'periodic'){
            this.isPeriodic = true;
        }else{
            this.isPeriodic = false; //HARUSNYA START DATE DAN END DATE MENJADI STATIS !!!
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
                                       this.newSchedule.daytime,
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