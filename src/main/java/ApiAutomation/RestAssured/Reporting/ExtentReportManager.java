package ApiAutomation.RestAssured.Reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class ExtentReportManager {
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> tesThreadLocal=new ThreadLocal<>();
	
	public static ExtentReports createInstance() {
		if(extentReports==null) {
			String fileName=generateReportFileName();
			ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter("test-output/reports/"+fileName);
			extentSparkReporter.config().setTheme(Theme.DARK);
			extentSparkReporter.config().setDocumentTitle("Api Test Report");
			extentSparkReporter.config().setDocumentTitle("Rest Api test results");
			
			extentReports=new ExtentReports();
			extentReports.attachReporter(extentSparkReporter);
			extentReports.setSystemInfo("Environment", "Test");
			extentReports.setSystemInfo("User ", System.getProperty("user.name"));
			
			
		}
		return extentReports;
	}
	
	private static String generateReportFileName() {
		DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		return "APITestReport"+dateTimeFormatter.format(LocalDateTime.now())+".html";
	}
	
	public static void startTest(String testName) {
		ExtentTest test=createInstance().createTest(testName);
		tesThreadLocal.set(test);
	}
	
	public static void LogRequest(FilterableRequestSpecification requestSpecification) {
		StringBuilder requestSpec=new StringBuilder();
		requestSpec.append("<pre>");
		requestSpec.append("Request Method : ").append(requestSpecification.getMethod()).append("\n");
		requestSpec.append("Request URI : ").append(requestSpecification.getURI()).append("\n");
		requestSpec.append("Request Headers : ").append("\n");
		
		for(Header header:requestSpecification.getHeaders()) {
			requestSpec.append(" ").append(header.getName()).append(": ").append(header.getValue()).append("\n");
		}
		if(requestSpecification.getBody()!=null) {
			requestSpec.append("Request Body : ").append("\n");
			requestSpec.append(requestSpecification.getBody().toString());
		}
		requestSpec.append("</pre>");
		tesThreadLocal.get().log(Status.INFO, "Request Details : "+requestSpec.toString());
	}
	
	public static void logResponse(Response response) {
		StringBuilder responseDetails=new StringBuilder();
		responseDetails.append("<pre>");
		responseDetails.append("Response Status : ").append(response.getStatusCode()).append("\n");
		responseDetails.append("Response Headers : ").append("\n");
		
		response.getHeaders().forEach(header->
		responseDetails.append(" ").append(header.getName()).append(": ").append(header.getValue()).append("\n"));
		
		
		responseDetails.append("Request Body : ").append("\n");
		responseDetails.append(response.getBody().prettyPrint());		
		responseDetails.append("</pre>");
		
		tesThreadLocal.get().log(Status.INFO, "Response Details : "+responseDetails.toString());
		
		if(response.getStatusCode()>=200 && response.getStatusCode()<300) {
			tesThreadLocal.get().pass("Request completed Successfully");
		}
		else {
			tesThreadLocal.get().fail("Request Failed with Status code : "+ response.getStatusCode());
		}
	}
	
	public static void endTest() {
		if(tesThreadLocal.get()!=null) {
			extentReports.flush();
		}
	}
	
	public static ExtentTest getTest() {
		return tesThreadLocal.get();
	}
	
	

}
