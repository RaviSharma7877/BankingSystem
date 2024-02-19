package com.RSstudies.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RSstudies.Exceptions.InsufficientBalanceException;
import com.RSstudies.Exceptions.InvalidLoanAmountException;
import com.RSstudies.Exceptions.NoDataFoundException;
import com.RSstudies.Services.AccountService;
import com.RSstudies.Services.LoanService;
import com.RSstudies.Services.PayeeService;
import com.RSstudies.Services.TransactionService;
import com.RSstudies.Services.UsersService;
import com.RSstudies.models.Account;
import com.RSstudies.models.Loan;
import com.RSstudies.models.Payee;
import com.RSstudies.models.Transaction;
import com.RSstudies.models.Users;

@RestController
@RequestMapping("/api")
public class UserController {
	
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
	
	
	@PostMapping("/addusers")
	public ResponseEntity<Users> addUser(@RequestBody Users user) {
		return new ResponseEntity<Users>(userService.addUser(user),HttpStatus.CREATED);
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<Users> getLoggedInCustomerDetailsHandler(Authentication auth){
		
		
		Users customer = userService.login(auth.getName());
		 
		return new ResponseEntity<Users>(customer, HttpStatus.ACCEPTED);		
	}
	
	@GetMapping("/user/profile/{userId}")
	public ResponseEntity<Users> getUserByUserId(@RequestParam int userId) {
		return new ResponseEntity<Users>(userService.getUserByUserId(userId),HttpStatus.OK);
	}
	
	
	@GetMapping("/user/getallusers")
	public ResponseEntity<List<Users>> getAllUser(){
		return new ResponseEntity<List<Users>>(userService.getAllUsers(),HttpStatus.CREATED);
	}
	@DeleteMapping("/user/delete/{userId}")
	public ResponseEntity<String> deleteUserByUserId(@RequestParam int userId){
		return new ResponseEntity<String>(userService.deleteUser(userId), HttpStatus.OK);
	}
	
	
	@GetMapping("/accounts/accountId")
	public ResponseEntity<Account> getAccountByAccountId(@RequestParam int accountId) {
		return new ResponseEntity<Account>(accountService.getAccountById(accountId),HttpStatus.OK);
	}
	@PostMapping("/accounts/create/{userId}")
	public ResponseEntity<Account> createAccount(@RequestBody Account account, @RequestParam int userId) throws NoDataFoundException {
		return new ResponseEntity<Account>(accountService.createAccount(account, userId),HttpStatus.CREATED);
	}
	@GetMapping("/accounts/list")
	public ResponseEntity<List<Account>> getAllAccount() {
		return new ResponseEntity<List<Account>>(accountService.getAllAccount(),HttpStatus.OK);
	}
	
	
	
//	Transaction
	@PostMapping("/transaction/deposit")
	public ResponseEntity<String> depositAmount(@RequestBody Account account,@RequestBody double amount){
		return new ResponseEntity<String>(transactionService.deposit(account, amount), HttpStatus.OK);
	}
	@PostMapping("/transaction/transfer")
	public ResponseEntity<String> transferAmount(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber, double amount){
		return new ResponseEntity<String>(transactionService.transfer(fromAccountNumber, toAccountNumber, amount), HttpStatus.OK);
	}
	@PostMapping("/transaction/withdraw")
	public ResponseEntity<String> withdrawAmount(@RequestBody Account account,@RequestBody double amount){
		return new ResponseEntity<String>(transactionService.withdraw(account, amount), HttpStatus.OK);
	}
	@GetMapping("/transaction/list")
	public ResponseEntity<List<Transaction>> getAllTransaction() {
		return new ResponseEntity<List<Transaction>>(transactionService.getAllTransaction(),HttpStatus.OK);
	}
	
	
	
//	Payee
	@PostMapping("/payee/add")
	public ResponseEntity<String> addPayee(@RequestParam String accountNumber,@RequestParam double amount) throws InvalidLoanAmountException, NoDataFoundException, InsufficientBalanceException{
		return new ResponseEntity<String>(payeeService.addPayee(accountNumber, amount),HttpStatus.OK);
	}
	@GetMapping("/payee/list")
	public ResponseEntity<Payee> getAllPayees(Account account){
		return new ResponseEntity<Payee>(payeeService.getPayee(account), HttpStatus.OK);
	}
	
	
	
	
//	Loan
	@PostMapping("/loan/apply-loan")
	public ResponseEntity<String> applyLoan(@RequestParam String accountNumber,@RequestParam double amount) {
		return new ResponseEntity<String>(loanService.applyLoan(accountNumber, amount), HttpStatus.OK);
	}
	@GetMapping("/loan/list-loan")
	public ResponseEntity<Loan> getAllLoans(Account account) {
		return new ResponseEntity<Loan>(loanService.getLoans(account), HttpStatus.OK);
	}
}
