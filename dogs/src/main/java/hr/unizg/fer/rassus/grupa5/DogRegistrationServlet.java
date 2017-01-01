package hr.unizg.fer.rassus.grupa5;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

/**
 * Servlet implementation class DogRegistrationServlet
 */
@WebServlet("/DogRegistrationServlet")
public class DogRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer id = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DogRegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("dogName");
		String breed = request.getParameter("dogBreed");
		String owner = request.getParameter("dogOwner");
		Double age = Double.parseDouble(request.getParameter("dogAge"));
		String gender = request.getParameter("dogGender");
		String health = request.getParameter("dogHealthCondition");
		String hairColour = request.getParameter("dogHairColour");
		String eyeColour = request.getParameter("dogEyeColour");
		Double weight = Double.parseDouble(request.getParameter("dogWeight"));
		Double height = Double.parseDouble(request.getParameter("dogHeight"));
		String hairLength = request.getParameter("dogHairLength");

		Dog dog = new Dog();
		// dog.setId(id);
		dog.setName(name);
		dog.setBreed(breed);
		dog.setOwner(owner);
		dog.setAge(age);
		dog.setGender(gender);
		dog.setHealth(health);
		dog.setHairColour(hairColour);
		dog.setEyeColour(eyeColour);
		dog.setWeight(weight);
		dog.setHeight(height);
		dog.setHairLength(hairLength);

		System.out.println(dog);
		saveInDB(dog);

	}

	public static void saveInDB(Dog saveDog) throws UnknownHostException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		/*
		 * @SuppressWarnings("deprecation") MongoOperations mongoOps = new
		 * MongoTemplate(new Mongo(), "dogs");
		 */

		mongoOperation.insert(saveDog);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
