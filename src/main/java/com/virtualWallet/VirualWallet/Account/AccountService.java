package com.virtualWallet.VirualWallet.Account;

/**
 * @author Prabhsimran
 * Service Class for Account. It include methods for creating user Account, getting current user balance,
 * and get all accounts by walletId.
 */

import java.util.ArrayList;
import java.util.List;

import org.h2.jdbc.JdbcSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.virtualWallet.VirualWallet.ErrorHandling.CustomErrorType;
import com.virtualWallet.VirualWallet.Users.User;
import com.virtualWallet.VirualWallet.Users.UserRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<?> createAccount(Account account) throws JdbcSQLException{
		try {
			accountRepository.save(account);
			return new ResponseEntity<Account>(account, HttpStatus.OK);
			
		}
		catch(Exception e) {
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("INAPPROPRIATE WALLETID. WALLET DOES NOT EXIST", "CONFLICT"),
					HttpStatus.CONFLICT);
		}
		
	}

	public ResponseEntity<?> getAllAccountsByWalletId(String WalletId) {

		List<Account> accounts = new ArrayList<>();
		accountRepository.findAllByUserWalletId(WalletId).forEach(t -> accounts.add(t));
		if (accounts.isEmpty()) {
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("NO SUCH ACCOUNTS WITH WALLET ID:" + WalletId + " FOUND", "NOT_FOUND"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}

	
	public ResponseEntity<?> currentBalance(User user,Long accountId) {
		Account account = new Account();
		if(userRepository.existsById(user.getWalletId())) {
			account = accountRepository.findByaccountId(accountId).get();			
		}
		return new ResponseEntity<Account>(account, HttpStatus.OK);
		
	}

}
