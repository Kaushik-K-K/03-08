package com.spring;

import java.util.Date;

public class Message {

	private int id;
	private String content;
	private Date date;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(int id, String content, Date date) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", date=" + date + "]";
	}

}
