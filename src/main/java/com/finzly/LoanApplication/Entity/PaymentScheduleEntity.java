package com.finzly.LoanApplication.Entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.finzly.LoanApplication.paymentStatusEnum.PaymentStatus;


@Entity
public class PaymentScheduleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	private String username;
	private LocalDate paymentDate;
	private float principal;
	private float projectedInterest;
	private PaymentStatus paymentStatus;
	private double paymentAmount;	

	public PaymentScheduleEntity() {
		super();
		//TODO Auto-generated constructor stub
	}

	public PaymentScheduleEntity(String username, LocalDate paymentDate, float principal, float projectedInterest,
			PaymentStatus paymentStatus, double paymentAmount) {
		super();
		this.username = username;
		this.paymentDate = paymentDate;
		this.principal = principal;
		this.projectedInterest = projectedInterest;
		this.paymentStatus = paymentStatus;
		this.paymentAmount = paymentAmount;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}


	public LocalDate getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}


	public float getPrincipal() {
		return principal;
	}


	public void setPrincipal(float principal) {
		this.principal = principal;
	}


	public float getProjectedInterest() {
		return projectedInterest;
	}


	public void setProjectedInterest(float projectedInterest) {
		this.projectedInterest = projectedInterest;
	}


	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Override
	public String toString() {
		return "PaymentScheduleEntity [paymentId=" + paymentId + ", username=" + username + ", paymentDate="
				+ paymentDate + ", principal=" + principal + ", projectedInterest=" + projectedInterest
				+ ", paymentStatus=" + paymentStatus + ", paymentAmount=" + paymentAmount + "]";
	}
		
	
}
