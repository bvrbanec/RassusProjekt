package com.vo44480.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo44480.model.User;
import com.vo44480.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findByFirstNameLike(String firstName) {
		return userRepository.findByFirstNameLike(firstName);
	}

	@Override
	public User findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	@Override
	public List<User> findByLastNameLike(String lastName) {
		return userRepository.findByLastNameLike(lastName);
	}

	@Override
	public User findByLastName(String lastName) {
		return userRepository.findByLastName(lastName);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findByActiveTrue();
	}
	
	@Override
	public List<User> saveUserAndGetAll(User user) {
		userRepository.save(user);
		return getAll();
	}
	
	@Override
	public List<User> deactivateAndGetAll(User user) {
		user.setActive(false);
		return saveUserAndGetAll(user);
	}
	
	@Override
	public List<User> deactivateUserByIdAndGetAll(long id) {
		return deactivateAndGetAll(userRepository.findOne(id));
	}
	
	@Override
	public List<User> getAllActiveWalkers(){
		return userRepository.findByWalkerTrueAndActiveTrue();
	}
	

}
