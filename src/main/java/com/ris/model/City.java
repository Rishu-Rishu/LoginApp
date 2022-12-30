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
public class City {

	@Id
	private Integer cityId;
	private String cityName;
	private Integer stateId;
}
