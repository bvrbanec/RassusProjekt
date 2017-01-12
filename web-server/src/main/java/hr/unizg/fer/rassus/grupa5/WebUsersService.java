package hr.unizg.fer.rassus.grupa5;

import java.util.List;


public interface WebUsersService {
	
	User findById(long id);
	
	List<User> getAll();
	
	List<User> saveUserAndGetAll(User user);

	List<User> deactivateUserByIdAndGetAll(long id);

}
