package com.sumit.springboot.friendflow.repository;

import java.util.List;

import com.sumit.springboot.friendflow.entities.Post;
import com.sumit.springboot.friendflow.entities.User;

public interface PostRepository {
	
	public Post findById(int id);
	
	public void save(Post p);
	
	public void delete(Post p);
	
	public List<Post> findPostsOfFriends(User u);
}
