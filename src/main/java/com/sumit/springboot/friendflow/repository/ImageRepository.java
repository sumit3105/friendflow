package com.sumit.springboot.friendflow.repository;

import com.sumit.springboot.friendflow.entities.Image;

public interface ImageRepository {
	
	public void save(Image i);
	
	public void delete(Image i);
	
}
