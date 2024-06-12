package com.sumit.springboot.friendflow.repository;

import com.sumit.springboot.friendflow.entities.Like;

public interface LikeRepository {
	
	public void save(Like l);
	
	public void delete(Like l);
}
