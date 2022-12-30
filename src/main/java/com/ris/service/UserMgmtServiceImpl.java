/**
 * 
 */
package com.ris.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ris.model.City;
import com.ris.model.Country;
import com.ris.model.EmailDetails;
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
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
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
			String emailId = user.getEmailId();
			String pwd = "abc123";
			EmailDetails details = new EmailDetails();
			details.setRecipient(emailId);
			details.setSubject("Unlock IES Account");
			details.setMsgBody(
				"Hi "+ user.getFirstName() + " ," + user.getLastName() + ": \n" 
				+ "Welcome to IES Family, Your registration is almost complete. \n"
				+ "Please Unlock your account using below details."
				+ "Temporary password : " + pwd + " \n"
				+ "<a href='http://localhost:8080/home'>Link to Unlock Account</a>"
				);
			return sendEmail(details);
		}else{
			return "User Not registered with above email id";
		}
	}

	@Override
	public String sendEmail(EmailDetails emailDetails) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(sender);
			mailMessage.setTo(emailDetails.getRecipient());
			mailMessage.setText(emailDetails.getMsgBody());
			mailMessage.setSubject(emailDetails.getSubject());
			
			javaMailSender.send(mailMessage); 
			//send(mailMessage);
			return "Temp pwd sent successfully, Please check your email";
		}catch(MailParseException mpe){
				return "parsing the message:";
		}catch(MailAuthenticationException mae) {
			return " in case of authentication failure";
		}catch(MailSendException mse) {
			return " in case of failure when sending the message";
		}catch(MailException me) {
			return "Error MailException sending email";
		}catch(Exception e) {
			return "Error while sending email";
		}
	}
	
	

}
