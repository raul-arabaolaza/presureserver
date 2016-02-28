package com.arabaolaza.usermanagement.aggregates.user.commands;

public class CreateUserCommand implements UserCommand {
	private Integer userId, userAge;
	private String userName, userLastName;

	public CreateUserCommand(Integer userId, Integer userAge, String userName, String userLastName) {
		super();
		this.userId = userId;
		this.userAge = userAge;
		this.userName = userName;
		this.userLastName = userLastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateUserCommand other = (CreateUserCommand) obj;
		if (userAge == null) {
			if (other.userAge != null)
				return false;
		} else if (!userAge.equals(other.userAge))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userAge == null) ? 0 : userAge.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

}
