package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/evaluations/*")
public class WebEvaluationsController {

	@Autowired
	protected WebEvaluationsService evalsService;

	public WebEvaluationsController(WebEvaluationsService evalsService) {
		this.evalsService = evalsService;
	}

	@RequestMapping("/dog/{dogId}")
	public String byDog(Model model, @PathVariable("dogId") Long dogId) {
		List<Evaluation> evs = new ArrayList<Evaluation>();
		evs = evalsService.findByDogId(dogId);
		model.addAttribute("dogEvals", evs);
		return "dog-evals";
	}

	@RequestMapping("/walker/{walkerId}")
	public String byWalker(Model model, @PathVariable("walkerId") Long walkerId) {
		List<Evaluation> evs = new ArrayList<Evaluation>();
		evs = evalsService.findByOwnerId(walkerId);
		model.addAttribute("walkerEvals", evs);
		return "walker-evals";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getall(Model model) {
		List<Evaluation> evals = new ArrayList<Evaluation>();
		evals = evalsService.findAll();
		model.addAttribute("evaluations", evals);
		return "evaluations-all";
	}

	// @RequestMapping(value = "/rate-dog/{dogId}", method = RequestMethod.GET)
	public String rateDog(Model model, HttpSession session, @PathVariable("dogId") Long dogId) {
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		Evaluation eval = evalsService.checkExisting(reg.getPersonId(), dogId);
		if (eval == null) {
			return "redirect:/dogs/" + dogId;
		}

		model.addAttribute("dogEvaluation", eval);
		model.addAttribute("dogId", dogId);
		return "add-evaluation";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String postDogReview(@RequestParam("dogId") Long dogId, Evaluation evaluation, HttpSession session,
			HttpServletRequest request) {
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		Long walkerId = reg.getPersonId();

		Evaluation oldEval = evalsService.checkExisting(walkerId, evaluation.getDogId());
		oldEval.setDogComment(evaluation.getDogComment());
		oldEval.setDogRating(evaluation.getDogRating());

		evalsService.save(oldEval);

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String postWalkerReview(@RequestParam("walkerId") Long walkerId, Evaluation evaluation, HttpSession session,
			HttpServletRequest request) {
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		Long ownerId = reg.getPersonId();

		Evaluation oldEval = evalsService.checkExisting(walkerId, evaluation.getDogId());
		oldEval.setWalkerComment(evaluation.getWalkerComment());
		oldEval.setWalkerRating(evaluation.getWalkerRating());
		oldEval.setOwnerId(ownerId);

		evalsService.save(oldEval);

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

}
