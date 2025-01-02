package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.PetEndpoints;
import api.payloads.Pet;
import api.utilities.PetDataProvider;
import io.restassured.response.Response;

public class PetTests {
	
	int petId = 101;
	int petIdNegative = 105;
	
		@Test(priority=1, dataProvider="PetDP", dataProviderClass = PetDataProvider.class)
		public void testAddNewPet(Pet addNewPetData) {
			Response response = PetEndpoints.addNewPet(addNewPetData);
			Assert.assertEquals(response.statusCode(), 200);
			System.out.println(response.then().log().body());
		}
		
		@Test(priority=2)
		public void getPetDetailsByIdTest() {
			Response response = PetEndpoints.getPetDetails(petId);
			Assert.assertEquals(response.statusCode(), 200);
			System.out.println(response.then().log().body());
		}
		
		@Test(priority=2)
		public void getPetDetailsByIdNegativeTest() {
			Response response = PetEndpoints.getPetDetails(petIdNegative);
			Assert.assertEquals(response.statusCode(), 404);
			System.out.println(response.then().log().body());
		}
		
		

}
