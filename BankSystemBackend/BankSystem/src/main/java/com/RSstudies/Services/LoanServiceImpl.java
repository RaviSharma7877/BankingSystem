package com.RSstudies.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RSstudies.Repository.AccountRepository;
import com.RSstudies.Repository.LoanRepository;
import com.RSstudies.models.Account;
import com.RSstudies.models.Loan;
@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private AccountRepository accountRepo;

	@Override
	public String applyLoan(String accountNumber, double amount) {
	    // Check if the account is eligible for a loan
		Account account = accountRepo.findByAccountNumber(accountNumber).orElseThrow();
	    if (account != null) {
	        // Reduce the account balance by the loan amount
	        account.setBalance(account.getBalance() + amount);
	        
	        // Create a new Loan object
	        Loan loan = new Loan();
	        loan.setAccount(account);
	        loan.setAmount(amount);
	        loan.setStatus("APPROVED");
	        loan.setCreatedAt(LocalDateTime.now());
	        loan.setUpdatedAt(LocalDateTime.now());
	        
	        account.setLoans(loan);
	        loanRepository.save(loan);
	        
	        
	        return "Loan application approved. Your loan application ID is: " + loan.getLoanApplicationId();
	    } else {
	        return "Loan application rejected due to insufficient balance.";
	    }
	}


	@Override
	public String updateLoanStatus(int loanId, String loanStatus) {
		Loan loan  = loanRepository.findById(loanId).orElseThrow();
		loan.setStatus(loanStatus);
		return "Loan Status updated successfully";
	}

	@Override
	public Loan getLoans(Account account) {
		Account acc = accountRepo.findById(account.getAccountId()).orElseThrow();
		return acc.getLoans();
	}
	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		return loanRepository.findAll();
	}

}
