package SeleniumFrameworkDesign.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumFrameworkDesign.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;
	ExtentReports extent=ExtentReportsNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) 
	{
		// Executes before each test starts
		test=extent.createTest(result.getMethod().getMethodName()); //here we need to pass the name of the testcase
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		// Executes when test passes
		test.log(Status.PASS, "TestCase is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		// Executes when test fails
		extentTest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String ssFilePath = null;
		try {
			ssFilePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(ssFilePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		// Executes when test is skipped
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// Rarely used
	}

	@Override
	public void onStart(ITestContext context) 
	{
		// Executes before <test> tag starts in testng.xml
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		// Executes after <test> tag completes
		extent.flush();
	}
}
