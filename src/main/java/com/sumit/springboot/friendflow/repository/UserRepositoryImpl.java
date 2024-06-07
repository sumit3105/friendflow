package com.sumit.springboot.friendflow.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sumit.springboot.friendflow.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private EntityManager entityManager;
	
	public UserRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public User findByUsername(String username) {
		TypedQuery<User> ty = entityManager.createQuery("FROM User WHERE username=:user", User.class);
		ty.setParameter("user", username);
		try {
			return ty.getSingleResult();
		}
		catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public List<User> findAllExcept(User user) {
		 String query = "SELECT u FROM User u WHERE u.username NOT IN (" +
                 "SELECT f.user1.username FROM Friendship f WHERE f.user2.username = :currentUsername " +
                 "UNION " +
                 "SELECT f.user2.username FROM Friendship f WHERE f.user1.username = :currentUsername) " +
                 "AND u.username != :currentUsername ORDER BY u.username FETCH FIRST 3 ROWS ONLY";
  
		 TypedQuery<User> ty = entityManager.createQuery(query, User.class);
		 ty.setParameter("currentUsername", user.getUsername());
		 return ty.getResultList();
	}

	@Override
	@Transactional
	public void save(User user) {
		entityManager.merge(user);
	}

	@Override
	public List<User> findAllEx(User user) {
		String query = "SELECT u FROM User u WHERE u.username NOT IN (" +
                "SELECT f.user1.username FROM Friendship f WHERE f.user2.username = :currentUsername " +
                "UNION " +
                "SELECT f.user2.username FROM Friendship f WHERE f.user1.username = :currentUsername) " +
                "AND u.username != :currentUsername ORDER BY u.username";
		
		TypedQuery<User> ty = entityManager.createQuery(query, User.class);
		ty.setParameter("currentUsername", user.getUsername());
		return ty.getResultList();
	}

	@Override
	public List<User> findUserWithPendindRequest(User user) {
		String query = "SELECT u FROM User u " +
                "JOIN Friendship f ON u.username = f.user2.username " +
                "WHERE f.user1.username = :currentUsername AND f.status = false";
 
		 TypedQuery<User> ty = entityManager.createQuery(query, User.class);
		 ty.setParameter("currentUsername", user.getUsername());
		 return ty.getResultList();
	}

}
