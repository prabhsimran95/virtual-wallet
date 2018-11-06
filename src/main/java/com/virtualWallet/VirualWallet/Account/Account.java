package com.virtualWallet.VirualWallet.Account;
/**
 * @author Prabhsimran
 * Account Model Class
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.virtualWallet.VirualWallet.Users.User;

@Entity
@Table(name = "Account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;
	
	private double accountBalance;

	@ManyToOne
	private User user;
	
	public Account() {		
	}

	public Account(long accountId) {
		super();
		this.accountId = accountId;		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getAccountId() {
		return accountId;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

}
