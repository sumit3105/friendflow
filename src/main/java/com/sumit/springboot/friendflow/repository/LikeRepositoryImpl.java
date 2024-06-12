package com.sumit.springboot.friendflow.repository;

import org.springframework.stereotype.Repository;

import com.sumit.springboot.friendflow.entities.Like;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class LikeRepositoryImpl implements LikeRepository {

	private EntityManager entityManager;
	
	public LikeRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Like l) {
		entityManager.merge(l);
	}

	@Override
	@Transactional
	public void delete(Like l) {
		entityManager.remove(l);
	}

}
