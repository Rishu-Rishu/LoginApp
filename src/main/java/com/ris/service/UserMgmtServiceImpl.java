/**
 * 
 */
package com.ris.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ris.model.City;
import com.ris.model.Country;
import com.ris.model.LoginForm;
import com.ris.model.State;
import com.ris.model.UnlockAccForm;
import com.ris.model.User;
import com.ris.repo.IUserMgmtCityRepository;
import com.ris.repo.IUserMgmtCountryRepository;
import com.ris.repo.IUserMgmtStateRepository;
import com.ris.repo.IUserMgmtUserRepository;

/**
 * @author Agarw
 *
 */
@Service
public class UserMgmtServiceImpl implements IUserMgmtService {

	@Autowired
	private IUserMgmtCountryRepository countryRepo;
	
	@Autowired
	private IUserMgmtStateRepository stateRepo;
	
	@Autowired
	private IUserMgmtCityRepository cityRepo;
	
	@Autowired
	private IUserMgmtUserRepository userRepo;
	
	@Override
	public String checkEmail(String email) {
		User user = userRepo.findByEmailId(email);
		if(email.equals(user.getEmailId())){
			return "email exist";
		}else {
			return "email not exist";
		}
	}

	@Override
	public Map<Integer, String> getCountries() {
		Iterable<Country> itr = countryRepo.findAll();
		Map<Integer,String> countryMap = new HashMap();
		
		for(Country country : itr) {
			countryMap.put(country.getCountryId(),country.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		Map<Integer,String> stateMap = new HashMap();
		List<State> ls = stateRepo.findByCountryId(countryId);
		for(State state:ls) {
			stateMap.put(state.getStateId(), state.getStateName());
		}
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		Map<Integer, String> cityMap = new HashMap();
		List<City> ls = cityRepo.findByStateId(stateId);
		for(City city:ls) {
			cityMap.put(city.getCityId(), city.getCityName());
		}
		return cityMap;
	}

	@Override
	public String registerUser(User user) {
		User usr = userRepo.save(user);
		if(usr!=null) {
			return "User registered successfully having user id::"+usr.getUserId();
		}
		return "Registration is unsuccessfull";
	}

	@Override
	public String unlockAccount(UnlockAccForm accForm) {
		String emailId = accForm.getEmailId();
		String modifiedPwd = accForm.getModifiedPwd();
		String tempUserPwd = accForm.getTempUserPwd();
		
		User user = userRepo.findByEmailId(emailId);
		if(user !=null){
			user.setUserPwd(modifiedPwd);
			User usr = userRepo.save(user);
			return "Account Unlocked Successfully";
		}else {
			return "email not exist. Please signup to access";
		}
	}

	@Override
	public String login(LoginForm loginForm) {
		String email = loginForm.getEmail();
		String pwd = loginForm.getPwd();
		User user = userRepo.findByEmailIdAndUserPwd(email, pwd);
		
		if(user !=null){
			return "Welcome to Home Page";
		}else {
			return "Logged in fail";
		}
	}

	@Override
	public String forgotPwd(String email) {
		User user = userRepo.findByEmailId(email);
		if (user !=null) {
			//write logic to send email for temp pwd
			return "Temp pwd sent successfully, Please check your email";
		}else{
			return "User Not registered with above email id";
		}
	}

}
