package hr.unizg.fer.rassus.g5;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface RegistrationRepository  extends CrudRepository<Registration, Long>{
	
	public Registration findByUsername(String username);
	List<Registration> findAll();
	Registration findByUsernameAndPassword(String username,String password);
	
}
