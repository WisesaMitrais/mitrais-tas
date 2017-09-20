import { Component, OnInit } from '@angular/core';
import { CookieService } from 'angular2-cookie/core';
import { Router } from '@angular/router';

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

declare var $: any;

@Component({
  selector: 'tas-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  currentUser;

  constructor(private cookieService: CookieService,
    private router: Router) { }

  ngOnInit() {
    if (this.cookieService.get('currentUser')){
      this.currentUser = JSON.parse(this.cookieService.get('currentUser'));
    } else { 
      this.router.navigate(['/login']) 
      $.notify({
          icon: "notifications",
          message: "<b>Information</b> - Your are not logged in"
      },{
          type: 'info',
          timer: 3000,
          placement: {
              from: 'bottom',
              align: 'center'
          }
      });
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

}