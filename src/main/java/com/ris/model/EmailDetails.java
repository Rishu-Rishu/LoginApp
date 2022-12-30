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
public class EmailDetails {

	private String recipient;
	private String msgBody;
	private String subject;
}
