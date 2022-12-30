/**
 * 
 */
package com.ris.service;

import java.util.Map;

import com.ris.model.EmailDetails;
import com.ris.model.LoginForm;
import com.ris.model.UnlockAccForm;
import com.ris.model.User;

/**
 * @author Agarw
 *
 */
public interface IUserMgmtService {

	public String checkEmail (String email);

	public Map<Integer, String> getCountries ( ) ;

	public Map<Integer, String> getStates (Integer countryId);

	public Map<Integer, String> getCities (Integer stateId);

	public String registerUser (User user);

	public String unlockAccount (UnlockAccForm accForm);

	public String login (LoginForm loginForm);

	public String forgotPwd (String email);
	
	public String sendEmail(EmailDetails emailDetails);
}
