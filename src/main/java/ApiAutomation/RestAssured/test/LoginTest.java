package ApiAutomation.RestAssured.test;

import org.testng.annotations.Test;

import ApiAutomation.RestAssured.Service.AuthService;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class LoginTest {
	
	@Test
	public void loginTest() {
		//RestAssured.baseURI="http://64.227.160.186:8080";
		Response x=given()
				.baseUri("http://64.227.160.186:8080")
				.header("Content-type","application/json")
				.body("{\"username\": \"uday1234\", \"password\": \"uday1234\"}")
				.post("/api/auth/login");
		System.out.print(x.asPrettyString());
	}

	@Test
	public void forgotPasswordTest() {
		AuthService authService=new AuthService();
		Response x= authService.forgotPassword("berny@gmail.com");
		System.out.print(x.asPrettyString());
		
	}
}
