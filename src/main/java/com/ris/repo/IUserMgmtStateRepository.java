/**
 * 
 */
package com.ris.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ris.model.State;

/**
 * @author Agarw
 *
 */
public interface IUserMgmtStateRepository extends JpaRepository<State, Integer> {
	
	public List<State> findByCountryId(Integer id);

}
