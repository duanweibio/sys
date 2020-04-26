package com.bean;

public class User {

	private Long id;
	private String username;
	private String age;
	private String sex;
	
	public User() {
		super();
	}
	
	public User(Long id, String username, String age, String sex) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
		this.sex = sex;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
