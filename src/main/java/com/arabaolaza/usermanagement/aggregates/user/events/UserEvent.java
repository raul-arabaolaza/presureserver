package com.arabaolaza.usermanagement.aggregates.user.events;

import net.chrisrichardson.eventstore.Event;
import net.chrisrichardson.eventstore.EventEntity;

@EventEntity(entity = "com.arabaolaza.usermanagement.aggregates.User")
public interface UserEvent extends Event {

}
