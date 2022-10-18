import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-applyloan',
  templateUrl: './applyloan.component.html',
  styleUrls: ['./applyloan.component.css'],
})
export class ApplyloanComponent implements OnInit {
  amount_pattern: any = /^[.0-9]+$/;
  interestPattern: any = /^[1-9]+([.0-9])*$/;
  name_pattern: any = /^[a-zA-z]+([\s.][a-zA-Z]+)*$/;

  loanPeriodSelect: number[] = [12, 24, 36, 48, 60, 72];
  paymentTermSelect: string[] = ['Interest Only', 'Even Principle'];
  paymentFreq: number[] = [1, 3, 6, 12];

  constructor(
    private service: HttpService,
    private router: Router,
    private toaster: ToastrService
  ) {}

  ngOnInit(): void {}

  onApply(f: NgForm) {
    let obj = {
      username: f.value.username,
      loanAmount: f.value.laonAmount,
      loanPeriod: f.value.loanPeriod,
      loanStartDelay: f.value.loanStartDelay,
      paymentFrequency: f.value.paymentFrequency,
      interestRate: f.value.interestRate,
      paymentTerm: f.value.paymentTerm,
    };
    this.service.postRecord(obj).subscribe(
      (response) => {
        this.toaster.success('Loan Applied');
      },
      (error) => {
        this.toaster.warning('Loan Not Applied');
      }
    );
  }
}
