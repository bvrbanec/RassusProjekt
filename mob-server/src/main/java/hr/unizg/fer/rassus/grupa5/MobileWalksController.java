package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/walks/*")
public class MobileWalksController {

	@Autowired
	protected MobileWalksService walksService;
	//protected MobileDogsService dogsService;

	public MobileWalksController(MobileWalksService walksService) {
		this.walksService = walksService;
		//this.dogsService = dogsService;
	}

	@RequestMapping("/dog/{dogId}")
	public List<Walk> byDog(@PathVariable("dogId") Long dogId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByDogId(dogId);
		// String dogName = dogsService.findDogNameById(dogId);
		for (Walk walk : walks) {
			if (walk.getWalkerId() != null)
				walk.setWalkerName("Tomo Tomina");
		}
		return walks;
	}

	@RequestMapping("/walker/{walkerId}")
	public List<Walk> byWalker(@PathVariable("walkerId") Long walkerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerId(walkerId);
		for (Walk walk : walks) {
			walk.setDogName("Bonzi");
			walk.setOwnerName("Simon Sayes");
		}
		return walks;
	}

	@RequestMapping("/owner/{ownerId}")
	public List<Walk> byOwner(@PathVariable("ownerId") Long ownerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByOwnerId(ownerId);
		for (Walk walk : walks) {
			walk.setDogName("Bonzi");
			if (walk.getWalkerId() != null)
				walk.setWalkerName("Tomo Tomina");
		}
		return walks;
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public List<Walk> activeWalks() {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerIdIsNull();
		for (Walk walk : walks) {
			// walk.setDogName(dogsService.findDogNameById(walk.getDogId()));
			walk.setDogName("Bonzi");
			walk.setOwnerName("Vladimir"); // Isto tako dohvatiti ime vlasnika
											// za owner ID -
											// webPeopleService.findNameById(ownerId)
		}
		return walks;
	}
/*
	@RequestMapping(value = "/offer", method = RequestMethod.GET)
	public String newOffer(Model model) {
		// List<Dog> dogs = dogsService.findByOwner(dogOwner);
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		Dog dog = new Dog();
		dog.setId((long) 1);
		dog.setName("Mongo");
		dogs.add(dog);

		model.addAttribute("walk", new Walk());
		model.addAttribute("dogs", dogs);
		return "create-offer";
	}
*/
	@RequestMapping(value = "/offer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Walk createOffer(Walk walk) {
		return walksService.offerWalk(walk);
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public Walk acceptOffer(@RequestParam("id") Long id, @RequestParam("walkerId") Long walkerId) {
		Walk walk = walksService.findById(id);
		walkerId = (long) 1;

		if (walk != null && walk.getWalkerId() == null) {
			walk.setWalkerId(walkerId);
			return walksService.acceptOffer(walk);
		} else
			return new Walk();
	}
}
