import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../http.service';
import { LoandetailsComponent } from '../loandetails/loandetails.component';

@Component({
  selector: 'app-payment-schedule',
  templateUrl: './payment-schedule.component.html',
  styleUrls: ['./payment-schedule.component.css'],
})
export class PaymentScheduleComponent implements OnInit {
  arObj: any[] = [];
  tobj: any[] = [];
  p: number = 1;
  username: any = 'Revanth';

  constructor(private service: HttpService) {}

  ngOnInit(): void {
    this.getpaymentDetails();
  }

  getLoanDetails() {
    this.service.getPaymentSchedule().subscribe((res: any) => {
      this.arObj = res;
      // console.log(res.status);
      // console.log(this.arObj);
    });
  }
  getpaymentDetails() {
    this.service.getPaymentSchedule().subscribe((res: any) => {
      // console.log(this.username);
      this.tobj = res;
      console.log(this.tobj);
    });
  }
  onPay(name: string) {
    this.username = name;
    this.service.getPaymentDue(this.username).subscribe((res: any) => {
      this.tobj = res;
      console.log(this.tobj);
    });
  }
}
