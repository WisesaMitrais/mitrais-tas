import { Component, OnInit, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'angular2-cookie/core';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { RoleComponent } from '.../../app/login/role.component';
import { ROUTES } from '../sidebar/sidebar.component';

@Component({
  selector: 'tas-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    private listTitles: any[];
    location: Location;
    private toggleButton: any;
    private sidebarVisible: boolean;
    userRole;

    constructor(location: Location,  
        private element: ElementRef, 
        private router: Router, 
        public dialog: MdDialog, 
        public cookieService: CookieService) {
        this.location = location;
        this.sidebarVisible = false;
    }

    ngOnInit(){
      this.listTitles = ROUTES.filter(listTitle => listTitle);
      const navbar: HTMLElement = this.element.nativeElement;
      this.toggleButton = navbar.getElementsByClassName('navbar-toggle')[0];
    }

    getTitle(){
      var titlee = this.location.prepareExternalUrl(this.location.path());
      if(titlee.charAt(0) === '#'){
          titlee = titlee.slice( 2 );
      }
      titlee = titlee.split('/').pop();

      for(var item = 0; item < this.listTitles.length; item++){
          if(this.listTitles[item].path === titlee){
              return this.listTitles[item].title;
          }
      }
      return 'Dashboard';
    }

    toLogin(){
        this.userRole = JSON.parse(this.cookieService.get('currentUser'));
        this.userRole.roleActive = 'none';
        this.cookieService.remove('currentUser');
        this.router.navigate(['/login']);
    }

    toDashboard(){
        this.router.navigate(['/home/dashboard']);
    }

    toChangeRole(): void{
        let dialogRef = this.dialog.open(RoleComponent, {
            width: '500px',
            height: '225px'
        });
    }
}
