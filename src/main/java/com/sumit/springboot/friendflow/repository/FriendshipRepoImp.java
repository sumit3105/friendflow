package com.sumit.springboot.friendflow.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sumit.springboot.friendflow.entities.Friendship;
import com.sumit.springboot.friendflow.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class FriendshipRepoImp implements FriendshipRepo {

	private EntityManager entityManager;
	
	public FriendshipRepoImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(Friendship f) {
		entityManager.merge(f);
	}

	@Override
	public List<Friendship> findPending(User u) {
		
		TypedQuery<Friendship> ty = entityManager.createQuery("FROM Friendship f WHERE f.user2.username = :uname AND f.status=false", Friendship.class);
		ty.setParameter("uname", u.getUsername());
		return ty.getResultList();
		
	}
}
