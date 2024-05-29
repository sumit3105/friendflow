package com.sumit.springboot.friendflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.springboot.friendflow.entities.Image;
import com.sumit.springboot.friendflow.repository.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Transactional
	public void deleteImage(Image image) {
		imageRepository.delete(image);
	}
}
