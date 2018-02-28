package org.bnss.service;

import java.util.List;

import org.bnss.domain.User;


public interface UserService {
	
	List<User> getAllUsers();
	User findUserById(Long id);
	User addUser(User user);
	User updateUser(User user);

}
