package testcase.herokuapp;

import java.io.IOException;

import org.testng.annotations.Test;

import com.restapibase.BaseClass;

import io.restassured.response.Response;
import utility.Authn;
import utility.CreateUrl;
import utility.PayLoadGenerator;
import utility.RestFWLogger;

public class DeleteBooking {
	
	String endPoint = CreateUrl.getBaseURI("/booking");
	Response response;

	@Test
	public void deleteBooking() throws IOException {
		
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("Delete the created booking");
		
		if(Authn.getBookingId() != null) {
			String completeendpoint = endPoint + "/" + Authn.getBookingId();
			BaseClass.deleteRequest(completeendpoint, Authn.getToken());
		}else {
			RestFWLogger.error("Id not found.");
		}
		
		RestFWLogger.endTestCase();
		
	}
	
	

}
