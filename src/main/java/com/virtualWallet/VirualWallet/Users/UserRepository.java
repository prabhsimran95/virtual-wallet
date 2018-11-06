package com.virtualWallet.VirualWallet.Users;

/**
 * @author Prabhsimran
 * Repository class which gives various inbuilt database functions along with user defined
 * functions.
 */

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>{
	
	public boolean existsByUserEmail(String email);

}
