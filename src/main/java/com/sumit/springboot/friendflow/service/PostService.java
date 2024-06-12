package com.sumit.springboot.friendflow.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.springboot.friendflow.entities.Image;
import com.sumit.springboot.friendflow.entities.Like;
import com.sumit.springboot.friendflow.entities.Post;
import com.sumit.springboot.friendflow.entities.User;
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

	@Transactional
	public void removePost(int id, HttpSession session) {
		User user = UserSessionManager.getLoggedInUser(session);
		Post p = postRepository.findById(id);
		if (p != null) {
			List<Image> images = p.getImages();
			if (images != null && !images.isEmpty()) {
				for (Image image : images) {
					try {
						Files.deleteIfExists(Paths.get(UPLOADED_FOLDER + image.getName()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					imageRepository.delete(image);
				}				
			}
			
			System.out.println(p.getImages().removeAll(images));
			postRepository.delete(p);			
			
			List<Post> pt = user.getPosts();
			
			System.out.println(pt.remove(p));
			System.out.println("--------------------Posts from service--------------------");
			System.out.println(user.getPosts());
			user.setPosts(pt);
			userRepository.save(user);
			UserSessionManager.setUserLoggedIn(session, user);
		}
	}
	
	public List<Post> getPostsOfFriends(User u){
		return postRepository.findPostsOfFriends(u);
	}
	
	public List<Post> getPostOfUser(String username){
		return postRepository.findPostOfUser(username);
	}
	
	public void likePost(int id, User u) {
		Post p = postRepository.findById(id);
		
		Like like = new Like(p, u);
		likeRepository.save(like);
		
		List<Like> l = p.getLikes();
		l.add(like);
		p.setLikes(l);

		postRepository.save(p);
		
	}
	
	public void unlikePost(Post p, User u) {
		Like like = p.getLikes().stream()
                .filter(l -> l.getUser().getUsername().equals(u.getUsername()))
                .findFirst()
                .orElse(null);
		
		if (like != null) {
            p.getLikes().remove(like);
            likeRepository.delete(like);
            postRepository.save(p);
        }
		
	}
}
