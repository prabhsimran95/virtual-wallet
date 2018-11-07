package com.virtualWallet.VirualWallet.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.virtualWallet.VirualWallet.Account.Account;
import com.virtualWallet.VirualWallet.Account.AccountRepository;
import com.virtualWallet.VirualWallet.ErrorHandling.CustomErrorType;
import com.virtualWallet.VirualWallet.ErrorHandling.Exceptions;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRepository accRepository;

	@Async
	public CompletableFuture<ResponseEntity<?>> withdrawMoney(Account account, double amount) throws InterruptedException {
		if (account.getAccountBalance() < amount || account.getAccountBalance() <= 0) {
			ResponseEntity<CustomErrorType> rs = new ResponseEntity<CustomErrorType>(
					new CustomErrorType("LOW BALANCE. CANNOT PROCESS THE TRANSACTION", "UNPROCESSABLE_ENTITY"),
					HttpStatus.UNPROCESSABLE_ENTITY);
			return CompletableFuture.completedFuture(rs);
		} else {
			account.setAccountBalance(account.getAccountBalance() - amount);
			accRepository.save(account);
			Transaction trans = new Transaction();
			trans.setAccount(new Account(account.getAccountId(),account.getAccountBalance()));
			trans.setAmount(amount);
			trans.setTimestamp(trans.getTimestamp());
			trans.setType("Withdrawal");
			transactionRepository.save(trans);
			ResponseEntity<Transaction> rs = new ResponseEntity<Transaction>(trans, HttpStatus.OK);
			return CompletableFuture.completedFuture(rs);
		}

	}

	@Async
	public CompletableFuture<ResponseEntity<?>> depositMoney(Account acc, double amount) throws InterruptedException {
		
		acc.setAccountBalance(acc.getAccountBalance() + amount);
		accRepository.save(acc);
		Transaction trans = new Transaction();
		trans.setAccount(new Account(acc.getAccountId(),acc.getAccountBalance()));
		trans.setAmount(amount);
		trans.setTimestamp(trans.getTimestamp());
		trans.setType("Deposit");
		transactionRepository.save(trans);
		ResponseEntity<Transaction> rs = new ResponseEntity<Transaction>(trans, HttpStatus.OK);
		return CompletableFuture.completedFuture(rs);
	}

	@Async
	public CompletableFuture<ResponseEntity<?>> transferAmountBetweenAccounts(Account ac1, Account ac2, double amount)
			throws InterruptedException {
		try {
			if (ac1.getAccountBalance() < amount || ac1.getAccountBalance() <= 0) {
				ResponseEntity<CustomErrorType> rs = new ResponseEntity<CustomErrorType>(
						new CustomErrorType("LOW BALANCE. CANNOT PROCESS THE TRANSACTION", "UNPROCESSABLE_ENTITY"),
						HttpStatus.UNPROCESSABLE_ENTITY);
				return CompletableFuture.completedFuture(rs);
			} else {
				ac1.setAccountBalance(ac1.getAccountBalance() - amount);
				ac2.setAccountBalance(ac2.getAccountBalance() + amount);
				Transaction trans1 = new Transaction();
				trans1.setAccount(new Account(ac1.getAccountId(),ac1.getAccountBalance()));
				trans1.setAmount(amount);
				trans1.setFromAccountId(ac1.getAccountId());
				trans1.setToAccountId(ac2.getAccountId());
				trans1.setTimestamp(trans1.getTimestamp());
				trans1.setType("Withdrawal");
				Transaction trans2 = new Transaction();
				trans2.setAccount(new Account(ac2.getAccountId(),ac2.getAccountBalance()));
				trans2.setAmount(amount);
				trans2.setFromAccountId(ac1.getAccountId());
				trans2.setToAccountId(ac2.getAccountId());
				trans2.setTimestamp(trans2.getTimestamp());
				trans2.setType("Deposit");
				accRepository.save(ac1);
				accRepository.save(ac2);
				transactionRepository.save(trans1);
				transactionRepository.save(trans2);
				ResponseEntity<String> rs = new ResponseEntity<String>(
						"TRANSFERRED " + amount + "$ FROM ACCOUNT_ID: " + ac1.getAccountId() + " TO ACCOUNT_ID: " + ac2.getAccountId(),
						HttpStatus.OK);
				return CompletableFuture.completedFuture(rs);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public ResponseEntity<List<Transaction>> getTransactions(long id) {
		List<Transaction> transactions = new ArrayList<>();
		transactionRepository.findAllByAccountAccountId(id).forEach(t -> transactions.add(t));
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);

	}

}
