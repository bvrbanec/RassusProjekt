package hr.unizg.fer.rassus.grupa5;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WalkRepository extends CrudRepository<Walk, Long> {

	List<Walk> findByDogId(Long dogId);

	List<Walk> findByWalkerId(Long walkerId);
	
	List<Walk> findByOwnerId(Long ownerId);

	List<Walk> findByWalkerIdIsNull();

}
