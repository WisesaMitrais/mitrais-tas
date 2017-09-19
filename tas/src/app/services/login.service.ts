import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { CookieService } from 'angular2-cookie/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { RoleComponent } from '../login/role.component';

import { Login } from './login';
import { Observable } from 'rxjs/Rx';
import { UrlService } from '../services/url.service';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/observable/throw';

declare var $: any;

@Injectable() 
 export class LoginService {
     url: string;
     username: string;
     password: string;
     checkLogin: Login;

    constructor(private http: Http, 
        private cookieService: CookieService,
        private urlService: UrlService,
        public dialog: MdDialog,) { }

    public checkUserData(): Observable<Login>{
        this.url = this.urlService.loginUrl(this.username, this.password);
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

    public loginProcess(u: string, p: string, v: boolean){
        if(u.length == 0 || p.length == 0){
            this.showAlert('bottom', 'center', 1);
        }else{
            this.username = u;
            this.password = p;
            this.checkUserData().subscribe(((checkLogin) => {
                this.checkLogin = checkLogin;
                if(this.checkLogin[0].idUser == 0){
                    this.showAlert('bottom', 'center', 2);
                }else{
                    if(v == true){
                        this.cookieService.put('loginData',JSON.stringify({'username': u, 'password': p}));
                    }
                    this.cookieService.put('currentUser', JSON.stringify({'id': this.checkLogin[0].idUser, 'name': this.checkLogin[0].name, 'roles': this.checkLogin[0].roles, 'roleActive': ''}));
                    let dialogRef = this.dialog.open(RoleComponent, {
                        width: '500px',
                        height: '225px'
                        });
                }
              }));
        }
    }

    private showAlert(from, align, status : number){
        if(status == 1){
            $.notify({
                icon: "notifications",
                message: "<b>WARNING</b> - username and password required !"
            },{
                type: 'warning',
                timer: 3000,
                placement: {
                    from: from,
                    align: align
                }
            });
        }else{
            $.notify({
                icon: "notifications",
                message: "<b>Error</b> - Login failed ! invalid username or password"
            },{
                type: 'danger',
                timer: 3000,
                placement: {
                    from: from,
                    align: align
                }
            });
        }
    }
 }