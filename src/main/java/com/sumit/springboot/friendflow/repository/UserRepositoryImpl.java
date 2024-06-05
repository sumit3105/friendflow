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
		TypedQuery<User> ty = entityManager.createQuery("FROM User u WHERE u.username != :uname ORDER BY u.firstName FETCH FIRST 3 ROWS ONLY ", User.class);
		ty.setParameter("uname", user.getUsername());
		return ty.getResultList();
	}

	@Override
	@Transactional
	public void save(User user) {
		entityManager.merge(user);
	}

	@Override
	public List<User> findAllEx(User user) {
		TypedQuery<User> ty = entityManager.createQuery("FROM User u WHERE u.username != :uname", User.class);
		ty.setParameter("uname", user.getUsername());
		return ty.getResultList();
	}

}
