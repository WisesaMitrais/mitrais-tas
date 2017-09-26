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
        return this.baseUrl+'period/create'
    }

    public getEligibleParticipantList(idTraining: number){
        return this.baseUrl+'eligibleparticipant/findByTraining/'+idTraining
    }

    public getUserEligibleParticipant(idTraining: number){
        return this.baseUrl+'user/findByTrainingNot/'+idTraining;
    }

    public postAddUserEligibleParticipant(){
        return this.baseUrl+'eligibleparticipant/add'
    }

    public getAllUserData(){
        return this.baseUrl+'user/all';
    }
}