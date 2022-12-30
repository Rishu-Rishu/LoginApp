/**
 * 
 */
package com.ris.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ris.model.User;

/**
 * @author Agarw
 *
 */
public interface IUserMgmtUserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmailId(String emailId);

	public User findByEmailIdAndUserPwd(String emailId, String userPwd);
}
