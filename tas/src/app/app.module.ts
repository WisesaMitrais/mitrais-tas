import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { CookieService } from 'angular2-cookie/core';

import { MdCardModule, MdToolbarModule, MdInputModule, MdTableModule, MdAutocompleteModule,
  MdButtonModule, MdButtonToggleModule, MdCheckboxModule, MdChipsModule, MdCoreModule, MdDatepickerModule,
  MdDialogModule, MdExpansionModule, MdGridListModule, MdIconModule, MdListModule,
  MdMenuModule, MdNativeDateModule, MdPaginatorModule, MdProgressBarModule, MdProgressSpinnerModule,
  MdRadioModule, MdRippleModule, MdSelectModule, MdSidenavModule, MdSliderModule, 
  MdSlideToggleModule, MdSnackBarModule, MdSortModule, MdTabsModule, MdTooltipModule} from '@angular/material';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
import { AppComponent } from './app.component';
import { LoginAuth } from './authentication/authentication.component'
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

import { AlertService } from './services/alert.service';
import { LoginService } from './services/login.service';
import { DashboardService } from './services/dashboard.service';
import { PeriodService } from './services/period.service';
import { UserService } from './services/user.service';
import { EnrollmentService } from './services/enrollment.service';
import { AchievementService } from './services/achievement.service';
import { TrainingMaintenanceService } from './services/training-maintenance.service';
import { UrlService } from './services/url.service';
import { NotificationService } from './services/notification.service';

@NgModule({
  exports: [ MdAutocompleteModule, MdButtonModule, MdButtonToggleModule,
    MdCardModule, MdCheckboxModule, MdChipsModule, MdCoreModule, MdDatepickerModule,
    MdDialogModule, MdExpansionModule, MdGridListModule, MdIconModule, MdInputModule,
    MdListModule, MdMenuModule, MdNativeDateModule, MdPaginatorModule, MdProgressBarModule,
    MdProgressSpinnerModule, MdRadioModule, MdRippleModule, MdSelectModule, MdSidenavModule, MdSliderModule,
    MdSlideToggleModule, MdSnackBarModule, MdSortModule, MdTableModule, MdTabsModule, MdToolbarModule,
    MdTooltipModule]
})
export class MaterialModule {}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RoleComponent,
    HomeComponent,
    DashboardComponent,
    PeriodComponent,
    AddPeriodComponent,
    PeriodEligibleParticipantComponent,
    PeriodEligibleParticipantAddComponent,
    PeriodScheduleComponent,
    PeriodScheduleAddComponent,
    PeriodScheduleDetailsComponent,
    PeriodEnrollParticipantComponent,
    PeriodEnrollParticipantShowComponent,
    PeriodScheduleEditComponent,
    EditPeriodComponent,
    UserComponent,
    AddUserComponent,
    EditUserComponent,
    EnrollementComponent,
    AchievementComponent,
    AchievementDetailComponent,
    AchievementRepeatComponent,
    TrainingMaintenanceComponent,
    TrainingMaintenanceAssessmentComponent,
    TrainingMaintenanceAttendanceComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    NoopAnimationsModule
  ],
  providers: [
    LoginAuth,
    CookieService,
    AlertService,
    LoginService,
    UrlService,
    NotificationService,
    DashboardService,
    PeriodService,
    UserService,
    EnrollmentService,
    AchievementService,
    TrainingMaintenanceService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
