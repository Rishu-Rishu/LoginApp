/**
 * 
 */
package com.ris.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ris.model.City;

/**
 * @author Agarw
 *
 */
public interface IUserMgmtCityRepository extends JpaRepository<City, Integer> {
	
	public List<City> findByStateId(Integer id);

}
