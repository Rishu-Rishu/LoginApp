/**
 * 
 */
package com.ris.model;

import lombok.Data;

/**
 * @author Agarw
 *
 */
@Data
public class LoginForm {
	
	private String email;
	private String pwd;

	public String toString() {
		return "email:"+email +",password:"+pwd;
	}
}
