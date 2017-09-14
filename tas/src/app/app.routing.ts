import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { LoginAuth } from './authentication/authentication.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PeriodComponent, AddPeriodComponent } from './period/period.component';

const routes: Routes =[
  { path: '', redirectTo:'home/dashboard', pathMatch: 'full', canActivate: [LoginAuth] },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'period', component: PeriodComponent, children: [
        { path: 'add', component: AddPeriodComponent }
      ]}
  ]}
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})
export class AppRoutingModule { }