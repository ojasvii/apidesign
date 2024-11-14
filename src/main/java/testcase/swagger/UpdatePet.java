package testcase.swagger;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class UpdatePet {
	
	String endpoint  = "https://petstore.swagger.io/v2/pet/";
	int petId = 6;
	
	@Test
	public void updatePet() {
		
		 // Base URI for the Petstore API
		 String formData = "name=himanshu&status=available soon";

	        // Make the POST request
	        Response response = RestAssured.given()
	            .header("accept", "application/json")  // Accept header
	            .header("Content-Type", "application/x-www-form-urlencoded")  // Content-Type header for JSON
	            .body(formData)  // Request body as JSON
	            .when()
	            .post(endpoint + petId)  // Endpoint with the pet ID
	            .then()
	            .statusCode(200)  // Verify status code 200 OK
	             // Assuming the message field returns the pet ID
	            .extract().response();

	        // Print response body for debugging
	        System.out.println("Response Body: " + response.getBody().asString());
	        
	        String resp  =response.getBody().asString();
	        
	        JSONObject json = new JSONObject(resp);
	        
	        
	        Assert.assertEquals(json.get("message"), "6");
	        
	       
	        
	        
	       
	}
	
}
