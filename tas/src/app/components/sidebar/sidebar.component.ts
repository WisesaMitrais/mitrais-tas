import { Component, OnInit } from '@angular/core';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: 'dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
    { path: 'period', title: 'Period',  icon:'date_range', class: '' },
    { path: 'user', title: 'User',  icon:'person', class: '' },
    { path: 'enrollment', title: 'Enrollment',  icon:'assignment_turned_in', class: '' },
    { path: 'achievement', title: 'Achievement',  icon:'school', class: '' },
    { path: 'training', title: 'Training Maintenance',  icon:'settings', class: '' }
];

@Component({
  selector: 'tas-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

}
