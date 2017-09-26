import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Period } from './period';
import { User } from './user';
import { AddPeriod } from './period-add';
import { EligibleParticipantPeriod } from './period-eligibleparticipant';
import { AddUserEligibleParticipant } from '../services/period-eligibleparticipant-add';
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