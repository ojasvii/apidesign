package utility;

public class CreateUrl {
	
public final static String baseURI = "https://restful-booker.herokuapp.com";

public final static String baseURIReqRes = "https://reqres.in";
	
	public static String getBaseURI() {
		return baseURI;
	}
	
	public static String getBaseURI(String resource) {
		return baseURI + resource;
	}
	
	public static String getBaseURIReqRes() {
		return baseURIReqRes;
	}

	
	public static String getBaseURIReqRes(String resource) {
		return baseURIReqRes + resource;
	}
}
