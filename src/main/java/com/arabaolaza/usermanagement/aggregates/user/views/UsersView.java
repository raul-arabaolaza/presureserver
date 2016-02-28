package com.arabaolaza.usermanagement.aggregates.user.views;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.arabaolaza.usermanagement.web.views.UserView;

@Service
public class UsersView {
	
	private Map<String, UserView>currentUsers=new HashMap<String, UserView>();

	public Map<String, UserView> getCurrentUsers() {
		return currentUsers;
	}


}
