import { Component, OnInit, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import {Location, LocationStrategy, PathLocationStrategy} from '@angular/common';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { RoleComponent } from '.../../app/login/role.component';
import { ROUTES } from '../sidebar/sidebar.component';

@Component({
  selector: 'tas-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    test : Date = new Date();
    private listTitles: any[];
    location: Location;
    private toggleButton: any;
    private sidebarVisible: boolean;

    constructor(location: Location,  private element: ElementRef, private router: Router, public dialog: MdDialog) {
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
        this.router.navigate(['/login']);
    }

    toChangeRole(): void{
        let dialogRef = this.dialog.open(RoleComponent, {
            width: '500px',
            height: '225px'
        });
    }
}
