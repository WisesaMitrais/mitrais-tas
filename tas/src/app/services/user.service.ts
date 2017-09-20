import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { User } from './user';
import { Observable } from 'rxjs/Rx';
import { UrlService } from '../services/url.service';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/observable/throw';

@Injectable() 
 export class UserService {
     url: string;

    constructor(private http: Http,
        private urlService: UrlService) { }

    public getDataUsers(): Observable<User[]>{
        this.url = this.urlService.getAllUserData();
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