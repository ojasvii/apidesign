package testcase.reqres;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utility.CreateUrl;

public class SingleUser {

	
	SoftAssert softAssert = new SoftAssert();
//	Single user automation for the api

	String endpoint = CreateUrl.getBaseURIReqRes("/api/users/2");

	@Test
	public void validUserDataRetrievalPositiveCases() {

		RestAssured.baseURI = endpoint;

		Response response = RestAssured.given().log().all().get().then().log().all().extract().response();
		
		
		// validate the status code
		int statusCode = response.getStatusCode();
		softAssert.assertEquals(statusCode, 200);
		
		
//		validate the response time threshold
		long responseTime = response.getTime();
		softAssert.assertTrue(responseTime < 2000, "Response time is more then 200");
		
		
//		Verify the content type header
		String contentType = response.contentType();
		softAssert.assertEquals(contentType, "application/json; charset=utf-8");
//
//		System.out.println(response.getBody().asString());
		
		
		
		
//		verify the Presence of Required Fields id, email, first_name, last_name, avatar

//		Converted to string
		String jsonData = response.getBody().asString();

//		Now we have to convert string to jsonObject

		JSONObject jsonResponse = new JSONObject(jsonData);

		if (jsonResponse.has("data")) {

			System.out.println("Data key Exists");
			
				Object dataObject =	jsonResponse.get("data");
			
				
				// Check if "data" is a JSONArray
			if(dataObject instanceof JSONArray) {
				
				JSONArray dataArray = jsonResponse.getJSONArray("data");
				
//				or we can user
				
//				JSONArray dataArray = (JSONArray) dataObject;
				
				for (int i = 0; i < dataArray.length(); i++) {
					
				JSONObject	dataKeyValues = dataArray.getJSONObject(i);
				
				if(dataKeyValues.has("id") && dataKeyValues.has("email") && dataKeyValues.has("first_name") && dataKeyValues.has("last_name") &&
						dataKeyValues.has("avatar")) {
					
//					Verify the user id with data
					System.out.println("Value of id key : " + dataKeyValues.getInt("id"));
					softAssert.assertEquals(dataKeyValues.getInt("id"), 2);
					
//					verify the user email id and also the proper format
					System.out.println("Value of email key : " + dataKeyValues.getString("email"));
					softAssert.assertEquals("^[A-Za-z0-9+_.-]+@(.+)$", "janet.weaver@reqres.in");
					
					
					System.out.println("Value of first_name key : " + dataKeyValues.getString("first_name"));
					System.out.println("Value of last_name : " + dataKeyValues.getString("last_name"));
					System.out.println("Value of avatar : " + dataKeyValues.getString("avatar"));
				}
				}
				
				// Check if "data" is a JSONObject
			}else if(dataObject instanceof JSONObject) {
				
			JSONObject dataKeyValues = (JSONObject)	dataObject;
			
			if(dataKeyValues.has("id") && dataKeyValues.has("email") && dataKeyValues.has("first_name") && dataKeyValues.has("last_name") &&
					dataKeyValues.has("avatar")) {
				System.out.println("Value of id key : " + dataKeyValues.getInt("id"));
				System.out.println("Value of email key : " + dataKeyValues.getString("email"));
				System.out.println("Value of first_name key : " + dataKeyValues.getString("first_name"));
				System.out.println("Value of last_name : " + dataKeyValues.getString("last_name"));
				System.out.println("Value of avatar : " + dataKeyValues.getString("avatar"));
			}
				
			}
			

		} else {
			System.out.println("Data key doesnot exists");
		}
		
		
		softAssert.assertAll();
	}
	
	
}
