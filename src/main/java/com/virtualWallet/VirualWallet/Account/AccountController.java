package com.virtualWallet.VirualWallet.Account;
/**
 * @author Prabhsimran
 * Controller Class for Account which gives public endpoints to create an account, get all accounts 
 * associated with given accounId and retreive account Balance based on walletId and accountId.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.virtualWallet.VirualWallet.Users.User;
import com.virtualWallet.VirualWallet.Users.UserRepository;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/users/{WalletId}/accounts")
	public ResponseEntity<?> getAllAccounts(@PathVariable String WalletId) {
		return accountService.getAllAccountsByWalletId(WalletId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users/{walletId}/accounts")
	public ResponseEntity<?> createAccount(@RequestBody Account account, @PathVariable String walletId)
			throws Exception {
		account.setUser(new User("", walletId, "", "", ""));
		return accountService.createAccount(account);
	}

	@RequestMapping("/users/{WalletId}/accounts/{accountId}")
	public ResponseEntity<?> getCurrentAccountBalance(@PathVariable String accountId, @PathVariable String WalletId) {
		User user = userRepository.findById(WalletId).get();
		return accountService.currentBalance(user, Long.parseLong(accountId));
	}

}
