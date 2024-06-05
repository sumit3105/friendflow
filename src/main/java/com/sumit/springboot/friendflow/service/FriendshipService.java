package com.sumit.springboot.friendflow.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.springboot.friendflow.entities.User;
import com.sumit.springboot.friendflow.entities.Friendship;

import com.sumit.springboot.friendflow.repository.FriendshipRepo;

@Service
public class FriendshipService {
	
	@Autowired
	private FriendshipRepo friendshipRepo;
	
	public void addFriendship(User user1, User user2) {
		Friendship f = new Friendship(user1,user2,new Date(),false);
		friendshipRepo.save(f);
	}
	
}
