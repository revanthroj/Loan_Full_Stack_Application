package com.finzly.LoanApplication.Dao;

import com.finzly.LoanApplication.Entity.LoanApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationDao extends JpaRepository<LoanApplicationEntity, Integer> {

}
