package testcase.herokuapp;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restapibase.BaseClass;

import io.restassured.response.Response;
import utility.Authn;
import utility.CommonUtilFunction;
import utility.CreateUrl;
import utility.PayLoadGenerator;
import utility.RestFWLogger;

public class UpdateBooking {
	
	String endpoint = CreateUrl.getBaseURI("/booking");
	Response response;
	
	@Test
	public void updateBooking() throws IOException {
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("Update the booking id.");
		
		String updatebooking = PayLoadGenerator.generateStringPayload("updatebooking.json");
		
		if(Authn.getBookingId()!=null) {
			String completeendpoint= endpoint + "/" + Authn.getBookingId();
			response = BaseClass.putRequest(completeendpoint, updatebooking, Authn.getToken());
		}else {
			RestFWLogger.error("There is no booking Id to update.");
		}	
		
		Assert.assertEquals(CommonUtilFunction.getStatusCode(response), 200);
		
		RestFWLogger.endTestCase();
	}

}
