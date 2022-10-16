import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ApplyloanComponent } from './applyloan/applyloan.component';
import { LoandetailsComponent } from './loandetails/loandetails.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { ExitComponent } from './exit/exit.component';
import { HttpClientModule } from '@angular/common/http';
import { PaymentScheduleComponent } from './payment-schedule/payment-schedule.component';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from './shared/material.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { HeaderComponent } from './header/header.component';
import { SidemenubarComponent } from './sidemenubar/sidemenubar.component';
import { ToastrModule } from 'ngx-toastr';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DashboardComponent,
    ApplyloanComponent,
    LoandetailsComponent,
    NotfoundComponent,
    ExitComponent,
    PaymentScheduleComponent,
    HeaderComponent,
    SidemenubarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MaterialModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 2000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
    Ng2SearchPipeModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
