import { Component, OnInit, Inject } from '@angular/core';
import { CookieService } from 'angular2-cookie/core';
import { Router } from '@angular/router';

import { LoginService } from '../services/login.service';

@Component({
  selector: 'tas-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model: any = {};

    constructor(
        private loginService: LoginService,
        private cookieService: CookieService,
        private router: Router){
    }

    ngOnInit(){
        if (this.cookieService.get('currentUser')){
            this.router.navigate(['/home/dashboard'])
        }
        this.model = JSON.parse(this.cookieService.get('loginData'));
    }

    login(): void {
        this.loginService.loginProcess(this.model.username, this.model.password, this.model.rememberMe)
    }
}