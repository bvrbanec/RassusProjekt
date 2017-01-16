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
	protected WebUsersService usersService;
	protected WebEvaluationsService evalService;

	public WebWalksController(WebWalksService walksService, WebDogsService dogsService, WebUsersService usersService,
			WebEvaluationsService evalService) {
		this.walksService = walksService;
		this.dogsService = dogsService;
		this.usersService = usersService;
		this.evalService = evalService;
	}

	@RequestMapping("/dog/{dogId}")
	public String byDog(Model model, @PathVariable("dogId") Long dogId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByDogId(dogId);
		Dog dog = dogsService.findById(dogId);
		for (Walk walk : walks) {
			if (walk.getWalkerId() != null) {
				User user = usersService.findById(walk.getWalkerId());
				String name = user.getFirstName() + " " + user.getLastName();
				walk.setWalkerName(name);
			}
		}
		model.addAttribute("dogWalks", walks);
		model.addAttribute("dogName", dog.getName());
		return "dog-walks";
	}

	@RequestMapping("/walker/{walkerId}")
	public String byWalker(Model model, @PathVariable("walkerId") Long walkerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerId(walkerId);
		for (Walk walk : walks) {
			Dog dog = dogsService.findById(walk.getDogId());
			walk.setDogName(dog.getName());
			User user = usersService.findById(walk.getOwnerId());
			String name = user.getFirstName() + " " + user.getLastName();
			walk.setOwnerName(name);
		}
		model.addAttribute("walkerWalks", walks);
		User user = usersService.findById(walkerId);
		String name = user.getFirstName() + " " + user.getLastName();
		model.addAttribute("walkerName", name);
		return "walker-walks";
	}

	@RequestMapping("/owner/{ownerId}")
	public String byOwner(Model model, @PathVariable("ownerId") Long ownerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByOwnerId(ownerId);
		for (Walk walk : walks) {
			Dog dog = dogsService.findById(walk.getDogId());
			walk.setDogName(dog.getName());
			if (walk.getWalkerId() != null) {
				User user = usersService.findById(walk.getWalkerId());
				String name = user.getFirstName() + " " + user.getLastName();
				walk.setWalkerName(name);
			}

		}
		model.addAttribute("ownerWalks", walks);
		User user = usersService.findById(ownerId);
		String name = user.getFirstName() + " " + user.getLastName();
		model.addAttribute("ownerName", name);
		return "owner-walks";
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String activeWalks(Model model) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerIdIsNull();
		for (Walk walk : walks) {
			Dog dog = dogsService.findById(walk.getDogId());
			walk.setDogName(dog.getName());

			User user = usersService.findById(walk.getOwnerId());
			String name = user.getFirstName() + " " + user.getLastName();
			walk.setOwnerName(name);

		}
		model.addAttribute("activeWalks", walks);
		return "active-walks";
	}

	@RequestMapping(value = "/offer", method = RequestMethod.GET)
	public String newOffer(Model model, HttpSession session) {
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		Long ownerId = reg.getPersonId();
		List<Dog> dogs = dogsService.findByOwnerId(ownerId);

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
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		Long walkerId = reg.getPersonId();
		if (walk != null && walk.getWalkerId() == null) {
			walk.setWalkerId(walkerId);
			walk = walksService.acceptOffer(walk);

			Evaluation eval = new Evaluation();
			eval.setDogId(walk.getDogId());
			eval.setOwnerId(walk.getOwnerId());
			eval.setWalkerId(walkerId);
			if (evalService.checkExisting(walkerId, walk.getDogId()) == null) {
				evalService.save(eval);
			}
			User user = usersService.findById(walk.getOwnerId());

			model.addAttribute("owner", user);
			return "owner-connect";
		} else
			return "error";
	}

}
