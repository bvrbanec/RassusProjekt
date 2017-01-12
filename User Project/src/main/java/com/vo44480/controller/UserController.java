package com.vo44480.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.vo44480.model.User;
import com.vo44480.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping(path="/info", method=RequestMethod.GET)
	public String test(){
		StringBuilder sb = new StringBuilder();
		sb.append("Profili: ").append(String.join(", ", environment.getActiveProfiles()));
		sb.append(System.lineSeparator());
		try {
			sb.append("DataSource; ").append(dataSource.getConnection().getMetaData().getURL().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	
	@RequestMapping(path="/hello", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public User hello() {
		User u = new User();
		u.setFirstName("ime");
		u.setLastName("prezime");
		u.setUsername("bbb");
		u.setEmail("vladimir"+UUID.randomUUID().toString()+"@");
		return userService.saveUser(u);
	}
	
	@RequestMapping(path="/special/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String getUserTailorMade(long id){
		User u = findById(id);
		return u.getFirstName() + u.getLastName();
	}
	
	@RequestMapping(method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<User> getAllUsers(){
		return userService.getAll();
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public User findById(@PathVariable long id) {
		return userService.findById(id);
	}

	@RequestMapping(path="/firstNameLike/{firstName}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<User> findByFirstNameLike(String firstName) {
		return userService.findByFirstNameLike(firstName);
	}

	@RequestMapping(path="/firstName/{firstName}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public User findByFirstName(String firstName) {
		return userService.findByFirstName(firstName);
	}

	@RequestMapping(path="/lastNameLike/{lastName}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<User> findByLastNameLike(String lastName) {
		return userService.findByLastNameLike(lastName);
	}

	@RequestMapping(path="/lastName/{lastName}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public User findByLastName(String lastName) {
		return userService.findByLastName(lastName);
	}

	@RequestMapping(path="/username/{username}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public User findByUsername(String username) {
		return userService.findByUsername(username);
	}

	@RequestMapping(method=RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<User> saveUser(@RequestBody User user) {
		return userService.saveUserAndGetAll(user);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<User> deleteUser(@PathVariable long id){
		return userService.deactivateUserByIdAndGetAll(id);
	}

}
