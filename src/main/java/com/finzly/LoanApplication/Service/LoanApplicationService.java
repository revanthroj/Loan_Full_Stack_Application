package com.finzly.LoanApplication.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.finzly.LoanApplication.Dao.LoanApplicationDao;
import com.finzly.LoanApplication.Dao.PaymentScheduleDao;
import com.finzly.LoanApplication.Entity.LoanApplicationEntity;
import com.finzly.LoanApplication.Entity.PaymentScheduleEntity;
import com.finzly.LoanApplication.paymentStatusEnum.PaymentStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class LoanApplicationService {

	@Autowired
	private LoanApplicationDao loanDao;
	@Autowired
	private PaymentScheduleDao paymentDao;

	public List<LoanApplicationEntity> displayLoan() {
		return loanDao.findAll();
	}


	public void applyLoan(LoanApplicationEntity obj) {
		LoanApplicationEntity loan = new LoanApplicationEntity();
		PaymentScheduleEntity paymentObj = new PaymentScheduleEntity();
		loan.setLoanAmount(obj.getLoanAmount());
		loan.setUsername(obj.getUsername());
		loan.setLoanPeriod(obj.getLoanPeriod());
		loan.setTradeDate(obj.getTradeDate());
		loan.setLoanStartDate(obj.getLoanStartDate());
		LocalDate maturityDate = obj.getLoanStartDate().plusMonths(obj.getLoanPeriod());
		loan.setMaturityDate(maturityDate);
		loan.setPaymentFrequency(obj.getPaymentFrequency());
		loan.setInterestRate(obj.getInterestRate());
		loan.setPaymentSchedule((int) Math.ceil((double)obj.getLoanPeriod() / (double)obj.getPaymentFrequency()));
		loan.setPaymentTerm(obj.getPaymentTerm());
//		LocalDate paymentInitiateDate = LocalDate.now();
		float principal = obj.getLoanAmount();
		float interest = 0;
		PaymentScheduleEntity data = null;
		List<PaymentScheduleEntity> paymentScheduleList = loan.getPaymentScheduleEntity();
		
		LocalDate paymentDate = obj.getLoanStartDate();
		int paymentSchedule = (int) Math.ceil((double)obj.getLoanPeriod() / (double)obj.getPaymentFrequency());
		int payFreq = obj.getPaymentFrequency();
		interest = (principal * (payFreq) * obj.getInterestRate()) / 100;
		double amount = interest;
		int i=0;
		float new_principal = 0;
		double evenPrincipal = obj.getLoanAmount() / paymentSchedule;
		while (loan.getMaturityDate().compareTo(paymentDate) > 0) {
			int count = i+1;
			if (obj.getPaymentTerm().equalsIgnoreCase("Even Principle")) {
				if(count == 1) {
				new_principal = principal;
				}
				double temp_evenPrincipal = (new_principal + interest);
				paymentDate = paymentDate.plusMonths(obj.getPaymentFrequency());
				if(paymentDate.compareTo(maturityDate)>0) {
					paymentDate = maturityDate;
				}
				data = new PaymentScheduleEntity(obj.getUsername(),paymentDate, new_principal, interest, PaymentStatus.Projected,
						temp_evenPrincipal);
				principal -= evenPrincipal;
				new_principal = (principal/paymentSchedule)+interest;
				interest = (principal * (payFreq) * obj.getInterestRate()) / 100;
				paymentScheduleList.add(data);	
			} 
			else if (obj.getPaymentTerm().equalsIgnoreCase("Interest Only")) {
				paymentDate = paymentDate.plusMonths(obj.getPaymentFrequency());
				if(paymentDate.compareTo(maturityDate)>0) {
					paymentDate = maturityDate;
				}
				if (loan.getMaturityDate().compareTo(paymentDate) == 0)
					amount = principal + interest;
				data = new PaymentScheduleEntity(obj.getUsername(),paymentDate, principal, interest, PaymentStatus.Projected, amount);
				paymentScheduleList.add(data);
			}
			loan.setPaymentScheduleEntity(paymentScheduleList);
			System.out.println(paymentScheduleList);
			loanDao.save(loan);
		}
	}
	
	public PaymentStatus paymentStaus(LocalDate paymentInitiateDate,LocalDate paymentDate) {
		PaymentStatus status = null;
		if(paymentInitiateDate.compareTo(paymentDate)<0) {
			status = PaymentStatus.Projected;
			return status;
		}
		else if (paymentInitiateDate.compareTo(paymentDate) == 0) {
			status = PaymentStatus.AwaitingPayment;
			return status;
		}
//		else if (paymentInitiateDate.compareTo(paymentDate) > 0) {
//			status = PaymentStatus.Paid;
//			return status;
//		}
		return null;
	}

	public List<PaymentScheduleEntity> displayPaymentSchedule() {
		return paymentDao.findAll();
	}

	public List<PaymentScheduleEntity> paidStatus(String name) {
		return paymentDao.findByUsername(name);
	}


	public List<PaymentScheduleEntity> paid(String name) {
		List<PaymentScheduleEntity> paystatus = paymentDao.findByUsername(name);
		List<PaymentScheduleEntity> list = new ArrayList<>();
		System.out.println(paystatus);
		LocalDate currentDate = LocalDate.now();
		System.out.println(paystatus);
		for (PaymentScheduleEntity paymentScheduleEntity : paystatus) {
			if(paymentScheduleEntity.getPaymentDate().compareTo(currentDate) == 0) {
				paymentScheduleEntity.setPaymentAmount(paymentScheduleEntity.getPaymentAmount());
				paymentScheduleEntity.setPaymentDate(paymentScheduleEntity.getPaymentDate());
				paymentScheduleEntity.setPaymentStatus(PaymentStatus.Paid);
				paymentScheduleEntity.setProjectedInterest(paymentScheduleEntity.getProjectedInterest());
				paymentScheduleEntity.setPrincipal(paymentScheduleEntity.getPrincipal());
				paymentScheduleEntity.setUsername(paymentScheduleEntity.getUsername());
				System.out.println(paymentScheduleEntity.getPaymentAmount());
//				System.out.println(paymentDao.save(paymentScheduleEntity));
				list.add(paymentDao.save(paymentScheduleEntity));
			}
			else if(paymentScheduleEntity.getPaymentDate().compareTo(currentDate) < 0 ) {
				paymentScheduleEntity.setPaymentAmount(paymentScheduleEntity.getPaymentAmount());
				paymentScheduleEntity.setPaymentDate(paymentScheduleEntity.getPaymentDate());
				paymentScheduleEntity.setPaymentStatus(PaymentStatus.Projected);
				paymentScheduleEntity.setProjectedInterest(paymentScheduleEntity.getProjectedInterest());
				paymentScheduleEntity.setPrincipal(paymentScheduleEntity.getPrincipal());
				paymentScheduleEntity.setUsername(paymentScheduleEntity.getUsername());
				System.out.println(paymentScheduleEntity.getPaymentAmount());
//				System.out.println(paymentDao.save(paymentScheduleEntity));
				list.add(paymentDao.save(paymentScheduleEntity));
			}
			else if(paymentScheduleEntity.getPaymentDate().compareTo(currentDate) > 0 ) {
				paymentScheduleEntity.setPaymentAmount(paymentScheduleEntity.getPaymentAmount());
				paymentScheduleEntity.setPaymentDate(paymentScheduleEntity.getPaymentDate());
				paymentScheduleEntity.setPaymentStatus(PaymentStatus.Projected);
				paymentScheduleEntity.setProjectedInterest(paymentScheduleEntity.getProjectedInterest());
				paymentScheduleEntity.setPrincipal(paymentScheduleEntity.getPrincipal());
				paymentScheduleEntity.setUsername(paymentScheduleEntity.getUsername());
				System.out.println(paymentScheduleEntity.getPaymentAmount());
//				System.out.println(paymentDao.save(paymentScheduleEntity));
				list.add(paymentDao.save(paymentScheduleEntity));
			}
			
		}
		return list;
	}
	
//	public PaymentScheduleEntity paid(PaymentScheduleEntity obj) {
//		List<PaymentScheduleEntity> payment_schedule = paymentDao.findByUsername(obj.getUsername());
////		PaymentScheduleEntity paymentObj = new PaymentScheduleEntity();
//		LocalDate currentDate = LocalDate.now();
//		LocalDate paymentInitDate = LocalDate.now().plusDays(10).plusMonths(3);
//		for (PaymentScheduleEntity paymentList : payment_schedule) {
//			obj.setUsername(paymentList.getUsername());
//			obj.setPaymentDate(paymentList.getPaymentDate());
//			obj.setPrincipal(paymentList.getPrincipal());
//			obj.setProjectedInterest(paymentList.getProjectedInterest());
//			obj.setPaymentAmount(paymentList.getPaymentAmount());
//			
//			if(paymentInitDate.compareTo(currentDate)<0) {
//				obj.setPaymentStatus(PaymentStatus.Projected);
//			}
//			else if(paymentInitDate.compareTo(currentDate) == 0) {
//				obj.setPaymentStatus(PaymentStatus.Paid);
//			}
//			else if(paymentInitDate.compareTo(currentDate) > 0) {
//				obj.setPaymentStatus(PaymentStatus.AwaitingPayment);
//			}
//			System.out.println(obj);
//			System.out.println(obj);
//			paymentDao.save(obj);
//		}
//		
//		return obj;
//	}
}
