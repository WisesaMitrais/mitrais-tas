import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { CookieService } from 'angular2-cookie/core';

import { Enrollment } from './enrollment';
import { Observable } from 'rxjs/Rx';
import { UrlService } from '../services/url.service';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/observable/throw';

@Injectable() 
 export class EnrollmentService {
     url: string;
     headers;

    constructor(private http: Http,
        private urlService: UrlService) { 
            this.headers = this.urlService.getHeaderSecurity();
        }

    public getEnrollmentData(idUser: number): Observable<Enrollment[]>{
        this.url = this.urlService.getEnrollmentData(idUser);
        return this.http.get(this.url, {headers: this.headers})
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