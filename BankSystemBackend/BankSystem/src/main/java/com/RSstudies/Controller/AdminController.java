package com.RSstudies.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RSstudies.Services.AccountService;
import com.RSstudies.Services.LoanService;
import com.RSstudies.Services.PayeeService;
import com.RSstudies.Services.TransactionService;
import com.RSstudies.Services.UsersService;
import com.RSstudies.models.Account;
import com.RSstudies.models.Loan;
import com.RSstudies.models.Transaction;
import com.RSstudies.models.Users;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private PayeeService payeeService;
	
	
	@Autowired
	private LoanService loanService;
	
	
	
	
//	Users
	@GetMapping("/users/list")
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<List<Users>>(userService.getAllUsers(),HttpStatus.OK);
	}
	@GetMapping("/users/userId")
	public ResponseEntity<Users> getUserByUserId(int id) {
		return new ResponseEntity<Users>(userService.getUserByUserId(id), HttpStatus.OK);
	}
	
	
	
	
	
//	Account
	@GetMapping("/accounts/accountId")
	public ResponseEntity<Account> getAccountByAccountId(@RequestParam int accountId) {
		return new ResponseEntity<Account>(accountService.getAccountById(accountId),HttpStatus.OK);
	}
	@GetMapping("/accounts/list")
	public ResponseEntity<List<Account>> getAllAccount() {
		return new ResponseEntity<List<Account>>(accountService.getAllAccount(),HttpStatus.OK);
	}
	@PostMapping("/accounts/set-account-status")
	public ResponseEntity<String> updateAccountStatus(int accountId, String status) {
		return new ResponseEntity<String>(accountService.updateAccountStatus(accountId, status), HttpStatus.OK);
	}
	
	
	
	
//	Transactions
	@GetMapping("/transaction/list")
	public ResponseEntity<List<Transaction>> getAllTransaction() {
		return new ResponseEntity<List<Transaction>>(transactionService.getAllTransaction(),HttpStatus.OK);
	}
	
	
	
	
//	Loan
	@GetMapping("/transaction/list-loan")
	public ResponseEntity<Loan> getAllLoans(Account account) {
		return new ResponseEntity<Loan>(loanService.getLoans(account), HttpStatus.OK);
	}
	@PostMapping("/transaction/set-role-status")
	public ResponseEntity<String> updateLoanStatus(int loanId, String loanStatus) {
		return new ResponseEntity<String>(loanService.updateLoanStatus(loanId,loanStatus), HttpStatus.OK);
	}
}
