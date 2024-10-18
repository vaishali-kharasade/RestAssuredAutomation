package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.UserDataProvider;
import io.restassured.response.Response;


public class DataDrivenTests {
	Faker faker = new Faker();
	
	@Test(priority=1, dataProvider="UserDP", dataProviderClass = UserDataProvider.class)
	public void testCreationUser(User userPayloadData) {
		Response response = UserEndpoints.createUser(userPayloadData);
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println(response.then().log().body());
	}
	
	@Test(priority=2,dataProvider="UserDP", dataProviderClass = UserDataProvider.class)
	public void testGetUserRequest(User userPayloadData) {
		Response response = UserEndpoints.getUser(userPayloadData.getUsername());
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println(response.then().log().body());
	}
	
	@Test(priority=3,dataProvider="UserDP", dataProviderClass = UserDataProvider.class)
	public void testUpdateDetailsRequest(User userPayloadData) {
		userPayloadData.setFirstName(faker.name().firstName());
		userPayloadData.setLastName(faker.name().lastName());
		Response response = UserEndpoints.updateUser(userPayloadData.getUsername(), userPayloadData);
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println(response.then().log().body());
		Response updatedResponse = UserEndpoints.getUser(userPayloadData.getUsername());
		updatedResponse.then().log().body().toString().contains(userPayloadData.getFirstName());
		System.out.println(response.then().log().body());
	}
	
	@Test(priority=4,dataProvider="UserDP", dataProviderClass = UserDataProvider.class)
	public void testDeleteUserRequest(User userPayloadData) {
		Response response = UserEndpoints.deleteUser(userPayloadData.getUsername());
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println(response.then().log().body());
	}
	
	

}
