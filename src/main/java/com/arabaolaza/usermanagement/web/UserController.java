package com.arabaolaza.usermanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arabaolaza.usermanagement.aggregates.User;
import com.arabaolaza.usermanagement.aggregates.user.services.UserService;
import com.arabaolaza.usermanagement.aggregates.user.views.UsersView;
import com.arabaolaza.usermanagement.web.views.UserView;

import net.chrisrichardson.eventstore.EntityIdentifier;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private UsersView view;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void create(@RequestBody UserView view) {
		service.createUser(view.getAge(), view.getName(), view.getLastName());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestParam(name = "newName") String newName, @PathVariable(value = "id") String id) {
		EntityIdentifier entityId = new EntityIdentifier(id);
		service.updateUserName(entityId, newName);
	}
	

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public UserView details(@PathVariable(value = "id") String id) {
		EntityIdentifier entityId = new EntityIdentifier(id);
		return service.findDetails(entityId).map((u) -> toView(u.entity())).toBlocking().first();
		
	}
	
	private UserView toView(User entity) {
		UserView view=new UserView(entity.getUserId(),entity.getUserName(),entity.getUserLastName());
		return view;
	}

}
