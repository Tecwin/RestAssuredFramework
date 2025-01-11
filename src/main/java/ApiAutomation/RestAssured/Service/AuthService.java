package ApiAutomation.RestAssured.Service;

import java.util.HashMap;

import ApiAutomation.RestAssured.Models.Request.*;
import io.restassured.response.Response;

public class AuthService extends BaseService{
	
	private static final String BASE_PATH="/api/auth";
	
	public Response login(LoginRequest payload) {
	return 	postRequest(payload, BASE_PATH+"/login");
	}
	
	public Response signup(SignupRequest signupRequest) {
		return postRequest(signupRequest, BASE_PATH+"/signup");
		
	}
	
	public Response forgotPassword(String email) {
		HashMap<String, String> payload=new HashMap<String,String>();
		payload.put("email", email);
		return postRequest(payload, BASE_PATH+"/forgot-password");
		
	}

}
