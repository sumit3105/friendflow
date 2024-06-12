package com.sumit.springboot.friendflow.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="friendship_id")
    private Long id;

    @ManyToOne
    private User user1;

    @ManyToOne
    private User user2;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="friendship_date")
    private Date friendshipDate;
    
    //status for maintaining friendship
    //0 - for pending request and 1 - for accepted
    @Column(name="status")
    private Boolean status;

    public Friendship(User user1, User user2, Date friendshipDate, Boolean status) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.friendshipDate = friendshipDate;
		this.status = status;
	}
    
    

	public Friendship() {
		super();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Date getFriendshipDate() {
        return friendshipDate;
    }

    public void setFriendshipDate(Date friendshipDate) {
        this.friendshipDate = friendshipDate;
    }

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Friendship [id=" + id + ", user1=" + user1 + ", user2=" + user2 + ", friendshipDate=" + friendshipDate
				+ "]";
	}
}
