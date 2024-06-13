package com.sumit.springboot.friendflow.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.springboot.friendflow.entities.User;
import com.sumit.springboot.friendflow.entities.Friendship;

import com.sumit.springboot.friendflow.repository.FriendshipRepo;
import com.sumit.springboot.friendflow.session.UserSessionManager;

import jakarta.servlet.http.HttpSession;

@Service
public class FriendshipService {
	
	@Autowired
	private FriendshipRepo friendshipRepo;
	
	public void addFriendship(User user1, User user2) {
		Friendship f = new Friendship(user1,user2,new Date(),false);
		friendshipRepo.save(f);
	}
	
	public List<Friendship> getPending(User u){
		return friendshipRepo.findPending(u);
	}
	
	public boolean acceptFriend(Long id, HttpSession session) {
		User u = UserSessionManager.getLoggedInUser(session);
		Friendship f = friendshipRepo.findById(id);
		
		if(f.getUser2().getUsername().equals(u.getUsername())) {
			f.setStatus(true);
			friendshipRepo.save(f);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean declineFriend(Long id, HttpSession session) {
		User u = UserSessionManager.getLoggedInUser(session);
		Friendship f = friendshipRepo.findById(id);
		if(f.getUser2().getUsername().equals(u.getUsername())) {
			friendshipRepo.delete(f);
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<User> getAllFriends(String username){
		return friendshipRepo.findAllFriends(username);
	}
	
	public boolean friendshipExist(User user1, User user2) {
		return friendshipRepo.friendshipExist(user1, user2);
	}
}
