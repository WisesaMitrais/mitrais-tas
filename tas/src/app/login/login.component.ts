import { Component, OnInit, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CookieService } from 'angular2-cookie/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { RoleComponent } from './role.component';

import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../services/alert.service';
import { LoginService } from '../services/login.service';
import { User } from '../services/user';

declare var $: any;

@Component({
  selector: 'tas-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    loading = false;
    model: any = {};
    user: User;

    constructor(
        public dialog: MdDialog,
        private loginService: LoginService,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService ,
        private cookieService: CookieService){
    }

    openRoleDialog(): void {
        let dialogRef = this.dialog.open(RoleComponent, {
        width: '500px',
        height: '225px'
        });
    }

    ngOnInit(){
        this.model = JSON.parse(this.cookieService.get('loginData'));
    }

    login(): void {
        // this.loginService
        // .getUserLogin("admin", "admin")
        // .then(user => this.user = user);
        // alert(this.user);
        if (this.model.rememberMe){
            this.rememberMe();
        }
        this.authenticationService.login(this.model.username, this.model.password);
    }

    rememberMe(){
        this.cookieService.put('loginData',JSON.stringify({ 'username': this.model.username, 'password': this.model.password }));
    }

    showWarning(from, align){
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
    }
}