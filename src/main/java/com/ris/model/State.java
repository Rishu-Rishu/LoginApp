/**
 * 
 */
package com.ris.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Agarw
 *
 */

@Data
@Entity
public class State {

	@Id
	private Integer stateId;
	private String stateName;
	private Integer countryId;
	
}
