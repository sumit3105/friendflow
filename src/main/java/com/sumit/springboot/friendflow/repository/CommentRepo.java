package com.sumit.springboot.friendflow.repository;

import com.sumit.springboot.friendflow.entities.Comment;

public interface CommentRepo {
	public void save(Comment c);
	
	public void delete(Comment c);
}
