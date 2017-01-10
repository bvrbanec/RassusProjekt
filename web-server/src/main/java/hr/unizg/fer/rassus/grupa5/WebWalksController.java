package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public WebWalksController(WebWalksService walksService) {
		this.walksService = walksService;
	}

	@RequestMapping("/dog/{dogId}")
	public String byDog(Model model, @PathVariable("dogId") Long dogId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByDogId(dogId);
		model.addAttribute("dogWalks", walks);
		return "dog-walks";
	}

	@RequestMapping("/walker/{walkerId}")
	public String byWalker(Model model, @PathVariable("walkerId") Long walkerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerId(walkerId);
		model.addAttribute("walkerWalks", walks);
		return "walker-walks";
	}

	@RequestMapping("/owner/{ownerId}")
	public String byOwner(Model model, @PathVariable("ownerId") Long ownerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerId(ownerId);
		model.addAttribute("ownerWalks", walks);
		return "owner-walks";
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String activeWalks(Model model) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerIdIsNull();
		for (Walk walk : walks) {
		   walk.setDogName("Mongo"); //Ideja je dohvatiti ime psa preko dogs servisa za dog ID - webDogsService.findNameById(dogId)
		   walk.setOwnerName("Vladimir"); //Isto tako dohvatiti ime vlasnika za owner ID - webPeopleService.findNameById(ownerId)
		}
		model.addAttribute("activeWalks", walks);
		return "active-walks";
	}
/*
	@RequestMapping(value = "/offer", method = RequestMethod.POST)
	public Walk offerWalk(@RequestBody Walk walk) {
		return walksService.offerWalk(walk);
	}
*/
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public String acceptOffer(@RequestParam("id") Long id, Model model) {
		Walk walk = walksService.findById(id);
		Long walkerId = (long) 1; //get currently logged in user
		if (walk != null && walk.getWalkerId() == null) {
			walk.setWalkerId(walkerId);
			walk = walksService.acceptOffer(walk);
			// Person owner = peopleService.getById(walk.ownerId);
			//model.addAttribute(owner);
			String ownerName = "Vladimir";
			model.addAttribute(ownerName);
			return "owner-connect";
		}
		else return "error";
	}

}
