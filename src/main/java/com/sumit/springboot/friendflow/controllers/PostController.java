package com.sumit.springboot.friendflow.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;

	@GetMapping("/create")
	public String viewPostForm(Model model, HttpSession session) {
		User user = UserSessionManager.getLoggedInUser(session);
		if (user != null) {
			model.addAttribute("user", user);
			return "postForm";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("/create")
	public String addPost(@RequestParam("username") String username, @RequestParam("images") MultipartFile[] imagesData,
			@RequestParam("caption") String caption, HttpSession session) {
		List<Image> images = postService.generateImages(imagesData);
		User user = UserSessionManager.getLoggedInUser(session);

		if (user != null && user.getUsername().equals(username)) {
			Post p = new Post(caption, new Date(), user, images);
			postService.createPost(p);
			List<Post> pt = user.getPosts();
			pt.add(p);
			user.setPosts(pt);
			System.out.println(user.getPosts());
			UserSessionManager.setUserLoggedIn(session, user);
			return "redirect:/user/profile";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") int id, HttpSession session) {
		User u = UserSessionManager.getLoggedInUser(session);
		Post p = postService.getPostById(id);
		
		if(p.getUser().getUsername().equals(u.getUsername())) {
			postService.removePost(id, session);
			return "redirect:/user/profile";
		}
		else {
			return "403";
		}
	}
	
	@PostMapping("/like/{postId}")
	public ResponseEntity<Void> likeOrDislikePost(@PathVariable int postId, HttpSession session){
		User user = UserSessionManager.getLoggedInUser(session);
		Post post = postService.getPostById(postId);
		
		if (post.isLikedByUser(user)) {
            // If already liked, remove the like
            postService.unlikePost(post, user);
        } else {
            // Otherwise, add a new like
            postService.likePost(postId, user);
        }
		return ResponseEntity.ok().build();
	}
}
