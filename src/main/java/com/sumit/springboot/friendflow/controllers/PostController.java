package com.sumit.springboot.friendflow.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.springboot.friendflow.entities.Image;
import com.sumit.springboot.friendflow.entities.Post;
import com.sumit.springboot.friendflow.entities.User;
import com.sumit.springboot.friendflow.service.PostService;
import com.sumit.springboot.friendflow.session.UserSessionManager;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@GetMapping("/post/create")
	public String viewPostForm(Model model, HttpSession session) {
		User user = UserSessionManager.getLoggedInUser(session);
		if(user != null) {
			model.addAttribute("user", user);
			return "postForm";
		}
		else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/post/create")
	public String addPost(@RequestParam("username") String username, 
	                      @RequestParam("images") MultipartFile[] imagesData, 
	                      @RequestParam("caption") String caption, HttpSession session) {
	    List<Image> images = postService.generateImages(imagesData);
	    User user = UserSessionManager.getLoggedInUser(session);

	    if (user != null && user.getUsername().equals(username)) {
	        Post p = new Post(caption, new Date(), user, images);
	        postService.createPost(p);
	        return "redirect:/user/profile";
	    } else {
	        return "redirect:/login";
	    }
	}

	
	
}
