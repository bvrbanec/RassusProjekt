package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WebUsersServiceImpl implements WebUsersService{
	
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	protected String usersServiceUrl;
	
	public WebUsersServiceImpl(String usersServiceUrl) {
		this.usersServiceUrl = usersServiceUrl.startsWith("http") ? usersServiceUrl : "http://" + usersServiceUrl;
	}
	
	@Override
	public User findById(long id) {
		return restTemplate.getForObject(usersServiceUrl + "/{id}", User.class, id);
	}
	List<User> users;
	
	public List<User> getDummyList(){
		List<User> list = new ArrayList<>();
		for (int i = 0; i < 4; ++i) {
			User user = new User();
			user.setId(Long.valueOf(i));
			user.setFirstName("Probnoime");
			user.setLastName("probnoprezime");
			user.setUsername("korisnickoime");
			user.setActive(true);
			user.setEmail("mail");
			user.setTelephoneNumber("tel");
			user.setWalker(true);
			list.add(user);
		}
		users = list;
		return list;
	}
	
	public User getById(long id){
		return users.get((int) id);
	}
	
	@Override
	public List<User> getAll() {
		ResponseEntity<List<User>> usersResponse =
		        restTemplate.exchange(usersServiceUrl,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
		            });

		List<User> users = usersResponse.getBody();;
		return users;
	}

	@Override
	public List<User> deactivateUserByIdAndGetAll(long id) {
		ResponseEntity<List<User>> usersResponse =
		        restTemplate.exchange(usersServiceUrl + "/{id}",
		                    HttpMethod.DELETE, null, new ParameterizedTypeReference<List<User>>() {
		            }, id);
		List<User> users = usersResponse.getBody();
		return users;
	}
	
	@Override
	public List<User> saveUserAndGetAll(User user) {
		ResponseEntity<List<User>> usersResponse =
		        restTemplate.exchange(usersServiceUrl,
		                    HttpMethod.POST, new HttpEntity<User>(user), new ParameterizedTypeReference<List<User>>() {
		            });
		List<User> users = usersResponse.getBody();
		
		return users;
	}
	
	public User saveUser(User user) {
		return restTemplate.postForObject(usersServiceUrl + "/regUser", user, User.class);
		
	}
	
	@Override
	public List<User> getAllWalkers() {
		ResponseEntity<List<User>> usersResponse =
		        restTemplate.exchange(usersServiceUrl + "/walkers",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
		            });

		List<User> users = usersResponse.getBody();;
		return users;
	}

}
