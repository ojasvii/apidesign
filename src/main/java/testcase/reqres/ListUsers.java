package testcase.reqres;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.restapibase.BaseClass;

import io.restassured.response.Response;
import utility.CommonUtilFunction;
import utility.RestFWLogger;

public class ListUsers {

	SoftAssert softAssert = new SoftAssert();

	String endpoint = utility.CreateUrl.getBaseURIReqRes("/api/users?page=2");
	Response response;
	
	@Test(priority=0)
	public void verifyKeyExists() {
		
//		Verify each user has necessary fields  page, per_page
		
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("verify the key exists in api.");
		
		response = BaseClass.getRequest(endpoint);
		String jsonData = response.getBody().asString();
		
		
//		Converting the sting to the json Object
		JSONObject jsonResponse = new JSONObject(jsonData);
		
		
//		Using the response variable
		boolean hasPageKey = response.jsonPath().getMap("").containsKey("page");
		softAssert.assertTrue(hasPageKey, "The page key doesnot exists. ");
		
//		Using the jsonResponse when converted to jsonObject
		if(jsonResponse.has("per_pages")) {
			RestFWLogger.info("API response has key  per_page");
		}else {
			RestFWLogger.error("API response doesnot have key per_page");
		}
		
		
		if(jsonResponse.has("data")) {
			RestFWLogger.info("API response has key  data");
		}else {
			RestFWLogger.error("API response doesnot have key data");
		}
		
		softAssert.assertAll();
		
		RestFWLogger.endTestCase();
		
	}
	

	@Test(priority=1)
	public void listUsers() {
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("Getting the list of users from page");

		response = BaseClass.getRequest(endpoint);
		
		String res = response.getBody().asString();

//		Verify the response status code

		int actual_statusCode = CommonUtilFunction.getStatusCode(response);
		softAssert.assertEquals(actual_statusCode, 200);

//		Verify the content type of the response

		String actual_content = CommonUtilFunction.getResponseHeader(response, "Content-Type");
		softAssert.assertEquals(actual_content, "application/json; charset=utf-8");

//		Verify the response body contains correct data for page 2	
		Integer actualPageValue = (Integer) CommonUtilFunction.getResponseKeyValueCast(res, "page");
		softAssert.assertEquals(actualPageValue, 2);
		
//		Verify that the response includes per_page, total, and total_pages fields
		
		Integer perPage = (Integer) CommonUtilFunction.getResponseKeyValueCast(res, "per_page");
		softAssert.assertEquals(perPage, 6);
		
		Integer total = (Integer)CommonUtilFunction.getResponseKeyValueCast(res, "total");
		softAssert.assertEquals(total, 12);
		
		Integer totalPages = (Integer)CommonUtilFunction.getResponseKeyValueCast(res, "total_pages");
		softAssert.assertEquals(totalPages, 2);
		
//		Verify the number of users returned matches per_page
		
		ArrayList data = (ArrayList) CommonUtilFunction.getResponseKeyValueCast(res, "data");
		int dataLength = data.size();
		RestFWLogger.info("Length of data : " + dataLength);
		
		softAssert.assertEquals(dataLength, perPage);		
		

		softAssert.assertAll();

		RestFWLogger.endTestCase();

	}
	
	
	@Test(priority=2)
	public void verifyDataArray() {
		
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("verify Array in data key.");
		
		response = BaseClass.getRequest(endpoint);
		String jsonData = response.getBody().asString();
		
		
//		Converting the sting to the json Object
		JSONObject jsonResponse = new JSONObject(jsonData);
		
//		Covert the jsonObject to array

		JSONArray dataArray =  jsonResponse.getJSONArray("data");
		
		for(int i=0; i<dataArray.length(); i++) {
			
			JSONObject userObject = dataArray.getJSONObject(i);
			
			 // Verify if the required fields exist in each user object of data
            if (userObject.has("id") && userObject.has("email") &&
                userObject.has("first_name") && userObject.has("last_name") &&
                userObject.has("avatar")) {

                // Print out the verified data
                System.out.println("User " + (i+1) + " verified:");
                System.out.println("ID: " + userObject.getInt("id"));
                System.out.println("Email: " + userObject.getString("email"));
                System.out.println("First Name: " + userObject.getString("first_name"));
                System.out.println("Last Name: " + userObject.getString("last_name"));
                System.out.println("Avatar: " + userObject.getString("avatar"));
            } else {
                System.out.println("Required fields missing in user " + (i+1));
            }
        
			
		}
		 
       
	}

}
