package com.arabaolaza.usermanagement.web.views;

import org.springframework.hateoas.ResourceSupport;

public class UserView extends ResourceSupport {

	private Integer age;
	private String name, lastName;

	public UserView() {
		super();
	}

	public UserView(Integer age, String name, String lastName) {
		super();
		this.age = age;
		this.name = name;
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setName(String name) {
		this.name = name;
	}
}
