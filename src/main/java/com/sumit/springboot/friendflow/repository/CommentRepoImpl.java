package com.sumit.springboot.friendflow.repository;

import org.springframework.stereotype.Repository;

import com.sumit.springboot.friendflow.entities.Comment;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class CommentRepoImpl implements CommentRepo {

	private EntityManager entityManager;
	
	public CommentRepoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Comment c) {
		entityManager.merge(c);
	}

	@Override
	@Transactional
	public void delete(Comment c) {
		entityManager.remove(c);
	}

}
