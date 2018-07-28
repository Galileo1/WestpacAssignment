package org.galileo1.bdd.driver;



import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.galileo1")
@PropertySource("classpath:application.properties")
public class DriverConfiguration  {
    String location = System.getProperty("user.dir");

    
    @Value("${app.browser}")
    private String browser;
    
   // destroyMethod="quit"
    @Bean(destroyMethod="quit")
    public WebDriver webDriver() {
        getRunningEnvironment();
        return getDriver();   
    }

    private WebDriver getDriver() {
        switch(browser) {
            case "chrome" :
                return new ChromeDriver(setChromeCapabilities());
            case "firefox":
                return new FirefoxDriver();
            case "remote":
                try {
                    return new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), setChromeCapabilities());
                } catch (MalformedURLException e) {
                    
                    e.printStackTrace();
                }
            default:
                return new ChromeDriver(setChromeCapabilities());
        }
    }

    private ChromeOptions setChromeCapabilities() {
        final ChromeOptions chromeOptions  = new ChromeOptions();
        chromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox", "--disable-gpu");
        return chromeOptions;
    }


    
    //TODO: This is ugly. Need to write it in a better way.
    private void getRunningEnvironment() {
        String osname =System.getProperty("os.name").toLowerCase();

        if (osname.contains("windows") && browser.contains("chrome")) {
              setChromeDriverPath("//src//test//resources//chromedriverwindows//chromedriver.exe");
        } else if (osname.contains("linux") && browser.contains("chrome")) {
              setChromeDriverPath("//src//test//resources//chromedriver");
        } else if (osname.contains("mac os") && browser.contains("chrome")) {
              setChromeDriverPath("//src//test//resources//chromedriver");
        } else if (osname.contains("mac os") && browser.contains("firefox")) {
              setGeckoDriverPath("//src//test//resources//geckodrivermac//geckodriver");
        } else if (osname.contains("windows") && browser.contains("firefox")) {
              setGeckoDriverPath("//src//test//resources//geckodriverwindows//geckodriver.exe");
        }
    }

    private void setChromeDriverPath(String pathToChromeDriver) {
        System.setProperty("webdriver.chrome.driver", location + pathToChromeDriver);
    }
  
    private void setGeckoDriverPath(String pathToGeckoDriver) {
        System.setProperty("webdriver.gecko.driver", location + pathToGeckoDriver);
    }
    
}