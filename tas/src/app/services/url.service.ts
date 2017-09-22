import { Injectable } from '@angular/core';

@Injectable() 
export class UrlService {
    baseUrl: string = 'http://172.19.14.152:8080/';

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

    public getAllUserData(){
        return this.baseUrl+'user/all';
    }
}