package hr.unizg.fer.rassus.grupa5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebHomeController {
	
	@Autowired
	protected WebHomeService homeService;

	public WebHomeController(WebHomeService homeService) {
		this.homeService = homeService;
	}
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String register(Model model) {
		return "home";
	}
}
