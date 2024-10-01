package testcase.herokuapp;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restapibase.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utility.Authn;
import utility.CommonUtilFunction;
import utility.CreateUrl;
import utility.PayLoadGenerator;
import utility.RestFWLogger;

public class CreateToken {

	String endPoint = CreateUrl.getBaseURI("/auth");
	Response response;

	@Test
	public void createToken() throws IOException {
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("Create Token");

		RestFWLogger.info("Step1 : Genereating the payload string.");
		String req_payload = PayLoadGenerator.generateStringPayload("createtoken.json");

		RestFWLogger.info("Step2 : Executing the create token API");
		response = BaseClass.postRequest(endPoint, req_payload);

		String responseString = response.getBody().asString();
		
		String tokenResponseBody = CommonUtilFunction.getResponseKeyValue(responseString, "token");
		System.out.println("Response body token : " + tokenResponseBody);
		
		//save the token in authn class
		Authn.setToken(tokenResponseBody);
		
		System.out.println("After saving the token : " + Authn.getToken());
		
		
		int stautsCOde = CommonUtilFunction.getStatusCode(response);
		Assert.assertEquals(stautsCOde, 200);
		
		String statusMessage = CommonUtilFunction.getStatusMessage(response);
		Assert.assertEquals(statusMessage, "HTTP/1.1 200 OK");
		
		CommonUtilFunction.getResponseHeader(response, "Content-Type");
		
		CommonUtilFunction.getResponseContentType(response);
		
		CommonUtilFunction.getResponseExecutionTime(response);

		RestFWLogger.endTestCase();
	}

	

}
