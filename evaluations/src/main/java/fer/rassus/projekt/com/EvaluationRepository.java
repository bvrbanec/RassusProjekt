package fer.rassus.projekt.com;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {
	List<Evaluation> findByOwnerId(Long ownerId);

	List<Evaluation> findByDogId(Long dogId);

	List<Evaluation> findByWalkerId(Long walkerId);

	Evaluation findByWalkerIdAndDogId(Long walkerId, Long dogId);

	List<Evaluation> findAll();
}
