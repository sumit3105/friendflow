package com.sumit.springboot.friendflow.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sumit.springboot.friendflow.entities.User;


public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
	
}
