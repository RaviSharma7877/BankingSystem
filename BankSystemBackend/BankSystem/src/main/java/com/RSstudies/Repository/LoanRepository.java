package com.RSstudies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RSstudies.models.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

}
