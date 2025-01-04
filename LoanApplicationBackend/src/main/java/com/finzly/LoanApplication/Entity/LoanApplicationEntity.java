package com.finzly.LoanApplication.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "loan_application_entity")
public class LoanApplicationEntity {
@NotNull(message = "Entity shouldn't be null")
@NotFound
	LocalDate currentdDate = LocalDate.now();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String username;
	private int loanAmount;
	private int loanPeriod;
	private LocalDate tradeDate = LocalDate.now();
//	private LocalDate loanStartDate = LocalDate.now().plusDays(10);
	private LocalDate loanStartDate = LocalDate.now();
	private int loanStartDelay;
	private LocalDate maturityDate = LocalDate.now();
	private int paymentFrequency;
	private float interestRate;
	private int paymentSchedule;
	private String paymentTerm;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "f_key", referencedColumnName = "customerId")
	private List<PaymentScheduleEntity> paymentScheduleEntity = new ArrayList<>();

	public LoanApplicationEntity() {
	}

	public LoanApplicationEntity(String username, int loanAmount, int loanPeriod, LocalDate tradeDate,
			LocalDate loanStartDate, LocalDate maturityDate, int paymentFrequency, float interestRate,
			int paymentSchedule, String paymentTerm, List<PaymentScheduleEntity> paymentScheduleEntity) {
		super();
		this.username = username;
		this.loanAmount = loanAmount;
		this.loanPeriod = loanPeriod;
		this.tradeDate = tradeDate;
		this.loanStartDate = loanStartDate;
		this.maturityDate = maturityDate;
		this.paymentFrequency = paymentFrequency;
		this.interestRate = interestRate;
		this.paymentSchedule = paymentSchedule;
		this.paymentTerm = paymentTerm;
		this.paymentScheduleEntity = paymentScheduleEntity;
	}




	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public LocalDate getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(LocalDate loanStartDate) {
		this.loanStartDate = loanStartDate;
	}
	
	public int getLoanStartDelay() {
		return loanStartDelay;
	}

	public void setLoanStartDelay(int loanStartDelay) {
		this.loanStartDelay = loanStartDelay;
	}


	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public int getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(int paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public List<PaymentScheduleEntity> getPaymentScheduleEntity() {
		return paymentScheduleEntity;
	}

	public void setPaymentScheduleEntity(List<PaymentScheduleEntity> paymentScheduleEntity) {
		this.paymentScheduleEntity = paymentScheduleEntity;
	}

	public int getPaymentSchedule() {
		return paymentSchedule;
	}

	public void setPaymentSchedule(int paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "LoanApplicationEntity [customerId=" + customerId + ", username=" + username + ", loanAmount="
				+ loanAmount + ", loanPeriod=" + loanPeriod + ", tradeDate=" + tradeDate + ", loanStartDate="
				+ loanStartDate + ", loanStartDelay=" + loanStartDelay + ", maturityDate=" + maturityDate
				+ ", paymentFrequency=" + paymentFrequency + ", interestRate=" + interestRate + ", paymentSchedule="
				+ paymentSchedule + ", paymentTerm=" + paymentTerm + ", paymentScheduleEntity=" + paymentScheduleEntity
				+ "]";
	}
}
