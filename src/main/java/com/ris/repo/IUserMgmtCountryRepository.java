/**
 * 
 */
package com.ris.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ris.model.Country;
import com.ris.model.State;

/**
 * @author Agarw
 *
 */
public interface IUserMgmtCountryRepository extends JpaRepository<Country, Integer> {

}