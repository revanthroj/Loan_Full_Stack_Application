import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../http.service';
import { PaymentScheduleComponent } from '../payment-schedule/payment-schedule.component';

@Component({
  selector: 'app-loandetails',
  templateUrl: './loandetails.component.html',
  styleUrls: ['./loandetails.component.css'],
})
export class LoandetailsComponent implements OnInit {
  username: string = '';
  arObj: any[] = [];
  tobj: any[] = [];

  p: number = 1;
  constructor(private router: Router, private service: HttpService) {}
  PaymentScheduleComponent: any;
  ngOnInit(): void {
    this.getLoanDetails();
  }
  onPaymentSchedule() {
    // this.username = name;
    // this.service.getPaymentDue(name).subscribe((res: any) => {
    //   this.tobj = res;
    //   // console.log(this.tobj);
    //   // this.event.emit(this.tobj);
    // });
    this.router.navigate(['/PaymentSchedule']);
  }

  getLoanDetails() {
    this.service.getLoan().subscribe((res: any) => {
      this.arObj = res;
      // console.log(res);
    });
  }
}
