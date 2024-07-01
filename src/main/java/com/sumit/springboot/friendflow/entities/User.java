package com.sumit.springboot.friendflow.entities;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="fname")
	private String firstName;
	
	@Column(name="lname")
	private String lastName;
	
	@Column(name="profile_details")
	private String profileDetails;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_img") 
	private Image profileImage;
	
	@OneToMany(mappedBy = "user1", fetch = FetchType.EAGER)
    private List<Friendship> friendships;
	
	public User() {
		super();
	}

	public User(String username, String password, String firstName, String lastName, String profileDetails,
			List<Friendship> friendships, Image profileImage) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profileDetails = profileDetails;
		this.friendships = friendships;
		this.profileImage = profileImage;
		this.friendships = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "User [username=" + username + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfileDetails() {
		return profileDetails;
	}

	public void setProfileDetails(String profileDetails) {
		this.profileDetails = profileDetails;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Friendship> getFriendships() {
		return friendships;
	}

	public void setFriendships(List<Friendship> friendships) {
		this.friendships = friendships;
	}

	public Image getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(Image profileImage) {
		this.profileImage = profileImage;
	}	
}