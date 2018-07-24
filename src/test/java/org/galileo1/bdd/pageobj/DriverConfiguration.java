package org.galileo1.bdd.pageobj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("org.galileo1")
public class DriverConfiguration  {
    String location = System.getProperty("user.dir");

    
    // @Value("${browser}")
    // private String browser;
    
    
    @Bean(destroyMethod="quit")
    public WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", location + "//src//test//resources//chromedriver");
        return new ChromeDriver();
    }
}