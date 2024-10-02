package testcase.herokuapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Given;
import com.restapibase.BaseClass;
import com.restapibase.Config;
import com.restapibase.PropertiesLoader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utility.RestFWLogger;

public class BookingId {
	
	String endpoint = utility.CreateUrl.getBaseURI("/booking");
	
	Response response;
	
	@Test
	public void getBookingIdAll() {
		
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("Get Booking Id All");
		response = BaseClass.getRequest(endpoint);
		String responseBody = response.getBody().asString();
		System.out.println("All booking ID: " + responseBody);
		
		RestFWLogger.endTestCase();
	}
	
	
	@Test
	public void getBookingIdDetailsByName() {
		
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("Get Booking Id By Name");
		
//	// Load query parameters from the properties file
//        String propertiesFilePath = "D:\\JG_PROJ\\RestAssured_Design\\src\\test\\resources\\config.properties";
        
//		String propertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
//		Map<String, String> params = BaseClass.loadQueryParamsFromProperties(propertiesFilePath);
		
		
		
		 // Use the Config class to get the PropertiesLoader
        PropertiesLoader propertiesLoader = Config.getPropertiesLoader();

//        // Load query parameters from the properties file
//        Map<String, String> params = propertiesLoader.getAllProperties();
        
        
        // Define the keys you want to retrieve from the properties file
        List<String> keysToRetrieve = Arrays.asList("firstname", "lastname"); // Add other keys as needed
        
        Map<String, String> params = propertiesLoader.getSelectedProperties(keysToRetrieve);


        // Make the request using the getRequest method
        response = BaseClass.getRequest(endpoint, params);

        // Print the response body
        System.out.println("Response Body from name: " + response.getBody().asString());
        
        RestFWLogger.endTestCase();
	}
	
	
	@Test
	public void getBookingIdDetailsByDate() {
		
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("Get Booking Id By Date");
		
		// Use the Config class to get the PropertiesLoader
		PropertiesLoader propertiesLoader = Config.getPropertiesLoader();
		
		//selected keys from config properties file
		List<String> keysDate = Arrays.asList("checkin","checkout");
		
		Map<String, String> params = propertiesLoader.getSelectedProperties(keysDate);
		
		response = BaseClass.getRequest(endpoint, params);
		
     // Print the response body
        System.out.println("Response Body by date: " + response.getBody().asString());
        
        RestFWLogger.endTestCase();
	}
	
	

}
