package com.blogspot.bartoszkomin.hibernate_many_to_many_demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.model.User;
import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.repository.UserRepository;

/**
 * @author Bartosz Komin
 * User rest controller
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	/**
	 * Save new user
	 * @param userRequestBody - JAVA representation of JSON request body
	 * @return User entity
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public User addNewUser(@Valid @RequestBody User userRequest) {
		
		return userRepository.save(userRequest);
	}
	
	/**
	 * Get user list
	 * @return List of Country entity
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<User> getUsers() {
		
		List<User> returnUserList = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(returnUserList::add);
		
		return returnUserList;
	}
	
	/**
	 * Get single user by Id
	 * @param userId - User Id
	 * @return User entity
	 */
	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public User getUserById(@PathVariable("user_id") Integer userId) {

		return userRepository.findOne(userId);
	}


}
