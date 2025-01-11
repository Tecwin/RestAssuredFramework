package ApiAutomation.RestAssured.test;

import org.testng.annotations.Test;

import ApiAutomation.RestAssured.Models.Request.SignupRequest;
import ApiAutomation.RestAssured.Service.AuthService;
import io.restassured.response.Response;

public class AccountCreation {
	
	@Test
	public void loginTest() {
		SignupRequest signupRequest= new SignupRequest.Builder()
		.username("berny")
		.password("berny123")
		.email("berny@gmail.com")
		.firstName("benr")
		.lastName("djdk")
		.mobileNumber("9877568234")
		.build();
		AuthService authService=new AuthService();
		Response x = authService .signup(signupRequest);
		System.out.print(x.asPrettyString());
		
		
	}

}
