package ApiAutomation.RestAssured.test;

import org.testng.annotations.Test;

import ApiAutomation.RestAssured.Models.Request.LoginRequest;
import ApiAutomation.RestAssured.Models.Request.ProfileRequest;
import ApiAutomation.RestAssured.Models.Response.LoginResponse;
import ApiAutomation.RestAssured.Service.AuthService;
import ApiAutomation.RestAssured.Service.UserProfileManagementService;
import io.restassured.response.Response;

public class UpdateProfileTest {
	
	@Test
	public void updateProfile() {
		AuthService authService=new AuthService();
		Response response=authService.login(new LoginRequest("uday1234", "uday1234"));
		LoginResponse loginResponse=response.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		
		
		UserProfileManagementService userProfileManagementService=new UserProfileManagementService();
	 System.out.println(userProfileManagementService.getProfile(loginResponse.getToken()).asPrettyString());
	 
	 
	 System.out.println("-----------------------------------------------");
	 ProfileRequest profileRequest=new ProfileRequest.Builder()
			 .firstName("yush")
			 .lastName("mishra")
			 .email("ayuush123@gmail.com")
			 .mobileNumber("9988227766")
	 		 .build();
	 
	 response= userProfileManagementService.putProfile(loginResponse.getToken(), profileRequest);
	 System.out.print(response.asPrettyString());
	}

	
}

