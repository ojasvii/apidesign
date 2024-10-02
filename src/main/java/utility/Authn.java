package utility;

public class Authn {
	
	private static String createtoken;
	private static Integer bookingid;
	
	//setter method for token
	public static void setToken(String token) {
		createtoken = token;
	}

	
	//getter method for getToken
	
	public static String getToken() {
		return createtoken;
	}
	
	
	//setter method for booking id
	public static void setBookingId(Integer bookingid) {
		Authn.bookingid = bookingid;
	}
	
	//getter method for get booking id
	public static Integer getBookingId() {
		return Authn.bookingid;
	}
	
	
	
	
	

}
