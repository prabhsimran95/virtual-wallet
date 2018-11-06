package com.virtualWallet.VirualWallet.Users;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author Prabhsimran
 * This is the User Model class 
 *
 */


@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String walletId;	
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String userId;

	private String walletName;

	private String userName;

	private String userEmail;

	public User() {

	}

	public User(String userId, String walletId, String walletName, String userName, String userEmail) {
		this.userId = userId;
		this.walletId = walletId;
		this.walletName = walletName;
		this.userName = userName;
		this.userEmail = userEmail;

	}

	public String getWalletId() {
		return walletId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

}
