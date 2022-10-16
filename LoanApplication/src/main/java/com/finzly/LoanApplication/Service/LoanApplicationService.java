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
		LocalDate paymentInitiateDate = LocalDate.now();
		float principal = obj.getLoanAmount();
		float interest = 0;
		PaymentScheduleEntity data = null;
		List<PaymentScheduleEntity> paymentScheduleList = loan.getPaymentScheduleEntity();
		
		LocalDate paymentDate = obj.getLoanStartDate();
		int paymentSchedule = (int) Math.ceil((double)obj.getLoanPeriod() / (double)obj.getPaymentFrequency());
		int payFreq = obj.getPaymentFrequency();
		interest = ((principal * obj.getInterestRate()) / 100)*payFreq;
		double amount = interest;
		int i=0;
		float new_principal = principal;
		float evenPrincipal = obj.getLoanAmount() / paymentSchedule;
		float projectedInterest  = ((new_principal * obj.getInterestRate()) / 100)*payFreq;
		
		while (loan.getMaturityDate().compareTo(paymentDate) > 0) {
			int count = i+1;
			if (obj.getPaymentTerm().equalsIgnoreCase("Even Principle")) {
				if(paymentDate.compareTo(maturityDate)>0) {
					paymentDate = maturityDate;
				}
				new_principal = evenPrincipal + projectedInterest;
				paymentDate = paymentDate.plusMonths(obj.getPaymentFrequency());
				data = new PaymentScheduleEntity(obj.getUsername(),paymentDate, evenPrincipal, projectedInterest, paymentStaus(paymentInitiateDate, paymentDate),
						new_principal);
				
				principal -= evenPrincipal;
				projectedInterest = ((principal * obj.getInterestRate()) / 100)*payFreq;
				paymentScheduleList.add(data);	
			} 
			else if (obj.getPaymentTerm().equalsIgnoreCase("Interest Only")) {
				paymentDate = paymentDate.plusMonths(obj.getPaymentFrequency());
				if(paymentDate.compareTo(maturityDate)>0) {
					paymentDate = maturityDate;
				}
				if (loan.getMaturityDate().compareTo(paymentDate) == 0)
					amount = principal + interest;
				data = new PaymentScheduleEntity(obj.getUsername(),paymentDate, principal, interest, paymentStaus(paymentInitiateDate, paymentDate), amount);
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
	////
}
