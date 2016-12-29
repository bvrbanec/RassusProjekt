package hr.unizg.fer.rassus.grupa5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/walks/*")
public class WalksController {
	
	protected WalkRepository walkRepository;

	@Autowired
	public WalksController(WalkRepository walkRepository) {
		this.walkRepository = walkRepository;
	}
	
	@RequestMapping("/dog/{dogId}")
	public List<Walk> byDog(@PathVariable("dogId") Long dogId) {
		return walkRepository.findByDogId(dogId);
	}

	@RequestMapping("/walker/{walkerId}")
	public List<Walk> byWalker(@PathVariable("walkerId") Long walkerId) {
		return walkRepository.findByWalkerId(walkerId);
	}
	
	@RequestMapping("/owner/{ownerId}")
	public List<Walk> byOwner(@PathVariable("ownerId") Long ownerId) {
		return walkRepository.findByOwnerId(ownerId);
	}
	
	@RequestMapping("/active")
	public List<Walk> activeWalks(){
		return walkRepository.findByWalkerIdIsNull();
	}
	
	@RequestMapping(value = "/offer", method = RequestMethod.POST)
	public Walk offerWalk(@RequestBody Walk walk){
		return walkRepository.save(walk);
	}
	
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public Walk acceptOffer(@RequestBody Walk walk){
		return walkRepository.save(walk);
	}
}
