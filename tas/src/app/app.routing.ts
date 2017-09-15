import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { LoginAuth } from './authentication/authentication.component';
import { LoginComponent } from './login/login.component';
import { RoleComponent } from './login/role.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PeriodComponent } from './period/period.component';
import { AddPeriodComponent } from './period/period-add.component';
import { UserComponent } from './user/user.component';

const routes: Routes =[
  { path: '', redirectTo:'home/dashboard', pathMatch: 'full', canActivate: [LoginAuth] },
  { path: 'login', component: LoginComponent },
  { path: 'role', component: RoleComponent },
  { path: 'home', component: HomeComponent, children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'period', component: PeriodComponent, children: [
        { path: 'add', component: AddPeriodComponent }
      ]},
      { path: 'user', component: UserComponent }
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