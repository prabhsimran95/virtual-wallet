package com.virtualWallet.VirualWallet.Transaction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {
	public List<Transaction> findAllByAccountAccountId(long accountId);
}
