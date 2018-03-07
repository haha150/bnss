package org.bnss.service;

import java.util.List;

import org.bnss.domain.RadCheck;
import org.bnss.domain.User;


public interface UserService {
	
	List<User> getAllUsers();
	List<RadCheck> getAllRad();
	User findUserById(Long id);
	RadCheck findRadById(Long id);
	User addUser(User user);
	User updateUser(User user);

}
