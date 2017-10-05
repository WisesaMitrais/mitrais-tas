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
import { PeriodScheduleAddComponent } from './period/period-schedule-add.component';
import { PeriodScheduleDetailsComponent } from './period/period-schedule-details.component';
import { PeriodEnrollParticipantComponent } from './period/period-schedule-enrollparticipant.component';
import { PeriodEnrollParticipantShowComponent } from './period/period-schedule-enrollparticipant-show.component';
import { PeriodScheduleEditComponent } from './period/period-schedule-edit.component';
import { EditPeriodComponent } from './period/period-edit.component';
import { UserComponent } from './user/user.component';
import { AddUserComponent } from './user/user-add.component';
import { EditUserComponent } from './user/user-edit.component';
import { EnrollementComponent } from './enrollment/enrollment.component';
import { AchievementComponent } from './achievement/achievement.component';
import { AchievementDetailComponent } from './achievement/achievement-detail.component';
import { AchievementRepeatComponent } from './achievement/achievement-repeat.component';
import { TrainingMaintenanceComponent } from './training-maintenance/training-maintenance.component';
import { TrainingMaintenanceAssessmentComponent } from './training-maintenance/training-maintenance-assessment.component';
import { TrainingMaintenanceAttendanceComponent } from './training-maintenance/training-maintenance-attendance.component';

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
          { path: 'add', component: PeriodScheduleAddComponent },
          { path: 'details', component: PeriodScheduleDetailsComponent },
          { path: 'enroll-participant', component: PeriodEnrollParticipantComponent },
          { path: 'enroll-participant-show', component: PeriodEnrollParticipantShowComponent },
          { path: 'edit', component: PeriodScheduleEditComponent }
        ]},
        { path: 'edit', component: EditPeriodComponent }
      ]},
      { path: 'user', component: UserComponent, children: [
        { path: 'add', component: AddUserComponent },
        { path: 'edit', component: EditUserComponent }
      ]},
      { path: 'enrollment', component: EnrollementComponent },
      { path: 'achievement', component: AchievementComponent, children: [
        { path: 'details', component: AchievementDetailComponent },
        { path: 'repeat-history', component: AchievementRepeatComponent }
      ]},
      { path: 'training', component: TrainingMaintenanceComponent, children: [
        { path: 'attendance', component: TrainingMaintenanceAttendanceComponent },
        { path: 'assessment', component: TrainingMaintenanceAssessmentComponent }
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