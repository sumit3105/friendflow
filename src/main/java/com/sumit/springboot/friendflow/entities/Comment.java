package com.sumit.springboot.friendflow.entities;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="comments")
public class Comment{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id")
	private int commentId;
	
	// comment on which post
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	
	// comment by which user
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	@Column(name="comment")
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cmt_date")
	private Date date;
	
	@Column(name="is_edited", columnDefinition = "boolean default false")
	private boolean edited;
	
	public Comment() {}
	
	public Comment(Post post, User user, String comment, Date date, boolean edited) {
		super();
		this.post = post;
		this.user = user;
		this.comment = comment;
		this.date = date;
		this.edited = edited;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}
}