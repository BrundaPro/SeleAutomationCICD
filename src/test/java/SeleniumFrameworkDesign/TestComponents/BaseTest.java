package SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumFrameworkDesign.pageObjects.LandingPage;

public class BaseTest 
{
	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initialzeDriver() throws IOException
	{
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\seleniumFrameworkDesign\\resources\\GlobalData.properties");
		//load accepts only inputStream, so using FileInputStream() we converting GlobalData file to InputStream
		prop.load(fis);  //now it will read any property present inside the GlobalData
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		//using java ternary operator we are choosing browser either from terminal(through maven commands) or through GlobalData.properties File
//		
//	if(browserName.equalsIgnoreCase("chrome")) //this is for Headed chrome browser
//	{
//		 driver=new ChromeDriver();
//	}
	if(browserName.contains("chrome")) //this is for Headless chrome browser
	{
		 ChromeOptions options=new ChromeOptions();
		 if(browserName.contains("headless"))
		 {
		 options.addArguments("headless");
		 }
		 
		 driver=new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,900));
	}
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		driver=new FirefoxDriver();
	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		driver=new EdgeDriver();
	}	
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
		//method to convert json to string and string to HashMap(key-value) and return List of HashMaps
		public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
		{
			//read or convert json data to string data
			String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
			//System.out.println(jsonContent);
			//read or convert json to HashMap(key-value pair) using jackson Databind dependency
			ObjectMapper mapper=new ObjectMapper();
			List<HashMap<String,String>> data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>()
					{});
			return data;
			
		}
		

		//method or utility to take the screenshot when the test case fails
		
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
		{
			TakesScreenshot ts=(TakesScreenshot)driver; //here we are casting driver to screenshot mode
			File source=ts.getScreenshotAs(OutputType.FILE); 
			//here using ts object we are taking ss and we are asking the ss output must be in FILE format
			
			File file=new File(System.getProperty("user.dir")+"\\reports\\"+ testCaseName + ".png");
			//but the copyFile(source,destination) takes only File object as argument so we are converting ss taken to file type
			
			FileUtils.copyFile(source, file);//the ss taken, we need to store that in local project or workspace in file format
			return System.getProperty("user.dir")+"\\reports\\"+ testCaseName + ".png";
			
		}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initialzeDriver();
		landingpage=new LandingPage(driver);
		landingpage.goTo(); 
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeThepage()
	{
		driver.close();
	}

}
