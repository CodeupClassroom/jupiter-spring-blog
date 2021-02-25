package com.codeup.springblog.services;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	private final UserRepository usersDao;

	public UserService(UserRepository usersDao) {
		this.usersDao = usersDao;
	}

	//Placeholder for "logged in user"
	public User loggedInUser(){
		return usersDao.findAll().get(0);
	}
}
