package com.sumit.springboot.friendflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.springboot.friendflow.entities.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {
	public Post findById(int id);
}
