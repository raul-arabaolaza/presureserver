package com.arabaolaza.usermanagement.handlers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.arabaolaza.usermanagement.aggregates.user.events.UserCreatedEvent;
import com.arabaolaza.usermanagement.aggregates.user.events.UserUpdatedEvent;
import com.arabaolaza.usermanagement.aggregates.user.views.UsersView;
import com.arabaolaza.usermanagement.web.views.UserView;

import net.chrisrichardson.eventstore.subscriptions.CompoundEventHandler;
import net.chrisrichardson.eventstore.subscriptions.DispatchedEvent;
import net.chrisrichardson.eventstore.subscriptions.EventHandlerMethod;
import net.chrisrichardson.eventstore.subscriptions.EventSubscriber;
import rx.Observable;

@EventSubscriber(id = "userEventHandlers")
@Component
public class UserWorkflow implements CompoundEventHandler {

	private Log log = LogFactory.getLog(UserWorkflow.class);

	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private UsersView usersView;

	@EventHandlerMethod
	public Observable<Object> notifyNewUser(DispatchedEvent<UserCreatedEvent> ctx) {
		template.convertAndSend("/topic/users", "Created user with id "+ctx.getEntityIdentifier());
		template.convertAndSend("/topic/users", ctx.event());
		UserView view=new UserView(ctx.event().getUserAge(),ctx.event().getUserName(), ctx.event().getUserLastName());
		usersView.getCurrentUsers().put(ctx.getEntityIdentifier().getId(), view);
		return Observable.just(ctx.event());
	}
	
	@EventHandlerMethod
	public Observable<Object> notifyUpdatedUser(DispatchedEvent<UserUpdatedEvent> ctx) {
		log.debug("About to invoke update user handler");
		template.convertAndSend("/topic/users", "Updated user with id "+ctx.getEntityIdentifier());
		template.convertAndSend("/topic/users", ctx.event());
		log.debug("About to update users view");
		UserView view=usersView.getCurrentUsers().get(ctx.getEntityIdentifier().getId());
		view.setName(ctx.event().getNewName());
		usersView.getCurrentUsers().put(ctx.getEntityIdentifier().getId(), view);
		return Observable.just(ctx.event());
	}

}
