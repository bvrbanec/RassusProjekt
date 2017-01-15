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
public class WebWalksController {

	@Autowired
	protected WebWalksService walksService;
	protected WebDogsService dogsService;

	public WebWalksController(WebWalksService walksService, WebDogsService dogsService) {
		this.walksService = walksService;
		this.dogsService = dogsService;
	}

	@RequestMapping("/dog/{dogId}")
	public String byDog(Model model, @PathVariable("dogId") Long dogId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByDogId(dogId);
		// String dogName = dogsService.findDogNameById(dogId);
		for (Walk walk : walks) {
			if (walk.getWalkerId() != null)
				walk.setWalkerName("Tomo Tomina");
		}
		model.addAttribute("dogWalks", walks);
		// model.addAttribute("dogName", dogName);
		model.addAttribute("dogName", "Bonzi");
		return "dog-walks";
	}

	@RequestMapping("/walker/{walkerId}")
	public String byWalker(Model model, @PathVariable("walkerId") Long walkerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerId(walkerId);
		for (Walk walk : walks) {
			walk.setDogName("Bonzi");
			walk.setOwnerName("Simon Sayes");
		}
		model.addAttribute("walkerWalks", walks);
		model.addAttribute("walkerName", "Tomo Tomina");
		return "walker-walks";
	}

	@RequestMapping("/owner/{ownerId}")
	public String byOwner(Model model, @PathVariable("ownerId") Long ownerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByOwnerId(ownerId);
		for (Walk walk : walks) {
			walk.setDogName("Bonzi");
			if (walk.getWalkerId() != null)
				walk.setWalkerName("Tomo Tomina");
		}
		model.addAttribute("ownerWalks", walks);
		model.addAttribute("ownerName", "Simon Sayes");
		return "owner-walks";
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String activeWalks(Model model) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerIdIsNull();
		for (Walk walk : walks) {
			// walk.setDogName(dogsService.findDogNameById(walk.getDogId()));
			walk.setDogName("Bonzi");
			walk.setOwnerName("Vladimir"); // Isto tako dohvatiti ime vlasnika
											// za owner ID -
											// webPeopleService.findNameById(ownerId)
		}
		model.addAttribute("activeWalks", walks);
		return "active-walks";
	}

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

	@RequestMapping(value = "/offer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createOffer(Walk walk, HttpSession session) {
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		Long ownerId = reg.getPersonId();
		walk.setOwnerId(ownerId);
		walksService.offerWalk(walk);
		return "redirect:/walks/active";
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public String acceptOffer(@RequestParam("id") Long id, Model model, HttpSession session) {
		Walk walk = walksService.findById(id);
		// Long walkerId = (long) 1; // get currently logged in user
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		Long walkerId = reg.getPersonId();
		if (walk != null && walk.getWalkerId() == null) {
			walk.setWalkerId(walkerId);
			walk = walksService.acceptOffer(walk);
			// Person owner = peopleService.getById(walk.ownerId);
			// model.addAttribute(owner);
			String ownerName = "Vladimir";
			model.addAttribute(ownerName);
			return "owner-connect";
		} else
			return "error";
	}

}
