import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root',
})
export class HttpService {
  Url: string = 'http://localhost:8080/';
  constructor(private http: HttpClient) {}

  getLoan() {
    return this.http.get(`${this.Url}displayLoan`);
  }
  getPaymentSchedule() {
    return this.http.get(`${this.Url}displayPaymentSchedule`);
  }
  postRecord(obj: any) {
    return this.http.post(`${this.Url}applyLoan`, obj);
  }
  getPaymentDue(obj: any) {
    // console.log(obj);
    return this.http.get(`${this.Url}paidStatus/` + obj);
  }
  getPaymentSyayus(obj: any) {
    // console.log(obj);
    return this.http.get(`${this.Url}paid/` + obj);
  }
}
