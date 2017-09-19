import { Injectable } from '@angular/core';

@Injectable() 
export class UrlService {
    public loginUrl(u: string, p: string): string{
        return 'http://172.19.14.152:8080/login/auth?username='+u+'&password='+p;
    }

    public getDataActiveTraining(){
        return 'http://172.19.14.152:8080/dashboard/activeTraining';
    }

    public getDataBCCSchedule(){
        return 'http://172.19.14.152:8080/dashboard/bccCourse';
    }

    

    public getAllUserData(){
        return 'http://172.19.14.152:8080/user/all';
    }
}