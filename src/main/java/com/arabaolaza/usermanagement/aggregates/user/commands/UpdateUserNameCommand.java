package com.arabaolaza.usermanagement.aggregates.user.commands;

public class UpdateUserNameCommand implements UserCommand {

	private String newName;

	public UpdateUserNameCommand(String newName) {
		super();
		this.newName = newName;
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
		UpdateUserNameCommand other = (UpdateUserNameCommand) obj;
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

	public String getNewName() {
		return newName;
	}

}
