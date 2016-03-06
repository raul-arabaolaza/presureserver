package com.arabaolaza.usermanagement.aggregates.user.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arabaolaza.usermanagement.aggregates.User;
import com.arabaolaza.usermanagement.aggregates.user.commands.CreateUserCommand;
import com.arabaolaza.usermanagement.aggregates.user.commands.UpdateUserNameCommand;
import com.arabaolaza.usermanagement.aggregates.user.commands.UserCommand;

import net.chrisrichardson.eventstore.EntityIdentifier;
import net.chrisrichardson.eventstore.EntityWithIdAndVersion;
import net.chrisrichardson.eventstore.EventStore;
import net.chrisrichardson.eventstore.repository.AggregateRepository;
import net.chrisrichardson.eventstore.util.ServiceUtil;
import rx.Observable;

@Service
public class UserService {
	@Autowired
	private AggregateRepository<User, UserCommand> userRepository;
	
	@Autowired
	private EventStore store;
	
	private final Random random = new Random(System.currentTimeMillis());

	public Observable<EntityWithIdAndVersion<User>> createUser(Integer age, String name, String lastName) {
		Integer userId = random.nextInt();
		return userRepository.save(new CreateUserCommand(userId, age, name, lastName));
	}
	
	public Observable<EntityWithIdAndVersion<User>> updateUserName(EntityIdentifier identifier, String newName) {
		Observable<EntityWithIdAndVersion<User>> toReturn=userRepository.update(identifier,new UpdateUserNameCommand(newName));
		toReturn.subscribe((u) -> System.out.println(u));
		return toReturn;
		
	}
	
	public Observable<EntityWithIdAndVersion<User>> findDetails(EntityIdentifier identifier){
		return store.find(User.class, identifier).map((u)-> new EntityWithIdAndVersion<User>(u.entityIdAndVersion(),new User()));
		
	}

}
