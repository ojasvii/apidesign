package utility;

public class CreateUrl {
	
public final static String baseURI = "https://restful-booker.herokuapp.com";

	
	public static String getBaseURI() {
		return baseURI;
	}
	
	public static String getBaseURI(String resource) {
		return baseURI + resource;
	}

}
