package com.arabaolaza.usermanagement.aggregates;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.arabaolaza.usermanagement.aggregates.user.commands.CreateUserCommand;
import com.arabaolaza.usermanagement.aggregates.user.commands.UpdateUserNameCommand;
import com.arabaolaza.usermanagement.aggregates.user.commands.UserCommand;
import com.arabaolaza.usermanagement.aggregates.user.events.UserCreatedEvent;
import com.arabaolaza.usermanagement.aggregates.user.events.UserUpdatedEvent;

import net.chrisrichardson.eventstore.Event;
import net.chrisrichardson.eventstore.EventUtil;
import net.chrisrichardson.eventstore.ReflectiveMutableCommandProcessingAggregate;

public class User extends ReflectiveMutableCommandProcessingAggregate<User, UserCommand> {

	private Log log = LogFactory.getLog(User.class);

	private Integer userId, userAge;
	private String userName, userLastName;

	public void apply(UserCreatedEvent event) {
		log.debug("Aplied user created event");
		userId = event.getUserId();
		userAge = event.getUserAge();
		userName = event.getUserName();
		userLastName = event.getUserLastName();
	}

	public void apply(UserUpdatedEvent event) {
		log.debug("Aplied user updated event");
		String newName = event.getNewName();
		userName = newName;

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

	public List<Event> process(CreateUserCommand cmd) {
		Integer userId = cmd.getUserId();
		Integer userAge = cmd.getUserAge();
		String userName = cmd.getUserName();
		String userLastName = cmd.getUserLastName();
		return EventUtil.events(new UserCreatedEvent(userId, userAge, userName, userLastName));
	}

	public List<Event> process(UpdateUserNameCommand cmd) {
		return EventUtil.events(new UserUpdatedEvent(cmd.getNewName()));
	}

}
