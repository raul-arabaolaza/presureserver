package com.arabaolaza.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.arabaolaza.usermanagement.aggregates.User;
import com.arabaolaza.usermanagement.aggregates.user.commands.UserCommand;

import net.chrisrichardson.eventstore.EventStore;
import net.chrisrichardson.eventstore.client.config.EventStoreHttpClientConfiguration;
import net.chrisrichardson.eventstore.javaapi.consumer.EnableJavaEventHandlers;
import net.chrisrichardson.eventstore.repository.AggregateRepository;
import net.chrisrichardson.utils.config.MetricRegistryConfiguration;

@Configuration
@Import({ EventStoreHttpClientConfiguration.class, MetricRegistryConfiguration.class })
@EnableJavaEventHandlers
public class EventuateConfig {

	@Bean
	public AggregateRepository<User, UserCommand> userRepository(EventStore eventStore) {
		return new AggregateRepository<User, UserCommand>(User.class, eventStore);
	}

}
