package testcase.herokuapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.restapibase.BaseClass;
import com.restapibase.Config;
import com.restapibase.PropertiesLoader;

import io.restassured.response.Response;
import utility.Authn;
import utility.RestFWLogger;

public class GetSingleBooking {

	String endpoint = utility.CreateUrl.getBaseURI("/booking");
	Response response;

	@Test
	public void getSingleBoooking() {

		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("Get the booking by single id");

		PropertiesLoader loader = Config.getPropertiesLoader();

		List<String> retrieveId = Arrays.asList("id");

		Map<String, String> paramId = loader.getSelectedProperties(retrieveId);

		// Extract 'id' from the map
		String id = paramId.get("id");

		// Ensure that 'id' exists in file
//		if (id != null && !id.isEmpty()) {
//			String completeendpoint = endpoint + "/" + id;
//			BaseClass.getRequest(completeendpoint, null);
//		} else {
//			RestFWLogger.error("ID not found in properties.");
//		}
		
		
//		Id save from the request body and fetching from the authn class
		
		if (Authn.getBookingId() != null) {
			String completeendpoint = endpoint + "/" + Authn.getBookingId();
			BaseClass.getRequest(completeendpoint, null);
		} else {
			RestFWLogger.error("ID not found in properties.");
		}

		RestFWLogger.endTestCase();

	}

}
