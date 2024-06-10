package com.sumit.springboot.friendflow.repository;


import org.springframework.stereotype.Repository;

import com.sumit.springboot.friendflow.entities.Image;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

	private EntityManager entityManager;
	
	public ImageRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Image i) {
		entityManager.merge(i);
	}

	@Override
	@Transactional
	public void delete(Image i) {
		entityManager.remove(i);
	}

}
