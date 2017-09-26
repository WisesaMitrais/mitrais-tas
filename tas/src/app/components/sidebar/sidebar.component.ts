import { Component, OnInit, ViewChild } from '@angular/core';
import { CookieService } from 'angular2-cookie/core';
import { Router } from '@angular/router';
import { MdSidenav } from '@angular/material';

import { NotificationService } from '.../../app/services/notification.service';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}

const menuAdmin: RouteInfo[] = [
  { path: 'dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
  { path: 'period', title: 'Period',  icon:'date_range', class: '' },
  { path: 'user', title: 'User',  icon:'person', class: '' },
  { path: 'enrollment', title: 'Enrollment',  icon:'assignment_turned_in', class: '' },
  { path: 'achievement', title: 'Achievement',  icon:'school', class: '' },
  { path: 'training', title: 'Training Maintenance',  icon:'settings', class: '' }
];

const menuTrainer: RouteInfo[] = [
  { path: 'dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
  { path: 'period', title: 'Period',  icon:'date_range', class: '' },
  { path: 'enrollment', title: 'Enrollment',  icon:'assignment_turned_in', class: '' },
  { path: 'achievement', title: 'Achievement',  icon:'school', class: '' },
  { path: 'training', title: 'Training Maintenance',  icon:'settings', class: '' }
];

const menuManager: RouteInfo[] = [
  { path: 'dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
  { path: 'period', title: 'Period',  icon:'date_range', class: '' },
  { path: 'enrollment', title: 'Enrollment',  icon:'assignment_turned_in', class: '' },
  { path: 'achievement', title: 'Achievement',  icon:'school', class: '' }
];

const menuStaff: RouteInfo[] = [
  { path: 'dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
  { path: 'period', title: 'Period',  icon:'date_range', class: '' },
  { path: 'enrollment', title: 'Enrollment',  icon:'assignment_turned_in', class: '' },
  { path: 'achievement', title: 'Achievement',  icon:'school', class: '' }
];

export var ROUTES: RouteInfo[] = menuAdmin;

@Component({
  selector: 'tas-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  currentUser;
  
  @ViewChild('sidenav') public sideNav: MdSidenav;

  constructor(private cookieService: CookieService,
    private notificationService: NotificationService,
    private router: Router) { }

  ngOnInit() {
    if (this.cookieService.get('currentUser')){
      this.currentUser = JSON.parse(this.cookieService.get('currentUser'));
    } else { 
      this.router.navigate(['/login']);
      this.notificationService.setNotificationInfo('Your are not logged in');
    };

    if(this.currentUser.roleActive == "admin"){
      ROUTES = menuAdmin;
    }else if(this.currentUser.roleActive == "manager"){
      ROUTES = menuManager;
    }else if(this.currentUser.roleActive == "trainer"){
      ROUTES = menuTrainer;
    }else{
      ROUTES = menuStaff;
    }
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

  closeSideNav(){
    this.sideNav.close();
  }

}