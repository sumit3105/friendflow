package com.sumit.springboot.friendflow.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sumit.springboot.friendflow.entities.Post;
import com.sumit.springboot.friendflow.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class PostRepositoryImpl implements PostRepository {

	private EntityManager entityManager;

	public PostRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Post findById(int id) {
		return entityManager.find(Post.class, id);
	}

	@Override
	@Transactional
	public void save(Post p) {
		entityManager.merge(p);
	}

	@Override
	@Transactional
	public void delete(Post p) {
		entityManager.remove(p);
	}

	@Override
	public List<Post> findPostsOfFriends(User u) {
		String query = "SELECT p FROM Post p " + "JOIN p.user u "
				+ "JOIN Friendship f ON (f.user1.username = u.username OR f.user2.username = u.username) "
				+ "WHERE ((f.user1.username = :currentLoggedInUser OR f.user2.username = :currentLoggedInUser) "
				+ "AND f.status = true " + "AND u.username != :currentLoggedInUser) " + "ORDER BY p.postDate DESC";

		TypedQuery<Post> ty = entityManager.createQuery(query, Post.class);
		ty.setParameter("currentLoggedInUser", u.getUsername());
		return ty.getResultList();
	}

}
