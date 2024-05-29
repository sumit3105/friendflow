package com.sumit.springboot.friendflow.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "posts")
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id")
	private int id;

	@Column(name="caption")
	private String caption;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="post_date")
    private Date postDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<Image> images;
	
	@Column(name="likes")
	private int Likes;
	
	@Column(name="dislikes")
	private int Dislikes;
	
	@Column(name="pinned", columnDefinition = "boolean default false")
	private boolean Pinned;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments;

	@Override
	public String toString() {
		return "Post [id=" + id + ", caption=" + caption + ", user=" + user + ", images=" + images + "]";
	}

	public Post() {}

	

	public Post(String caption, Date postDate, User user, List<Image> images) {
		super();
		this.caption = caption;
		this.postDate = postDate;
		this.user = user;
		this.images = images;
		this.Likes = 0;
		this.Dislikes = 0;
		this.Pinned = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getLikes() {
		return Likes;
	}

	public void setLikes(int likes) {
		Likes = likes;
	}

	public int getDislikes() {
		return Dislikes;
	}

	public void setDislikes(int dislikes) {
		Dislikes = dislikes;
	}

	public boolean isPinned() {
		return Pinned;
	}

	public void setPinned(boolean pinned) {
		Pinned = pinned;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}