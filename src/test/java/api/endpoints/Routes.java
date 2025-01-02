package api.endpoints;

/* Swagger URI : https://petstore.swagger.io 
 * Create User[POST] : https://petstore.swagger.io/v2/user
 * Get User[GET] : https://petstore.swagger.io/v2/user/{username}
 * Update User[PUT] : https://petstore.swagger.io/v2/{username}
 * Delete User[DELETE] : https://petstore.swagger.io/v2/{username}
 * 
 * */

public class Routes {
	
	public static String baseUrl = "https://petstore.swagger.io/v2";
	
	/* User Module */
	public static String postUrl = baseUrl + "/user";
	public static String getUrl = baseUrl + "/user/{username}";
	public static String putUrl = baseUrl + "/user/{username}";
	public static String deleteUrl = baseUrl + "/user/{username}";
	
	/* Pet Module */
	public static String petPostUrl = baseUrl + "/pet";
	public static String petGetUrl = baseUrl + "/pet/{petId}";
	public static String petPutUrl = baseUrl + "/pet";
	public static String petDeleteUrl = baseUrl + "/pet/{petId}";

}
