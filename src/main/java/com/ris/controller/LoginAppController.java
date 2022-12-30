/**
 * 
 */
package com.ris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ris.model.LoginForm;
import com.ris.model.UnlockAccForm;
import com.ris.model.User;
import com.ris.service.IUserMgmtService;

/**
 * @author Agarw
 *
 */
@RestController
public class LoginAppController {

	@Autowired
	private IUserMgmtService userMgmtService;
	
	@GetMapping("/home")
	public String homeLogin(@RequestBody LoginForm loginForm) {
		System.out.println(loginForm);
		return userMgmtService.login(loginForm);
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		return userMgmtService.registerUser(user);
	}
	
	@PostMapping("/unlockAcct")
	public String unlockAccountUser(@RequestBody UnlockAccForm unlockAccForm) {
		
		return userMgmtService.unlockAccount(unlockAccForm);
	}
	
	@PostMapping("/forgetPwd")
	public String UserforgotPwd(@RequestParam("email") String email) {
		System.out.println("controller email:"+email);
		return userMgmtService.forgotPwd(email);
	}
}
