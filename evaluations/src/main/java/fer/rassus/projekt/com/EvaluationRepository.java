package fer.rassus.projekt.com;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface EvaluationRepository extends CrudRepository<Evaluation, Long>{

	
	List<Evaluation> findByownerId(Long ownerId);
	List<Evaluation> findBydogId(Long dogId);
	List<Evaluation> findBywalkerId(Long walkerId);
	List<Evaluation> findAll();
}
