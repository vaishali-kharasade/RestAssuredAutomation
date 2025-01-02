package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.Pet;
import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndpoints {
	
	/* This method will create the user */
	public static Response addNewPet(Pet addNewPet){
		
		Response reponse = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(addNewPet)
		.when()
			.post(Routes.petPostUrl);
		
		return reponse;
	}
	
	public static Response getPetDetails(int petId) {
		Response reponse = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("petId", petId)
		.when()
		.get(Routes.petGetUrl);
		
		return reponse;
	}
//	
//public static Response updateUser(String userName, User payload){
//		
//		Response reponse = given()
//			.contentType(ContentType.JSON)
//			.accept(ContentType.JSON)
//			.pathParam("username", userName)
//			.body(payload)
//		.when()
//			.put(Routes.putUrl);
//		
//		return reponse;
//	}
//
//public static Response deleteUser(String userName) {
//	Response reponse = given()
//	.contentType(ContentType.JSON)
//	.accept(ContentType.JSON)
//	.pathParam("username", userName)
//	.when()
//	.delete(Routes.deleteUrl);
//	
//	return reponse;
//}

}
