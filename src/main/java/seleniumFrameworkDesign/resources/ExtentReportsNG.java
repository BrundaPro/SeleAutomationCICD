package seleniumFrameworkDesign.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG 
{
	
	public static ExtentReports getReporterObject()  //we made method static so that we can access method without creating object of this class
	{
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Results");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nanda");
		//extent.createTest(path); //creating entry for the Test
		return extent;  //we are returning extent object to catch it in onTestStart() method for 
		//further process of creating entry for the Testcase
	}

}
