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
public class Country {

	@Id
	private Integer countryId;
	private String countryName;
}
