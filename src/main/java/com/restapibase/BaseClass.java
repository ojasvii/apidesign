package com.restapibase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.RestFWLogger;

public class BaseClass {
	
	
	// Method to load query parameters from JSON file
    public static Map<String, String> loadQueryParamsFromJson(String filePath) {
        Map<String, String> queryParams = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file and convert to Map
            queryParams = objectMapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return queryParams;
    }
	
	
	
	// Method to load query parameters from properties file
    public static Map<String, String> loadQueryParamsFromProperties(String filePath) {
        Map<String, String> queryParams = new HashMap<>();
        Properties properties = new Properties();

        try (FileInputStream fileInput = new FileInputStream(filePath)) {
            properties.load(fileInput);
            // Add properties to the queryParams map
            for (String key : properties.stringPropertyNames()) {
                queryParams.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return queryParams;
    }
    
    
    
 // Modified getRequest method to accept dynamic params
    public static Response getRequest(String requestURI, Map<String, String> params) {
        RestFWLogger.initLogger();
        RestFWLogger.info("Request URI is - " + requestURI);

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);

        // Add query parameters if they are provided
        if (params != null && !params.isEmpty()) {
            requestSpecification.params(params);
        }

        RestFWLogger.info("Request Parameters is - " + params);
        // Make the GET request
        Response response = requestSpecification.get(requestURI);

        
        RestFWLogger.info("Request Response is - " + response.getBody().asString());
        return response;
    }
	
	
	
	
	// Basic Functions of Framework

		public static Response getRequest(String requestURI) {
			RestFWLogger.initLogger();
			RestFWLogger.info("Request URI is - " + requestURI);
			RequestSpecification requestSpecification = RestAssured.given();
			requestSpecification.contentType(ContentType.JSON);
			Response response = requestSpecification.get(requestURI);
			RestFWLogger.info("Request Response is - " + response.getBody().asString());
			return response;
		}

		public static Response postRequest(String requestURI, String requestPayLoad) {
			RestFWLogger.initLogger();
			RestFWLogger.info("Request URI is - " + requestURI);
			RestFWLogger.info("Request Payload is - " + requestPayLoad);
			RequestSpecification requestSpecification = RestAssured.given().body(requestPayLoad);
			requestSpecification.contentType(ContentType.JSON);
			Response response = requestSpecification.post(requestURI);
			RestFWLogger.info("Request Response is - " + response.getBody().asString());
			return response;
		}
		
		public static Response postRequest(String requestURI, String requestPayLoad, String bearer_token) {
			RestFWLogger.initLogger();
			RestFWLogger.info("Request URI is - " + requestURI);
			RestFWLogger.info("Request Payload is - " + requestPayLoad);
			RequestSpecification requestSpecification = RestAssured.given().body(requestPayLoad);
			requestSpecification.contentType(ContentType.JSON);
			requestSpecification.header("Authorization", "Bearer " + bearer_token);
			Response response = requestSpecification.post(requestURI);
			RestFWLogger.info("Request Response is - " + response.getBody().asString());
			return response;
		}

		public static Response putRequest(String requestURI, String requestPayLoad) {
			RestFWLogger.initLogger();
			RestFWLogger.info("Request URI is - " + requestURI);
			RestFWLogger.info("Request Payload is - " + requestPayLoad);
			RequestSpecification requestSpecification = RestAssured.given().body(requestPayLoad);
			requestSpecification.contentType(ContentType.JSON);
			Response response = requestSpecification.put(requestURI);
			RestFWLogger.info("Request Response is - " + response.getBody().asString());
			return response;
		}

		public static Response deleteRequest(String requestURI) {
			RestFWLogger.initLogger();
			RestFWLogger.info("Request URI is - " + requestURI);
			RequestSpecification requestSpecification = RestAssured.given();
			requestSpecification.contentType(ContentType.JSON);
			Response response = requestSpecification.delete(requestURI);
			RestFWLogger.info("Request Response Status Code is - " + response.getStatusCode());
			return response;
		}
		
		public static Response deleteRequest(String requestURI, String bearer_token) {
			RestFWLogger.initLogger();
			RestFWLogger.info("Request URI is - " + requestURI);
			RequestSpecification requestSpecification = RestAssured.given();
			requestSpecification.contentType(ContentType.JSON);
			requestSpecification.header("Content-Type", "application/json");
			requestSpecification.header("Authorization", "Bearer " + bearer_token);
			Response response = requestSpecification.delete(requestURI);
			RestFWLogger.info("Request Response Status Code is - " + response.getStatusCode());
			return response;
		}

		public static Response deleteRequestWithPayload(String requestURI, String requestPayLoad) {
			RestFWLogger.initLogger();
			RestFWLogger.info("Request URI is - " + requestURI);
			RestFWLogger.info("Request Payload is - " + requestPayLoad);
			RequestSpecification requestSpecification = RestAssured.given().body(requestPayLoad);
			requestSpecification.contentType(ContentType.JSON);
			Response response = requestSpecification.delete(requestURI);
			RestFWLogger.info("Request Response Status Code is - " + response.getStatusCode());
			return response;
		}

}
