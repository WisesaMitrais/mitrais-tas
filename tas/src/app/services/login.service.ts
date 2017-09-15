import { Injectable } from '@angular/core';
import { User } from './user';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable() 
 export class LoginService {
    userUrl: String = 'http://172.19.14.152:8080/login/auth?username=admin&password=admin';

    constructor(private http: Http) { }

    getUserLogin(u: String, p: String): Promise<User>{
        const url = `${this.userUrl}`;
        return this.http.get(url)
        .toPromise()
        .then(response => response.json().data as User)
        .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('Ada Error', error);
        return Promise.reject(error.message || error);
    }
 }