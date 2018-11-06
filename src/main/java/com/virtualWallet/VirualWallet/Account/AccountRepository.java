package com.virtualWallet.VirualWallet.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
      	public List<Account> findAllByUserWalletId(String WalletId);
    	public Optional<Account> findByaccountId(Long accountId);
}
