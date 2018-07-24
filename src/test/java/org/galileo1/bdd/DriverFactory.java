package org.galileo1.bdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class DriverFactory {

  @Value("${app.browser}")
  private String browser;

  private DriverFactory() {
    // Do-nothing..Do not allow to initialize this class from outside
  }

  private static DriverFactory instance = new DriverFactory();

  public static DriverFactory getInstance() {
    return instance;
  }

  // thread local driver object for webdriver
  ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
    @Override
    protected WebDriver initialValue() {
      String location = System.getProperty("user.dir");
      System.out.println("+++++++++++++++++behwbfnicklefmeolrkn dmdd+_+__+++++++++++" +location + browser);
      return getChromeDriver(location);
      //return new FirefoxDriver(); // or other browser drivers
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
		System.setProperty("webdriver.chrome.driver", location + "//src//test//resources//chromedriver");
		return new ChromeDriver();
		// driver = (WebDriver) Proxy.newProxyInstance(RemoteWebDriver.class.getClassLoader(),
		// 		new Class[] { WebDriver.class, HasInputDevices.class, TakesScreenshot.class, HasCapabilities.class, Keyboard.class },
		// 		new MyInvocationHandler(driver));
		// return driver;
	}
}
