package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="SeleniumFrameworkDesign.StepDefinitions",
monochrome=true,tags="@ErrorValidation", plugin= {"html:reports/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
