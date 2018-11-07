package com.virtualWallet.VirualWallet.Users;

/**
 * @author Prabhsimran
 * Service class for User which creates a new user and perform error handling
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.virtualWallet.VirualWallet.ErrorHandling.CustomErrorType;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<?> createWallet(User user) {

		if (!userRepository.existsByUserEmail(user.getUserEmail())) {
			userRepository.save(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} else

			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("USER ALREADY EXISTS. ENTER A UNIQUE EMAIL_ID", "CONFLICT"),
					HttpStatus.CONFLICT);

	}
}
