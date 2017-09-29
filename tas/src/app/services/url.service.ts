import { Injectable } from '@angular/core';
import { Headers } from '@angular/http';

@Injectable() 
export class UrlService {
    baseUrl: string = 'http://172.19.14.152:8080/';

    public getHeaderSecurity(): Headers{
        let headers = new Headers();
        headers.append("Authorization", "Basic " + btoa('bima' + ":" + 'bimateam')); 
        headers.append("Content-Type", "application/json");
        return headers
    }
    public loginUrl(u: string, p: string): string{
        return this.baseUrl+'login/auth?username='+u+'&password='+p;
    }


    public getDataActiveTraining(){
        return this.baseUrl+'dashboard/activeTraining';
    }
    public getDataBCCSchedule(){
        return this.baseUrl+'dashboard/bccCourse';
    }

    
    public getAllPeriodData(){
        return this.baseUrl+'period/all';
    }
    public postPeriodData(){
        return this.baseUrl+'period/create';
    }
    public getEligibleParticipantList(idTraining: number){
        return this.baseUrl+'eligibleparticipant/findByTraining/'+idTraining;
    }
    public getUserEligibleParticipant(idTraining: number){
        return this.baseUrl+'user/findByTrainingNot/'+idTraining;
    }
    public postAddUserEligibleParticipant(){
        return this.baseUrl+'eligibleparticipant/add';
    }
    public deleteEligibleParticipant(eligibleNumber: string){
        return this.baseUrl+'eligibleparticipant/'+eligibleNumber+'/delete';
    }
    public getScheduleList(idTraining: number){
        return this.baseUrl+'schedule/findByTraining/'+idTraining;
    }
    public postScheduleData(){
        return this.baseUrl+'schedule/create';
    }
    public postEnrollParticipant(){
        return this.baseUrl+'enrollment/add';
    }
    public getEnrollParticipant(idSchedule: number){
        return this.baseUrl+'enrollment/findBySchedule/'+idSchedule;
    }
    public deleteEnrollParticipant(idEnrollment: number){
        return this.baseUrl+'enrollment/'+idEnrollment+'/delete';
    }
    public updateSchedule(idSchedule: string){
        return this.baseUrl+'schedule/'+idSchedule+'/update';
    }
    public deleteSchedule(idSchedule: string){
        return this.baseUrl+'schedule/'+idSchedule+'/delete';
    }
    public updatePeriod(idTraining: number){
        return this.baseUrl+'period/'+idTraining+'/update';
    }
    public deletePeriod(idTraining: string){
        return this.baseUrl+'period/'+idTraining+'/delete';
    }


    public getAllUserData(){
        return this.baseUrl+'user/all';
    }


    public getTrainerData(){
        return this.baseUrl+'user/findTrainer/';
    }
    public getRoomData(){
        return this.baseUrl+'room/all';
    }
    public getNotBCCCourseData(){
        return this.baseUrl+'course/notBcc';
    }
    public getBCCCourseData(){
        return this.baseUrl+'course/bcc';
    }
}