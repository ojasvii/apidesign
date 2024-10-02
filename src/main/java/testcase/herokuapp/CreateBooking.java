package testcase.herokuapp;

import utility.Authn;
import utility.CommonUtilFunction;
import utility.CreateUrl;
import utility.PayLoadGenerator;

import java.io.IOException;

import org.testng.annotations.Test;

import com.restapibase.BaseClass;

import io.restassured.response.Response;

public class CreateBooking {
	
	String endpoint = CreateUrl.getBaseURI("/booking");
	Response response;
	
	@Test
	public void createBooking() throws IOException {
		
		String createbooking = PayLoadGenerator.generateStringPayload("createbooking.json");
		
		response = BaseClass.postRequest(endpoint, createbooking);
		
		String responseBody = response.getBody().asString();
		
		CommonUtilFunction.getStatusCode(response);
		
		Integer newbookingId = (Integer) CommonUtilFunction.getResponseKeyValue1(responseBody, "bookingid");
		System.out.println("New Booking Id : " + newbookingId);
		
		Authn.setBookingId(newbookingId);
		
		System.out.println("Booking id after saving :" + Authn.getBookingId());
	}
	

}
