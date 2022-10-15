import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  isChecked = false;

  constructor(private router: Router) {}

  ngOnInit(): void {}

  toggle() {
    if (!this.isChecked) {
      console.log('yes');
      this.isChecked = true;
    } else {
      console.log('no');
      this.isChecked = false;
    }
  }
  applyLaon() {
    this.router.navigate(['/ApplyLoan']);
  }
  loanDetails() {
    this.router.navigate(['/LoanDetails']);
  }
  onExit() {
    this.router.navigate(['/Exit']);
  }
}
