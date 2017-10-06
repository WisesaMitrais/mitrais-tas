import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { CookieService } from 'angular2-cookie/core';

import { SchedulePeriod } from './period-schedule';
import { TrainingMaintenanceAssessment } from './training-maintenance-assessment';
import { TrainingMaintenanceAssessmentUpdate } from './training-maintenance-assessment-update';
import { Observable } from 'rxjs/Rx';
import { UrlService } from '../services/url.service';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/observable/throw';

@Injectable() 
 export class TrainingMaintenanceService {
     url: string;
     headers;

    constructor(private http: Http,
        private urlService: UrlService) { 
            this.headers = this.urlService.getHeaderSecurity();
        }

    public getTrainingMaintenanceData(idTrainer: number): Observable<SchedulePeriod[]>{
        this.url = this.urlService.getTrainingByTrainerData(idTrainer);
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getAllTrainingMaintenanceData(): Observable<SchedulePeriod[]>{
        this.url = this.urlService.getAllTrainingData();
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getAllAssessmentData(idSchedule: string): Observable<TrainingMaintenanceAssessment[]>{
        this.url = this.urlService.getAssessmentData(idSchedule);
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public updateAssessmentData(idSchedule: string, assessmentData: TrainingMaintenanceAssessmentUpdate[]): Observable<boolean>{
        this.url = this.urlService.updateAssessmentData(idSchedule);
        return this.http.post(this.url, assessmentData, {headers: this.headers})
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