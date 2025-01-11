package ApiAutomation.RestAssured.Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class TestListener  implements ITestListener{
	
	private static final Logger LOGGER=LogManager.getLogger(TestListener.class);
	
	
	public void onStart(ITestContext context) {
		
	    // not implemented
		LOGGER.info("Test Suit Started");
	  }
	
	public void onTestSuccess(ITestResult result) {
	    // not implemented
		LOGGER.info("Success !!!"+ result.getMethod().getMethodName());
	
	  }


	 public void onTestFailure(ITestResult result) {
	    // not implemented
		 LOGGER.error("Failed !!!"+ result.getMethod().getMethodName());
	  }
	 
	 public void onTestSkipped(ITestResult result) {
		    // not implemented
		 LOGGER.info("Skipped !!!"+ result.getMethod().getMethodName());
		  }
	public void onFinish(ITestContext context) {
		    // not implemented
		LOGGER.info("Test Suite Completed");
		  }



}
