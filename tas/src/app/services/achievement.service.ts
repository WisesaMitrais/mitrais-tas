import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { CookieService } from 'angular2-cookie/core';

import { Achievement } from './achievement';
import { UpdateAchievementData } from './achievement-update';
import { AchievementRepeatHistory } from './achievement-repeat';
import { Period } from './period';
import { Observable } from 'rxjs/Rx';
import { UrlService } from '../services/url.service';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/observable/throw';

@Injectable() 
 export class AchievementService {
     url: string;
     headers;

    constructor(private http: Http,
        private urlService: UrlService) { 
            this.headers = this.urlService.getHeaderSecurity();
        }

    public getAllAchievementData(): Observable<Achievement[]>{
        this.url = this.urlService.getAllAchievementData();
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }
    
    public getSingleAchievementData(idUser: number): Observable<Achievement>{
        this.url = this.urlService.getSingleAchievementData(idUser);
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getTrainingBCCData(): Observable<Period[]>{
        this.url = this.urlService.getTrainingBCC();
        return this.http.get(this.url, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public postUpdateData(achievementData: UpdateAchievementData, idUser: number): Observable<boolean>{
        this.url = this.urlService.updateAchievementData(idUser);
        return this.http.post(this.url, achievementData, {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getRepeatHistoryData(idUser: number): Observable<AchievementRepeatHistory[]>{
        this.url = this.urlService.getRepeatHistory(idUser);
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