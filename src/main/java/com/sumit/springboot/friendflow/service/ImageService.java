package com.sumit.springboot.friendflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.springboot.friendflow.entities.Image;
import com.sumit.springboot.friendflow.repository.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	public void deleteImage(Image image) {
		imageRepository.delete(image);
	}
}
