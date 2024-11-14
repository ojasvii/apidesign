package testcase.swagger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.synchronoss.cloud.nio.multipart.io.buffer.EndOfLineBuffer;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetId {
	
	//find pet by id
	
	
	
	String endpoint = "https://petstore.swagger.io/v2/pet/";
	int petId = 5;
	int notFoundPetId = 1920;
	String invalidId = "12we";
	
	@Test(priority=0)
	public void validId() {
		
		RestAssured.baseURI = endpoint;
		
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).
		when().get(endpoint + petId).then().assertThat().statusCode(200).extract().response();
		
		System.out.println("valid data : " + response.getBody().asPrettyString());
		
		String resp = response.getBody().asString();
		
		JSONObject jsonObject = new JSONObject(resp);
		
		if(jsonObject.has("id")) {
			Assert.assertEquals(jsonObject.get("id"),5, "The value of the id is not matched.");
		}else {
			System.out.println("Key id is not found.");
		}
		
		////
		
		JSONObject  categoryJson = jsonObject.getJSONObject("category");
		
		// Check if the "category" object contains the expected keys and values
		if (categoryJson.has("id") && categoryJson.has("name")) {
		    //Assert.assertEquals("ID should be 0", 0, categoryJson.getInt("id"));
		    Assert.assertEquals("Name should be 'string'", "string", categoryJson.getString("name"));
		} else {
		    Assert.fail("Category does not contain the required keys 'id' and 'name'");
		}
		
		
		/////
		JSONArray tagArray = jsonObject.getJSONArray("tags");
		
		for( int i=0; i<tagArray.length(); i++) {
			
			JSONObject tagObject = tagArray.getJSONObject(i);
			
			if(tagObject.has("id") && tagObject.has("name")) {
				Assert.assertEquals(tagObject.get("id"), 0);
				Assert.assertEquals(tagObject.get("name"), "string");
			}
			
		}
	}
	
	
	@Test(priority = 1)
	public void petNotFound () {
		
		Response response = RestAssured.given().header("content-type","application/json")
		.accept(ContentType.JSON)
		.when().get(endpoint + notFoundPetId).then().extract().response();
		
		System.out.println("Pet not found: " + response.getBody().asPrettyString());
		System.out.println("invalid id status code: " + response.getStatusCode());
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 404);
		
	String resp = response.getBody().asPrettyString();

	
	JSONObject jsonObject = new JSONObject(resp);
	
	if(jsonObject.has("message")) {
		Assert.assertEquals(jsonObject.get("message"), "Pet not found");
	}
	
	
		
		
	}
	
	
	@Test(priority=2)
	public void InvalidId () {
		
		Response response = RestAssured.given().header("content-type","application/json")
		.accept(ContentType.JSON)
		.when().get(endpoint + invalidId)
		.then().extract().response();
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 404);
		
		
	}

}
