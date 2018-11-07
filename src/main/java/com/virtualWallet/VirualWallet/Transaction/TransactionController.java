package com.virtualWallet.VirualWallet.Transaction;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.virtualWallet.VirualWallet.Account.Account;
import com.virtualWallet.VirualWallet.Account.AccountRepository;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountRepository accRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/withdrawal/{accountId}/{amount}")
	public ResponseEntity<?> withdrawalTransaction(@PathVariable String accountId, @PathVariable String amount)
			throws InterruptedException, ExecutionException {
		Account acc = accRepository.findByaccountId(Long.parseLong(accountId)).get();
		CompletableFuture<ResponseEntity<?>> rs = transactionService.withdrawMoney(acc, Double.parseDouble(amount));
		return rs.get();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deposit/{accountId}/{amount}")
	public ResponseEntity<?> depositTransaction(@PathVariable String accountId, @PathVariable String amount)
			throws InterruptedException, ExecutionException {
		Account acc = accRepository.findByaccountId(Long.parseLong(accountId)).get();
		CompletableFuture<ResponseEntity<?>> rs = transactionService.depositMoney(acc, Double.parseDouble(amount));
		return rs.get();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transfer/{fromAccountId}/{toAccountId}/{amount}")
	public ResponseEntity<?> transferAmountbetweenAccounts(@PathVariable String fromAccountId,
			@PathVariable String toAccountId, @PathVariable String amount)
			throws NumberFormatException, InterruptedException, ExecutionException {
		Account ac1 = accRepository.findByaccountId(Long.parseLong(fromAccountId)).get();
		Account ac2 = accRepository.findByaccountId(Long.parseLong(toAccountId)).get();
		CompletableFuture<ResponseEntity<?>> rs = transactionService.transferAmountBetweenAccounts(ac1, ac2,
				Double.parseDouble(amount));
		return rs.get();
	}

	@RequestMapping("/transaction/{accountId}")
	public ResponseEntity<List<Transaction>> getLastNTransactions(@PathVariable String accountId) {
		return transactionService.getTransactions(Long.parseLong(accountId));
	}
}
