package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

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
		System.out.println("tutu sam");
		evals = evalsService.findAll();
		System.out.println("tutu sam2");
		model.addAttribute("evaluations", evals);
		return "evaluations-all";
	}
	//ja sam šetač pasa
	@RequestMapping(value = "/addEvOfDog", method = RequestMethod.GET)
	public String register(Model model,HttpSession session) {
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		model.addAttribute("evaluation", new Evaluation());
		return "add-evaluation";
	}
	
}
