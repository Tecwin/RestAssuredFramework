package ApiAutomation.RestAssured.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ApiAutomation.RestAssured.Models.Request.LoginRequest;
import ApiAutomation.RestAssured.Models.Response.LoginResponse;
import ApiAutomation.RestAssured.Service.AuthService;
import io.restassured.response.Response;

@Listeners(ApiAutomation.RestAssured.Listeners.TestListener.class)
public class LoginTest2 {

	@Test
	public void loginTest() {
		LoginRequest loginRequest=new LoginRequest("uday1234", "uday1234");
		AuthService authService=new AuthService();
		Response x = authService .login(loginRequest);
		LoginResponse loginResponse=x.as(LoginResponse.class);
		
		System.out.println(x.asPrettyString());
		System.out.println(loginResponse.getToken());
		
	}

	
}
