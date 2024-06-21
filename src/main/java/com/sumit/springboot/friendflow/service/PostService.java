package com.sumit.springboot.friendflow.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.springboot.friendflow.entities.Comment;
import com.sumit.springboot.friendflow.entities.Image;
import com.sumit.springboot.friendflow.entities.Like;
import com.sumit.springboot.friendflow.entities.Post;
import com.sumit.springboot.friendflow.entities.User;
import com.sumit.springboot.friendflow.repository.CommentRepo;
import com.sumit.springboot.friendflow.repository.ImageRepository;
import com.sumit.springboot.friendflow.repository.LikeRepository;
import com.sumit.springboot.friendflow.repository.PostRepository;
import com.sumit.springboot.friendflow.repository.UserRepository;
import com.sumit.springboot.friendflow.session.UserSessionManager;

import jakarta.servlet.http.HttpSession;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private CommentRepo commentRepo;

	private static String UPLOADED_FOLDER = "src/main/resources/static/img/post/";

	public List<Image> generateImages(MultipartFile[] imagesData) {
		List<Image> images = new ArrayList<Image>();

		File uploadDir = new File(UPLOADED_FOLDER);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		for (MultipartFile image : imagesData) {
			try {
				byte[] bytes = image.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + image.getOriginalFilename());
				Files.write(path, bytes);

				// Create Image entity
				Image img = new Image();
				img.setName(image.getOriginalFilename());
				img.setImageUrl("/img/post/" + image.getOriginalFilename());
				images.add(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return images;
	}

	public void createPost(Post p) {
		postRepository.save(p);
	}

	public Post getPostById(int id) {
		return postRepository.findById(id);
	}

	public void removePost(int id, HttpSession session) {
		User user = UserSessionManager.getLoggedInUser(session);
		Post p = postRepository.findById(id);
		if (p != null) {
			
			List<Image> images = p.getImages();
			if (images != null && !images.isEmpty()) {
				for (Image image : images) {
					imageRepository.delete(image);
				}				
			}			
			
			List<Like> likes = p.getLikes();
			if(likes != null && !likes.isEmpty()) {
				for(Like like : likes) {
					likeRepository.delete(like);
				}
			}
			
			List<Comment> comments = p.getComments();
			if(comments != null && !comments.isEmpty()) {
				for(Comment comment : comments) {
					commentRepo.delete(comment);
				}
			}
			
			List<Post> pt = user.getPosts();
			
			if(pt != null && !pt.isEmpty()) {
				for(Post post : pt) {
					if(post.getId() == p.getId()) {
						System.out.println(pt.remove(post));
						break;
					}
				}
			}
			
			System.out.println("--------------------Posts from service--------------------");
			user.setPosts(pt);
			System.out.println(user.getPosts());
			userRepository.save(user);
			postRepository.delete(p);
			UserSessionManager.setUserLoggedIn(session, user);
		}
	}
	
	public List<Post> getPostsOfFriends(User u){
		return postRepository.findPostsOfFriends(u);
	}
	
	public List<Post> getPostOfUser(String username){
		return postRepository.findPostOfUser(username);
	}
	
	public Post likePost(int id, User u) {
		Post p = postRepository.findById(id);
		
		Like like = new Like(p, u);
		likeRepository.save(like);
		
		List<Like> l = p.getLikes();
		l.add(like);
		p.setLikes(l);

		postRepository.save(p);
		
		return p;
		
	}
	
	public Post unlikePost(Post p, User u) {
		Like like = p.getLikes().stream()
                .filter(l -> l.getUser().getUsername().equals(u.getUsername()))
                .findFirst()
                .orElse(null);
		
		if (like != null) {
            p.getLikes().remove(like);
            likeRepository.delete(like);
            postRepository.save(p);
            
            return p;
        }
		return null;
	}
	
	public void addComment(int postId, String comment, User u) {
		
		Post post = postRepository.findById(postId);
		
		Comment newComment = new Comment(post,u,comment,new Date(),false);
		commentRepo.save(newComment);
		
		List<Comment> c = post.getComments();
		c.add(newComment);
		post.setComments(c);
		
		postRepository.save(post);	
	}
}
