package com.virtualWallet.VirualWallet;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.virtualWallet.VirualWallet.Account.Account;
import com.virtualWallet.VirualWallet.Account.AccountService;
import com.virtualWallet.VirualWallet.Transaction.TransactionService;
import com.virtualWallet.VirualWallet.Users.User;
import com.virtualWallet.VirualWallet.Users.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VirualWalletApplicationTests {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;

	private Account account;

	private User user;

	@Before
	public void VirualWalletApplicationTests1() {
		this.user = new User("0001", "101", "myWallet", "Prabh", "Prabhsimran@gmail.com");		
	}	
	
	@Test
	public void testCreateWallet() {
		ResponseEntity<?> rs = userService.createWallet(user);
		assertEquals(201, rs.getStatusCodeValue());
	}
	@Test
	public void testCreateDuplicateWallet() {
		ResponseEntity<?> rs = userService.createWallet(new User("","","","","Prabhsimran@gmail.com"));
		assertEquals(409, rs.getStatusCodeValue());
	}
	@Test
	public void testCreateAccount() {
		this.account = new Account(1001L, 500.00);
		ResponseEntity<?> rs = accountService.createAccount(account);
		assertEquals(200, rs.getStatusCodeValue());
	}
	@Test
	public void testBalance() {
		this.account = new Account(1001L, 500.00);
		ResponseEntity<?> rs = accountService.currentBalance(user, 1001L);
		assertEquals(200, rs.getStatusCodeValue());
	}
	@Test
	public void testGetAllAccountsByWalletId() {		
		this.account= new Account(1001L, 500.00, "101");	
		this.account= new Account(1002L, 500.00, "101");	
		ResponseEntity<?> rs = accountService.getAllAccountsByWalletId("101");
		assertEquals(200, rs.getStatusCodeValue());
	}
	
	@Test
	public void testGetAllAccountsByWalletIdNotfound() {		
		this.account= new Account(1001L, 500.00, "101");	
		this.account= new Account(1002L, 500.00, "101");	
		ResponseEntity<?> rs = accountService.getAllAccountsByWalletId("1011");
		assertEquals(404, rs.getStatusCodeValue());
	}
	@Test
	public void testWithdrawMoney() throws InterruptedException, ExecutionException {	
		this.account= new Account(1002L, 500.00, "101");
		CompletableFuture<ResponseEntity<?>> rs = transactionService.withdrawMoney(account,500.00);		
		assertEquals(200, rs.get().getStatusCodeValue());
	}
	
	
	
	
	

}
