package org.galileo1.bdd;

import java.io.File;

import javax.annotation.PreDestroy;

import com.cucumber.listener.Reporter;

import org.galileo1.bdd.driver.DriverConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;


@CucumberOptions(features = "src/test/java/org/galileo1/bdd/features"
                // ,dryRun = true
                ,
                plugin = { "pretty", "html:target/cucumber-reports/cucumber-pretty",
				"json:target/cucumber-reports/CucumberTestReport.json", 
				"rerun:target/cucumber-reports/rerun.txt",
				"com.cucumber.listener.ExtentCucumberFormatter:"
			    })
//@ComponentScan(basePackages ="org.galileo1")
//@ContextConfiguration(classes = DriverConfiguration.class)
//@SpringBootApplication()
@SpringBootConfiguration()

//@ContextConfiguration
@ContextConfiguration(classes = DriverConfiguration.class)
public class CucumberTest {

	@Autowired
	private WebDriver driver;

	//Before Webdriver bean lifecycle gets over close the driver 
	@PreDestroy
    public void destroy(){
        try{
            System.out.println("Inside destroy() method...");
            if(driver != null) {
                driver.close();
            }
        } catch(Exception ex){
             
        }
    }

    private TestNGCucumberRunner testNGCucumberRunner;
	
	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}


	@Test(groups = "cucumber", description = "Runs cucmber Features", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void testDownClass() {
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", System.getProperty("os.name"));
        Reporter.setTestRunnerOutput("Westpac Test Runner");
		testNGCucumberRunner.finish();
	}
	
}
