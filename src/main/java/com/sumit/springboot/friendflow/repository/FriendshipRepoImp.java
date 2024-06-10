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

		TypedQuery<Friendship> ty = entityManager.createQuery(
				"FROM Friendship f WHERE f.user2.username = :uname AND f.status=false ORDER BY f.user1.username FETCH FIRST 3 ROWS ONLY",
				Friendship.class);
		ty.setParameter("uname", u.getUsername());
		return ty.getResultList();

	}

	@Override
	public Friendship findById(Long id) {
		Friendship f = entityManager.find(Friendship.class, id);
		return f;
	}

	@Override
	@Transactional
	public void delete(Friendship f) {
		entityManager.remove(f);
	}

	@Override
	public List<User> findAllFriends(String username) {
		String query = "SELECT DISTINCT u FROM User u "
				+ "JOIN Friendship f ON (f.user1.username = u.username OR f.user2.username = u.username) "
				+ "WHERE ((f.user1.username = :currentLoggedInUser OR f.user2.username = :currentLoggedInUser) "
				+ "AND f.status = true " + "AND u.username != :currentLoggedInUser)";

		TypedQuery<User> ty = entityManager.createQuery(query, User.class);
		ty.setParameter("currentLoggedInUser", username);

		return ty.getResultList();

	}
}
