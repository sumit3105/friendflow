package com.sumit.springboot.friendflow.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sumit.springboot.friendflow.entities.User;
import com.sumit.springboot.friendflow.service.FriendshipService;
import com.sumit.springboot.friendflow.service.UserService;
import com.sumit.springboot.friendflow.session.UserSessionManager;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/friend")
public class FriendshipController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	@GetMapping("/create/{username}")
	public String addFriend(@PathVariable("username") String username, HttpSession session) {
		User user1 = UserSessionManager.getLoggedInUser(session);
		User user2 = userService.getUserByUsername(username);
		
		if(user1 != null && user2 != null) {
			
			if(friendshipService.friendshipExist(user1, user2)) {
				return "403";
			}
			
			friendshipService.addFriendship(user1,user2);
			return "redirect:/user/home";
		}
		else {
			return "404U";
		}
	}
	
	@GetMapping("/all")
	public String viewAllFriend(Model model, HttpSession session) {
		User user = UserSessionManager.getLoggedInUser(session);
		List<User> allUser = userService.getAllUser(user);
		model.addAttribute("allUser", allUser);
		model.addAttribute("user", user);
		return "allProfile";
	}
	
	@GetMapping("/accept/{id}")
	public String acceptFriendship(@PathVariable("id") Long id, HttpSession session) {
		if(friendshipService.acceptFriend(id,session)) {
			return "redirect:/user/home";
		}
		else {
			return "403";
		}
	}
	
	@GetMapping("/decline/{id}")
	public String declineFriendship(@PathVariable("id") Long id, HttpSession session) {
		if(friendshipService.declineFriend(id,session)) {
			return "redirect:/user/home";
		}
		else {
			return "403";
		}
	}
	
	@GetMapping("/view/{username}")
	public String viewAllFriends(@PathVariable("username") String username, HttpSession session, Model model) {
		User u = UserSessionManager.getLoggedInUser(session);
		
		if(u.getUsername().equals(username)) {
			List<User> friends = friendshipService.getAllFriends(username);
			model.addAttribute("friends", friends);
			model.addAttribute("user", u);
			return "viewFriends";
		}
		else {
			return "403";
		}
		
	}
	
}
