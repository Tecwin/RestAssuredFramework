package ApiAutomation.RestAssured.Service;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import ApiAutomation.RestAssured.Filters.*;
public class BaseService {
	
	public static final String BASE_URI="http://64.227.160.186:8080";
	
	private RequestSpecification requestSpecification;
	
	static {
		RestAssured.filters(new LoggingFilter());
	}
	
	public BaseService() {
		// TODO Auto-generated constructor stub
		requestSpecification=given().baseUri(BASE_URI);
	}
	
	protected void setAuthToken(String token) {
		requestSpecification.header("Authorization","Bearer "+token);
	}
	
	public Response postRequest(Object payload,String path) {
		return requestSpecification.contentType(ContentType.JSON).body(payload).post(path);
	}
	
	public Response putRequest(Object payload,String path) {
		return requestSpecification.contentType(ContentType.JSON).body(payload).put(path);
	}

	public Response getRequest(String path) {
		return requestSpecification.contentType(ContentType.JSON).get(path);
	}
}
