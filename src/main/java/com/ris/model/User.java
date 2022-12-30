package com.ris.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Agarw
 *
 */
@Data
@Entity
@Table(name="user_dtls")
public class User {

	@Id
	private Integer userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private long phoneNo;
	private Date dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	private String userPwd;
	private String accStatus;
}
