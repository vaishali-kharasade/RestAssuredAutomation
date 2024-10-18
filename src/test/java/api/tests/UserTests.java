package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	Logger logger;
	
	@BeforeClass
	public void setUp() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void postUserTest() {
		
		logger.info("This is test to create the user");
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("User is created successfully");
	}
	
	@Test(priority=2)
	public void getUserDetailsTest() {
		Response response = UserEndpoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority=3)
	public void updateUserDetailsTest() {
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		
		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		Response updatedResponse = UserEndpoints.getUser(this.userPayload.getUsername());
		updatedResponse.then().log().body().toString().contains(this.userPayload.getFirstName());
	}
	
	@Test(priority=4)
	public void deleteUserTest() {
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().body().statusCode(200);
	}

}
