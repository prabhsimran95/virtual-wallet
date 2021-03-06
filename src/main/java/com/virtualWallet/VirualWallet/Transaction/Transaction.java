package com.virtualWallet.VirualWallet.Transaction;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.test.annotation.Timed;

import com.virtualWallet.VirualWallet.Account.Account;

@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;
	
	private long fromAccountId;	
	
	private long toAccountId;
	
	private String type;
	
	private double amount;
	
	@CreationTimestamp
	private LocalDateTime timestamp;
	
	@ManyToOne
	private Account account;	
	
	
	public Transaction() {
		
	}
	
	public Transaction(long transactionId, long fromAccountId, long toAccountId, double amount, long accountId) {
		super();
		this.transactionId = transactionId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
		this.account = new Account(0,0.0);
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

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime createDateTime) {
		this.timestamp = createDateTime;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	

}
