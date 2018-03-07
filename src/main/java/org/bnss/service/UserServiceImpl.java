package org.bnss.service;

import java.util.List;

import org.bnss.domain.RadCheck;
import org.bnss.domain.User;
import org.bnss.repository.RadRepository;
import org.bnss.repository.UserCustomRepository;
import org.bnss.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RadRepository radRepo;
	
	@Autowired
	UserCustomRepository userCustomRepo;
	
	@Override
	public List<User> getAllUsers() {
		return userCustomRepo.getAllUsers();
	}

	@Override
	public User findUserById(Long id) {
		return userRepo.findById(id);
	}

	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public List<RadCheck> getAllRad() {
		return userCustomRepo.getAllRad();
	}

	@Override
	public RadCheck findRadById(Long id) {
		return radRepo.findById(id);
	}

}
