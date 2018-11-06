package com.virtualWallet.VirualWallet.Transaction;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.test.annotation.Timed;

import com.virtualWallet.VirualWallet.Account.Account;

@Entity
@Table(name="transaction")
public class Transaction {
	private static final String GenerationTime = null;

	@Id
	@GeneratedValue
	private long transactionId;
	
	private long fromAccountId;
	
	private long toAccountId;
	
	private double amount;
	
	@ManyToOne
	private Account account;
	
	public Transaction() {
		
	}
	
	public Transaction(long transactionId, long fromAccountId, long toAccountId, double amount, Account account) {
		super();
		this.transactionId = transactionId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
		this.account = account;
	}

	
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public long getFromAccountId() {
		return fromAccountId;
	}

	public long getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(long toAccountId) {
		this.toAccountId = toAccountId;
	}
	
	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	

}
