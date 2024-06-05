package com.sumit.springboot.friendflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.springboot.friendflow.entities.Friendship;

public interface FriendshipRepo extends JpaRepository<Friendship, Long>{
	
}
