package testcase.swagger;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetUploadImage {
	
	String endpoint = "https://petstore.swagger.io/v2";
	File file = new File("D:\\JG_PROJ\\RestAssured_Design\\download (1).jpg");
	int pedId = 1;
	String id = "abc";
	
	@Test
	public void pet() {
		RestAssured.baseURI =endpoint;
		
		Response response = RestAssured.given().contentType("multipart/form-data")
		.accept(ContentType.JSON)
		.multiPart(file)
		.formParam("additionalMetadata", "1")
		.when().post("/pet/" +pedId+ "/uploadImage").then().assertThat().statusCode(200).extract().response();
	
		System.out.println(response.getBody().prettyPrint());
	
	}
	
	@Test
	public void petUploadWithoutFile() {
		RestAssured.baseURI =endpoint;
		
		Response response = RestAssured.given().contentType("multipart/form-data")
		.accept(ContentType.JSON)
		.multiPart("file", file, "text/plain")
		.formParam("additionalMetadata", "1")
		.when().post("/pet/" +id+ "/uploadImage").then().assertThat().statusCode(404).extract().response();
	
		System.out.println(response.getBody().prettyPrint());
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(), 404);
	
	}
	
	
	@Test
	public void verifyKeyinResponse() {
		

		Response response = RestAssured.given().contentType("multipart/form-data")
		.accept(ContentType.JSON)
		.multiPart(file)
		.formParam("additionalMetadata", "1")
		.when().post("/pet/" +pedId+ "/uploadImage").then().assertThat().statusCode(200).extract().response();
	
		System.out.println(response.getBody().asString());
		
		String resp = response.getBody().asString();
		
		JSONObject jsonObject = new JSONObject(resp);
		
		if(jsonObject.has("codes")) {
			System.out.println("Code key exists.");
			
		}else {
			System.out.println("Code key doesnot exists.");
		}
		
		
	}
	

}
