package fer.rassus.projekt.com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluations/*")
public class EvaluationsController {
	private EvaluationRepository rp;

	@Autowired
	public EvaluationsController(EvaluationRepository rr) {
		this.rp = rr;
	}

	@RequestMapping("/owner/{ownerId}")
	public List<Evaluation> byOwner(@PathVariable("ownerId") Long ownerId) {
		return rp.findByOwnerId(ownerId);
	}

	@RequestMapping("/dog/{dogId}")
	public List<Evaluation> byDog(@PathVariable("dogId") Long dogId) {
		return rp.findByDogId(dogId);
	}

	@RequestMapping("/walker/{walkerId}")
	public List<Evaluation> byWalker(@PathVariable("walkerId") Long walkerId) {
		return rp.findByWalkerId(walkerId);
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public Evaluation byWalkerAndDog(@RequestBody Evaluation evaluation) {
		return rp.findByWalkerIdAndDogId(evaluation.getWalkerId(), evaluation.getDogId());
	}

	@RequestMapping("/all")
	public List<Evaluation> allEval() {
		return rp.findAll();
	}

	@RequestMapping(path = "/save", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Evaluation saveEvalAndReturn(@RequestBody Evaluation eval) {
		return rp.save(eval);
	}
}
