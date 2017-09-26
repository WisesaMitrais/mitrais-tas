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
import { PeriodEligibleParticipantComponent } from './period/period-eligibleparticipant.component';
import { PeriodEligibleParticipantAddComponent } from './period/period-eligibleparticipant-add.component';
import { PeriodScheduleComponent } from './period/period-schedule.component';
import { UserComponent } from './user/user.component';

const routes: Routes =[
  { path: '', redirectTo:'home/dashboard', pathMatch: 'full', canActivate: [LoginAuth] },
  { path: 'login', component: LoginComponent },
  { path: 'role', component: RoleComponent },
  { path: 'home', component: HomeComponent, children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'period', component: PeriodComponent, children: [
        { path: 'add', component: AddPeriodComponent },
        { path: 'eligible-participant', component: PeriodEligibleParticipantComponent, children: [
          { path: 'add', component: PeriodEligibleParticipantAddComponent }
        ]},
        { path: 'schedule-list', component: PeriodScheduleComponent, children: [
          { path: 'details', component: PeriodComponent },
          { path: 'enroll-participant', component: PeriodComponent }
        ]},
        { path: 'edit', component: PeriodComponent }
      ]},
      { path: 'user', component: UserComponent, children: [
        { path: 'add', component: PeriodComponent },
        { path: 'edit', component: PeriodComponent }
      ]},
      { path: 'enrollment', component: PeriodComponent, children: [
        { path: 'details', component: PeriodComponent }
      ]},
      { path: 'achievement', component: PeriodComponent, children: [
        { path: 'edit', component: PeriodComponent },
        { path: 'details', component: PeriodComponent }
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