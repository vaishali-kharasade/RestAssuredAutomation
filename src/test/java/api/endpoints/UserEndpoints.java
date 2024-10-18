package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	/* This method will create the user */
	public static Response createUser(User payload){
		
		Response reponse = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.postUrl);
		
		return reponse;
	}
	
	public static Response getUser(String userName) {
		Response reponse = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.when()
		.get(Routes.getUrl);
		
		return reponse;
	}
	
public static Response updateUser(String userName, User payload){
		
		Response reponse = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.putUrl);
		
		return reponse;
	}

public static Response deleteUser(String userName) {
	Response reponse = given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.pathParam("username", userName)
	.when()
	.delete(Routes.deleteUrl);
	
	return reponse;
}

}
