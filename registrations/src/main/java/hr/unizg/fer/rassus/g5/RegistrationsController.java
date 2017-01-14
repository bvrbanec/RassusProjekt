package hr.unizg.fer.rassus.g5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/registrations/*")
public class RegistrationsController {
	
	private RegistrationRepository rp;
	
	@Autowired
	public RegistrationsController(RegistrationRepository rr) {
		this.rp= rr;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	 public Registration checkIfUserExists(@RequestBody Login login){
	    
	    Registration reg;

	    try { 
	      
	      reg=rp.findByUsernameAndPassword(login.getUsername(), login.getPassword());
	      if(reg!=null)
	    	  System.out.println("ima takav username");
	      
	    }
	    catch (Exception ex) {
	    	System.out.println("fail");
	    	return null;
	    }
	    return   reg;
	  }
	
	

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	 public List<Registration> getAllUsersReg(){
	    
	    try { 
	      return  rp.findAll();
	    }
	    catch (Exception ex) {
	      return null;
	    }
	  }
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	 public Registration createRegistration(@RequestBody Registration registration){
	    
	    Registration reg;
	    try { 
	      reg = rp.save(new Registration(registration));
	    }
	    catch (Exception ex) {
	      return null;
	    }
	    return reg;
	  }
	
	

}
