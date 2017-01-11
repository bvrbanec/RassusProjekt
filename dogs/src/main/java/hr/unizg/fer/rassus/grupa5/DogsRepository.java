package hr.unizg.fer.rassus.grupa5;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DogsRepository extends CrudRepository<Dog, Long> {
	
	List<Dog> findById(Long Id);
	
	List<Dog> findByOwner(String dogOwner);

	List<Dog> findByBreed(String dogBreed);

	List<Dog> findByGender(String dogGender);
	
	List<Dog> findByHeight(Double dogHeight);

	List<Dog> findByWeight(Double dogWeight);
	
	String findNameById(Long Id);
	
}
