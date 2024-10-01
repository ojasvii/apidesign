package utility;

public class Authn {
	
	private static String createtoken;
	
	//setter method for token
	public static void setToken(String token) {
		createtoken = token;
	}

	
	//getter method for getToken
	
	public static String getToken() {
		return createtoken;
	}
	
	
	

}
