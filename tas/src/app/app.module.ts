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
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PeriodComponent, AddPeriodComponent } from './period/period.component';

import { AuthenticationService } from './services/authentication.service';
import { AlertService } from './services/alert.service';

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
    HomeComponent,
    DashboardComponent,
    PeriodComponent,
    AddPeriodComponent,
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
    AuthenticationService,
    AlertService,
  ],
  bootstrap: [
    AppComponent,
  ]
})
export class AppModule { }
