package com.virtualWallet.VirualWallet.Users;

/**
 * @author Prabhsimran
 * Controller Class for User which is used to call create user API
 */



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/user/create")
	public ResponseEntity<?> createWallet(@RequestBody User user)  {
		return userService.createWallet(user);
	}
	
	
	
}
