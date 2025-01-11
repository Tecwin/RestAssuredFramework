package ApiAutomation.RestAssured.test;

import org.testng.annotations.Test;

import ApiAutomation.RestAssured.Models.Request.LoginRequest;
import ApiAutomation.RestAssured.Models.Response.LoginResponse;
import ApiAutomation.RestAssured.Models.Response.UserProfileResponse;
import ApiAutomation.RestAssured.Service.AuthService;
import ApiAutomation.RestAssured.Service.UserProfileManagementService;
import io.restassured.response.Response;

public class GetProfileInfoTest {
	
	@Test
	public void getProfileInfo() {
		AuthService authService=new AuthService();
		Response response=authService.login(new LoginRequest("uday1234", "uday1234"));
		LoginResponse loginResponse=response.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		
		UserProfileManagementService userProfileManagementService=new UserProfileManagementService();
		response=userProfileManagementService.getProfile(loginResponse.getToken());
		System.out.println(response.asPrettyString());
		System.out.print(response.as(UserProfileResponse.class).getFirstName());
	}

}
