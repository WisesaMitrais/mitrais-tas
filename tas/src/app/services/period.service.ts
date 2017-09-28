import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Period } from './period';
import { User } from './user';
import { Room } from './room';
import { Course } from './course';
import { Trainer } from './trainer';
import { AddPeriod } from './period-add';
import { EligibleParticipantPeriod } from './period-eligibleparticipant';
import { AddUserEligibleParticipant } from '../services/period-eligibleparticipant-add';
import { AddEnrollParticipant } from '../services/period-enrollparticipant';
import { SchedulePeriod } from '../services/period-schedule';
import { AddNewSchedule } from '../services/period-schedule-add';
import { Observable } from 'rxjs/Rx';
import { UrlService } from '../services/url.service';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/observable/throw';

@Injectable() 
 export class PeriodService {
     url: string;
     headers;

    constructor(private http: Http,
        private urlService: UrlService) { 
            this.headers = this.urlService.getHeaderSecurity();
        }

    public getDataPeriod(): Observable<Period[]>{
        this.url = this.urlService.getAllPeriodData();
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public createDataPeriod(periodData: AddPeriod): Observable<boolean>{
        this.url = this.urlService.postPeriodData();
        return this.http.post(this.url, periodData, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getDataEligibleParticipant(idTraining: number): Observable<EligibleParticipantPeriod[]>{
        this.url = this.urlService.getEligibleParticipantList(idTraining);
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getUserEligibleParticipant(idTraining: number): Observable<User[]>{
        this.url = this.urlService.getUserEligibleParticipant(idTraining);
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public AddEligibleParticipant(eligibleParticipantData: AddUserEligibleParticipant): Observable<boolean>{
        this.url = this.urlService.postAddUserEligibleParticipant();
        return this.http.post(this.url, eligibleParticipantData, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public deleteEligibleParticipant(eligibleNumber: string): Observable<boolean>{
        this.url = this.urlService.deleteEligibleParticipant(eligibleNumber);
        return this.http.delete(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getDataSchedule(idTraining: number): Observable<SchedulePeriod[]>{
        this.url = this.urlService.getScheduleList(idTraining);
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getTrainerData(): Observable<Trainer[]>{
        this.url = this.urlService.getTrainerData();
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getRoomData(): Observable<Room[]>{
        this.url = this.urlService.getRoomData();
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getBCCCourseData(): Observable<Course[]>{
        this.url = this.urlService.getBCCCourseData();
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getNotBCCCourseData(): Observable<Course[]>{
        this.url = this.urlService.getNotBCCCourseData();
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public AddNewSchedule(scheduleData: AddNewSchedule): Observable<boolean>{
        this.url = this.urlService.postScheduleData();
        return this.http.post(this.url, scheduleData, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public AddEnrollParticipant(enrollParticipantData: AddEnrollParticipant): Observable<boolean>{
        this.url = this.urlService.postEnrollParticipant();
        return this.http.post(this.url, enrollParticipantData, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }
    
    private extractData(res:Response) {
        let body = res.json();
        return body || [];
    }
    
    private handleError(error:any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg);
        return Observable.throw(errMsg);
    }
 }