package com.codeup.springblog.services;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository usersDao;

	public UserService(UserRepository usersDao) {
		this.usersDao = usersDao;
	}

	//Temporary placeholder
	// In the future we will replace this with the current logged in user
	public User getLoggedInUser() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usersDao.findById(loggedInUser.getId()).get();
	}
}
