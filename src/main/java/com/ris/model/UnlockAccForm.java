/**
 * 
 */
package com.ris.model;

import java.sql.Date;

import lombok.Data;

/**
 * @author Agarw
 *
 */
@Data
public class UnlockAccForm {
	
	private String emailId;
	
	private String modifiedPwd;
	
	private String tempUserPwd;

}
