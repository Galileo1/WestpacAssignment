package org.galileo1.bdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class DriverFactory {

  @Value("${app.browser}")
  private String browser;

  String location = System.getProperty("user.dir");

  private DriverFactory() {
    // Driver Singleton Factory Class. Do not nitialize this class from outside
  }

  private static DriverFactory instance = new DriverFactory();

  public static DriverFactory getInstance() {
    return instance;
  }

  // thread local driver object for webdriver
  ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
    @Override
    protected WebDriver initialValue() {
      return getChromeDriver(location);
    }
  };

  // call this method to get the driver object and launch the browser
  public WebDriver getDriver() {
    return driver.get();
  }

  // Quits the driver and closes the browser
  public void removeDriver() {
    driver.get().quit();
    driver.remove();
  }


  public WebDriver getChromeDriver(String location) {
    getRunningEnvironment();
		return new ChromeDriver(setCapabilities());
  }
  
  private ChromeOptions setCapabilities() {
    final ChromeOptions chromeOptions  = new ChromeOptions();
    chromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox", "--disable-gpu");
    return chromeOptions;
  }

  private void getRunningEnvironment() {
    
    String osname =System.getProperty("os.name").toLowerCase();
    if (osname.contains("windows")) {
          setChromeDriverPath("//src//test//resources//chromedriverwindows//chromedriver.exe");
    } else if (osname.contains("linux")) {
          setChromeDriverPath("//src//test//resources//chromedriverlinux//chromedriver");
    } else if (osname.contains("mac os")) {
          setChromeDriverPath("//src//test//resources//chromedriver");
    }

  }

  private void setChromeDriverPath(String pathToChromeDriver) {
    System.setProperty("webdriver.chrome.driver", location + pathToChromeDriver);
  }
}

