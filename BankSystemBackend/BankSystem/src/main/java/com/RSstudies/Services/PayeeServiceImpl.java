package com.RSstudies.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RSstudies.Exceptions.InsufficientBalanceException;
import com.RSstudies.Exceptions.InvalidLoanAmountException;
import com.RSstudies.Exceptions.NoDataFoundException;
import com.RSstudies.Repository.AccountRepository;
import com.RSstudies.Repository.PayeeRepository;
import com.RSstudies.models.Account;
import com.RSstudies.models.Loan;
import com.RSstudies.models.Payee;
@Service
public class PayeeServiceImpl implements PayeeService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private PayeeRepository payeeRepo;

	@Override
	public String addPayee(String accountNumber, double amount) throws InvalidLoanAmountException, NoDataFoundException, InsufficientBalanceException {
	    Account acc = accountRepo.findByAccountNumber(accountNumber)
	                              .orElseThrow(() -> new NoDataFoundException("Account not found with account number: " + accountNumber));

	    // Check if the balance is sufficient for the payment
	    if (acc.getBalance() < amount) {
	        throw new InsufficientBalanceException("Insufficient balance in account");
	    }

	    // Update the balance of the account
	    double newBalance = acc.getBalance() - amount;
	    acc.setBalance(newBalance);

	    // Update the loan amount if present
	    Loan loan = acc.getLoans();
	    if (loan != null) {
	        double loanAmount = loan.getAmount();
	        if (loanAmount >= amount) {
	            loan.setAmount(loanAmount - amount);
	        } else {
	            throw new InvalidLoanAmountException("Loan amount is less than the payment amount");
	        }
	    } else {
	        throw new NoDataFoundException("No loan found for account");
	    }

	    // Save the updated account and payee
	    

	    Payee payee = new Payee();
	    payee.setAccount(acc);
	    payee.setAmount(amount);
	    payeeRepo.save(payee);
	    acc.setPayees(payee);
	    accountRepo.save(acc);
	    return "Payee added successfully";
	}



	@Override
	public Payee getPayee(Account account) {
		Account acc = accountRepo.findById(account.getAccountId()).orElseThrow();
		return acc.getPayees();
	}

}
