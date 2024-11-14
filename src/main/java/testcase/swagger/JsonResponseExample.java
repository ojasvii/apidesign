package testcase.swagger;


	
//	{
//	  "status": "success",
//	  "data": {
//	    "pets": [
//	      {
//	        "id": 1,
//	        "name": "Buddy",
//	        "category": {
//	          "id": 1,
//	          "name": "Dog"
//	        }
//	      },
//	      {
//	        "id": 2,
//	        "name": "Mittens",
//	        "category": {
//	          "id": 2,
//	          "name": "Cat"
//	        }
//	      }
//	    ],
//	    "totalCount": 2
//	  }
//	}
	
	
	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.junit.Assert;

	public class JsonResponseExample {
	    public static void main(String[] args) {
	        // Example JSON response
	        String jsonResponse = "{\n" +
	                "  \"status\": \"success\",\n" +
	                "  \"data\": {\n" +
	                "    \"pets\": [\n" +
	                "      {\n" +
	                "        \"id\": 1,\n" +
	                "        \"name\": \"Buddy\",\n" +
	                "        \"category\": {\n" +
	                "          \"id\": 1,\n" +
	                "          \"name\": \"Dog\"\n" +
	                "        }\n" +
	                "      },\n" +
	                "      {\n" +
	                "        \"id\": 2,\n" +
	                "        \"name\": \"Mittens\",\n" +
	                "        \"category\": {\n" +
	                "          \"id\": 2,\n" +
	                "          \"name\": \"Cat\"\n" +
	                "        }\n" +
	                "      }\n" +
	                "    ],\n" +
	                "    \"totalCount\": 2\n" +
	                "  }\n" +
	                "}";

	        // Convert string to JSONObject
	        JSONObject jsonObject = new JSONObject(jsonResponse);

	        // Extract the "data" JSONObject
	        JSONObject dataJson = jsonObject.getJSONObject("data");

	        // Extract the "pets" JSON array
	        JSONArray petsArray = dataJson.getJSONArray("pets");

	        // Verify first pet's information
	        JSONObject firstPet = petsArray.getJSONObject(0);
	        Assert.assertEquals("Buddy", firstPet.getString("name"));
	        Assert.assertEquals(1, firstPet.getInt("id"));
	        Assert.assertEquals("Dog", firstPet.getJSONObject("category").getString("name"));

	        // Verify second pet's information
	        JSONObject secondPet = petsArray.getJSONObject(1);
	        Assert.assertEquals("Mittens", secondPet.getString("name"));
	        Assert.assertEquals(2, secondPet.getInt("id"));
	        Assert.assertEquals("Cat", secondPet.getJSONObject("category").getString("name"));
	    }
	}
