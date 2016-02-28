package com.arabaolaza.usermanagement.aggregates.user.events;

public class UserUpdatedEvent implements UserEvent {

	private String newName;

	public UserUpdatedEvent() {
		super();
	}

	public String getNewName() {
		return newName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((newName == null) ? 0 : newName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserUpdatedEvent other = (UserUpdatedEvent) obj;
		if (newName == null) {
			if (other.newName != null)
				return false;
		} else if (!newName.equals(other.newName))
			return false;
		return true;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public UserUpdatedEvent(String newName) {
		super();
		this.newName = newName;
	}

}
