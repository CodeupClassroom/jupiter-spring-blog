package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(11) UNSIGNED")
	private long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String body;

	public Post() {
	}

	public Post(long id, String title, String body) {
		this.title = title;
		this.body = body;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
