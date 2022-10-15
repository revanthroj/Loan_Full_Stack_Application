import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApplyloanComponent } from './applyloan/applyloan.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ExitComponent } from './exit/exit.component';
import { HomeComponent } from './home/home.component';
import { LoandetailsComponent } from './loandetails/loandetails.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { PaymentScheduleComponent } from './payment-schedule/payment-schedule.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'DashBoard',
    component: DashboardComponent,
  },
  {
    path: 'ApplyLoan',
    component: ApplyloanComponent,
  },
  {
    path: 'PaymentSchedule',
    component: PaymentScheduleComponent,
  },
  {
    path: 'LoanDetails',
    component: LoandetailsComponent,
  },
  {
    path: 'Exit',
    component: ExitComponent,
  },
  {
    path: '**',
    component: NotfoundComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
