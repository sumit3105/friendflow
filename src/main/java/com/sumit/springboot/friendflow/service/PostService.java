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
import org.springframework.web.multipart.MultipartFile;

import com.sumit.springboot.friendflow.entities.Image;
import com.sumit.springboot.friendflow.entities.Post;
import com.sumit.springboot.friendflow.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/img/post/";
	
	public List<Image> generateImages(MultipartFile[] imagesData){
		List<Image> images = new ArrayList<Image>();
		
		File uploadDir = new File(UPLOADED_FOLDER);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		for(MultipartFile image: imagesData) {
			try {
				byte[] bytes = image.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + image.getOriginalFilename());
				Files.write(path, bytes);
				
				// Create Image entity
				Image img = new Image();
				img.setName(image.getOriginalFilename());
				img.setImageUrl("/img/post/" + image.getOriginalFilename());
				images.add(img);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return images;
	}
	
	public void createPost(Post p) {
		postRepository.save(p);
	}
}
