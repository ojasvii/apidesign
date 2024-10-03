package testcase.herokuapp;

import java.io.IOException;

import org.testng.annotations.Test;

import com.restapibase.BaseClass;

import io.restassured.response.Response;
import utility.Authn;
import utility.CreateUrl;
import utility.PayLoadGenerator;
import utility.RestFWLogger;

public class PartialUpdateBooking {
	
	String endpoint = CreateUrl.getBaseURI("/booking");
	Response response;
	
	@Test
	public void partialUpdate() throws IOException {
		
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("Partial Update of booking.");
		
		String partialUpdate = PayLoadGenerator.generateStringPayload("partialUpdateBooking.json");
		
		if(Authn.getBookingId()!=null) {
			String completeendpoint = endpoint + "/" + Authn.getBookingId();
			BaseClass.patchRequest(completeendpoint, partialUpdate, Authn.getToken());
		}else {
			RestFWLogger.error("Booking Id is missing.");
		}
		
		RestFWLogger.endTestCase();
	}

}
