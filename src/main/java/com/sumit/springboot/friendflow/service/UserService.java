package com.sumit.springboot.friendflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.springboot.friendflow.entities.User;
import com.sumit.springboot.friendflow.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public boolean authenticateUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void createUser(User user) {
		userRepository.save(user);
	}
	
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	public List<User> getAllUser(User user){
		return userRepository.findAllEx(user);
	}
	
	public List<User> getAllUserExcept(User user){
		List<User> allUser = userRepository.findAllExcept(user);
		return allUser;
	}
	
}
