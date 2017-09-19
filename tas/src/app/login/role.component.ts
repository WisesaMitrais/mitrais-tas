import { Component, Inject, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CookieService } from 'angular2-cookie/core';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { LoginComponent } from './login.component';
import { NavbarComponent } from '../components/navbar/navbar.component';

@Component({
    selector: 'tas-role',
    templateUrl: './role.component.html',
    styleUrls: ['./role.component.css']
  })
export class RoleComponent implements OnInit{
    userRole;
    roles;
    adminRole: boolean = false;
    managerRole: boolean = false;
    trainerRole: boolean = false;
    staffRole: boolean = false;

    constructor(
    public dialogRef: MdDialogRef<LoginComponent>,
    public dialogRef2: MdDialogRef<NavbarComponent>,
    private cookieService: CookieService,
    @Inject(MD_DIALOG_DATA) public data: any, private router: Router) { }

    ngOnInit(){
        this.userRole = JSON.parse(this.cookieService.get('currentUser'));
        this.roles = this.userRole.roles;
        for(let i = 0; i < this.roles.length; i++){
            if(this.roles[i].name == "Admin"){
                this.adminRole = true;
            }else if(this.roles[i].name == "Manager"){
                this.managerRole = true;
            }else if(this.roles[i].name == "Trainer"){
                this.trainerRole = true;
            }else{
                this.staffRole = true;
            }
        }
        console.log(this.roles);
    }

    toDashboard(role: string): void {
        this.userRole.roleActive = role;
        this.cookieService.put('currentUser', JSON.stringify(this.userRole));
        this.router.navigate(['/home/dashboard']);
        this.dialogRef.close();
        window.location.reload();
    }
}