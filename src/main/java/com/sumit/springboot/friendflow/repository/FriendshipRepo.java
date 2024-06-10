package com.sumit.springboot.friendflow.repository;

import java.util.List;

import com.sumit.springboot.friendflow.entities.Friendship;
import com.sumit.springboot.friendflow.entities.User;

public interface FriendshipRepo {
	public void save(Friendship f);
	
	public List<Friendship> findPending(User u);
	
	public Friendship findById(Long id);
	
	public void delete(Friendship f);
	
	public List<User> findAllFriends(String username);
	
}
