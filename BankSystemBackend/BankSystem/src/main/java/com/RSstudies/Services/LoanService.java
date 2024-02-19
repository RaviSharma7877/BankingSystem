package com.RSstudies.Services;

import java.util.List;

import com.RSstudies.models.Account;
import com.RSstudies.models.Loan;

public interface LoanService {
	String applyLoan(String account, double amount);
	String updateLoanStatus(int loanId, String loanStatus);
	Loan getLoans(Account account);
	List<Loan> getAllLoans();
}
