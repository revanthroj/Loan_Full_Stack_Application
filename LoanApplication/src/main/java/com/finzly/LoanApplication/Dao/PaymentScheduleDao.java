package com.finzly.LoanApplication.Dao;

import java.util.List;

import com.finzly.LoanApplication.Entity.PaymentScheduleEntity;
import com.finzly.LoanApplication.paymentStatusEnum.PaymentStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentScheduleDao extends JpaRepository<PaymentScheduleEntity, Integer> {

//	List<PaymentScheduleEntity> findById(int id);
	List<PaymentScheduleEntity> findByUsername(String name);

	List<PaymentScheduleEntity> findByPaymentStatus(PaymentStatus paymentStatus);

}

