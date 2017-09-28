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
import { UserComponent } from './user/user.component';

import { AlertService } from './services/alert.service';
import { LoginService } from './services/login.service';
import { DashboardService } from './services/dashboard.service';
import { PeriodService } from './services/period.service';
import { UserService } from './services/user.service';
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
    UserComponent
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
    UserService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
