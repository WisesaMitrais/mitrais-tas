import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Period } from './period';
import { AddPeriod } from './period-add';
import { Observable } from 'rxjs/Rx';
import { UrlService } from '../services/url.service';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/observable/throw';

@Injectable() 
 export class PeriodService {
     url: string;

    constructor(private http: Http,
        private urlService: UrlService) { }

    public getDataPeriod(): Observable<Period[]>{
        this.url = this.urlService.getAllPeriodData();
        return this.http.get(this.url)
            .map(this.extractData)
            .catch(this.handleError);
    }

    public createdDataPeriod(periodData: AddPeriod): Observable<boolean>{
        this.url = this.urlService.postPeriodData();
        return this.http.get(this.url)
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