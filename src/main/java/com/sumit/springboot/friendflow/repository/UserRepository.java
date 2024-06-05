package com.sumit.springboot.friendflow.repository;
import java.util.List;

import com.sumit.springboot.friendflow.entities.User;


public interface UserRepository{
	
	public User findByUsername(String username);
	
	public List<User> findAllExcept(User user);
	
	public void save(User user);
	
	public List<User> findAllEx(User user);
}
