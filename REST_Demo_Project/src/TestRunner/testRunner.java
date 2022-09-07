package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = "./src/Features", glue= "javaSubfolder.StepDefinitions",   plugin = { "pretty",  "html:Reports/cucumber-pretty" })
public class testRunner extends AbstractTestNGCucumberTests {

}

//monochrome = true, , dryRun = true