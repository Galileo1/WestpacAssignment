package org.galileo1.bdd.pageobj;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.cucumber.listener.Reporter;

import org.galileo1.bdd.datamodel.DataModel;
import org.galileo1.bdd.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kiwisaverpage")
@PropertySource("classpath:application.properties")
public class Kiwisaver extends AbstractPage {
  @Value("${app.kiwisaverurl}")
  private String baseurl ;//= "https://www.westpac.co.nz/kiwisaver/calculators/kiwisaver-calculator/";

 
  
  private WebDriver driver = DriverFactory.getInstance().getDriver();
  
  
  private String title = "KiwiSaver Retirement Calculator - Westpac NZ";
  //private By currentAgeBlock = By.cssSelector("div.wpnib-field-current-age");
  // private WebElement currentAgeBlock = driver.findElement(By.cssSelector("div.wpnib-field-current-age"));
  // private WebElement currentAgeTextField = currentAgeBlock.findElement(By.cssSelector("input"));
  // private WebElement currentAgeInformatioIcon = currentAgeBlock.findElement(By.cssSelector("div.field-info button"));
  // private WebElement currentAgeFieldMessageBlock = currentAgeBlock.findElement(By.cssSelector("div.message-row"));
  // private WebElement currentAgeFieldMessageBlockIsPresent = currentAgeFieldMessageBlock.findElement(By.cssSelector("div.field-message.message-info.ng-binding.ng-hide"));
  // private WebElement currentAgeFieldMessage = currentAgeFieldMessageBlock.findElement(By.cssSelector("p"));
  
  @FindBy(css = "div.wpnib-field-current-age")
  public WebElement currentAgeBlock;
  
  @FindBy(css = "div.wpnib-field-current-age input")
  public WebElement currentAgeTextField;
  
  @FindBy(css = "div.wpnib-field-current-age div.field-info button")
  public WebElement currentAgeInformatioIcon;
  
  @FindBy(css = "div.wpnib-field-current-age div.message-row")
  public WebElement currentAgeFieldMessageContainer;
  
  @FindBy(css = "div.wpnib-field-current-age div.message-row div.field-message.message-info.ng-binding.ng-hide")
  public WebElement currentAgeFieldMessageBlock;
  
  @FindBy(css = "div.wpnib-field-current-age div.message-row p")
  public WebElement currentAgeFieldMessageText;
  
  @FindBy(css = "div.wpnib-field-employment-status")
  public WebElement empStatusBlock;
  
  @FindBy(css = "div.wpnib-field-employment-status div.control-well")
  public WebElement empStatusDropDown;
  
  @FindBy(css = "div.wpnib-field-employment-status div.field-info button")
  public WebElement empStatusInformatioIcon;
  
  @FindBy(css = "div.wpnib-field-employment-status div.message-row")
  public WebElement empStatusFieldMessageContainer;
  
  @FindBy(css = "div.wpnib-field-employment-status div.message-row div.field-message.message-info.ng-binding.ng-hide")
  public WebElement empStatusFieldMessageBlock;
  
  @FindBy(css = "div.wpnib-field-employment-status div.message-row p")
  public WebElement empStatusMessageText;
  
  @FindBy(css = "div.wpnib-field-pir-rate")
  public WebElement pirBlock;
  
  @FindBy(css = "div.wpnib-field-pir-rate div.control-well")
  public WebElement pirDropDown;
  
  @FindBy(css = "div.wpnib-field-pir-rate button")
  public WebElement pirInformatioIcon;
  
  @FindBy(css = "div.wpnib-field-pir-rate div.message-row")
  public WebElement pirFieldMessageContainer;
  
  @FindBy(css = "div.wpnib-field-pir-rate div.message-row div.field-message.message-info.ng-binding.ng-hide")
  public WebElement pirFieldMessageBlock;
  
  @FindBy(css = "div.wpnib-field-pir-rate div.message-row p")
	public WebElement pirMessageText;

  @FindBy(css = "div.wpnib-field-annual-income")
  public WebElement annualIncomeBlock;
  
  @FindBy(css = "div.wpnib-field-annual-income input")
  public WebElement annualIncomeTextField;
  
  @FindBy(css = "div.wpnib-field-annual-income button")
  public WebElement annualIncomeInformatioIcon;
  
  @FindBy(css = "div.wpnib-field-annual-income div.message-row")
  public WebElement annualIncomeMessageContainer;
  
  @FindBy(css = "div.wpnib-field-annual-income div.message-row div.field-message.message-info.ng-binding.ng-hide")
  public WebElement annualIncomeMessageBlock;
  
  @FindBy(css = "div.wpnib-field-annual-income div.message-row p")
	public WebElement annualIncomeMessageText;

  @FindBy(css = "div.wpnib-field-kiwisaver-member-contribution")
  public WebElement memberContributionBlock;
  
  @FindBy(css = "div.wpnib-field-kiwisaver-member-contribution input")
  public List<WebElement> memberContributionTextField;
  
  @FindBy(css = "div.wpnib-field-kiwisaver-member-contribution button")
  public WebElement memberContributionInformatioIcon;
  
  @FindBy(css = "div.wpnib-field-kiwisaver-member-contribution div.message-row")
  public WebElement memberContributionMessageContainer;
  
  @FindBy(css = "div.wpnib-field-kiwisaver-member-contribution div.message-row div.field-message.message-info.ng-binding.ng-hide")
  public WebElement memberContributionMessageBlock;
  
  @FindBy(css = "div.wpnib-field-kiwisaver-member-contribution div.message-row p")
  public WebElement memberContributionMessageText;
  
  @FindBy(css = "div.wpnib-field-kiwi-saver-balance")
  public WebElement kiwiSaverBalanceBlock;
  
  @FindBy(css = "div.wpnib-field-kiwi-saver-balance input")
  public WebElement kiwiSaverBalanceTextField;
  
  @FindBy(css = "div.wpnib-field-kiwi-saver-balance button")
  public WebElement kiwiSaverBalanceInformatioIcon;
  
  @FindBy(css = "div.wpnib-field-kiwi-saver-balance div.message-row")
  public WebElement kiwiSaverBalanceMessageContainer;
  
  @FindBy(css = "div.wpnib-field-kiwi-saver-balance div.message-row div.field-message.message-info.ng-binding.ng-hide")
  public WebElement kiwiSaverBalanceMessageBlock;
  
  @FindBy(css = "div.wpnib-field-kiwi-saver-balance div.message-row p")
  public WebElement kiwiSaverBalanceMessageText;
  
  @FindBy(css = "div.wpnib-field-voluntary-contributions")
  public WebElement voluntaryContributionBlock;
  
  @FindBy(css = "div.wpnib-field-voluntary-contributions input")
  public WebElement voluntaryContributionTextField;

  @FindBy(css = "div.wpnib-field-voluntary-contributions  div.control-well[ng-click]")
  public WebElement voluntaryContributionFrequency;
  
  @FindBy(css = "div.wpnib-field-voluntary-contributions button")
  public WebElement voluntaryContributionInformatioIcon;
  
  @FindBy(css = "div.wpnib-field-voluntary-contributions div.message-row")
  public WebElement voluntaryContributionMessageContainer;
  
  @FindBy(css = "div.wpnib-field-voluntary-contributions div.message-row div.field-message.message-info.ng-binding.ng-hide")
  public WebElement voluntaryContributionMessageBlock;
  
  @FindBy(css = "div.wpnib-field-voluntary-contributions div.message-row p")
  public WebElement voluntaryContributionMessageText;

  @FindBy(css = "div.wpnib-field-risk-profile")
  public WebElement riskProfileBlock;
  
  @FindBy(css = "div.wpnib-field-risk-profile input")
  public List<WebElement> riskProfileTextField;
  
  @FindBy(css = "div.wpnib-field-risk-profile button")
  public WebElement riskProfileInformatioIcon;
  
  @FindBy(css = "div.wpnib-field-risk-profile div.message-row")
  public WebElement riskProfileMessageContainer;
  
  @FindBy(css = "div.wpnib-field-risk-profile div.message-row div.field-message.message-info.ng-binding.ng-hide")
  public WebElement riskProfileMessageBlock;
  
  @FindBy(css = "div.wpnib-field-risk-profile div.message-row p")
  public WebElement riskProfileMessageText;

  @FindBy(css = "div.wpnib-field-savings-goal")
  public WebElement savingsGoalBlock;
  
  @FindBy(css = "div.wpnib-field-savings-goal input")
  public WebElement savingsGoalTextField;
  
  @FindBy(css = "div.wpnib-field-savings-goal button")
  public WebElement savingsGoalInformatioIcon;
  
  @FindBy(css = "div.wpnib-field-savings-goal div.message-row")
  public WebElement savingsGoalMessageContainer;
  
  @FindBy(css = "div.wpnib-field-savings-goal div.message-row div.field-message.message-info.ng-binding.ng-hide")
  public WebElement savingsGoalMessageBlock;
  
  @FindBy(css = "div.wpnib-field-savings-goal div.message-row p")
  public WebElement savingsGoalMessageText;

  //@FindBy(css = "div.message-row div:not(.ng-hide) p")
  @FindBy(xpath = "//div[contains(@class, 'field-message message-info ng-binding') and not(contains(@class, 'field-message message-info ng-binding ng-hide'))]/descendant::*")
  public WebElement displayedInformation;

  @FindBy(xpath = "//div[contains(@class, 'field-message message-info ng-binding') and not(contains(@class, 'field-message message-info ng-binding ng-hide'))]/child::*[not(name()='strong' or name()='a')]")
  public List<WebElement> displayedInformation1;

  @FindBy(css = "button.btn-results-reveal")
  public WebElement calculateButton;

  @PostConstruct
  public void init() {
    PageFactory.initElements(driver, this);
  }

  public void enterCurrentAge(final String text) {
    enterText(currentAgeTextField,text);
  } 

  public void enterEmpStatus(final String text) {
    selectFromDropDowns(empStatusDropDown,text);
  } 

  public void enterAnnualIncome(final String text) {
    enterText(annualIncomeTextField,text);
  } 

  public void selectMemberContribution(final String text) {
    System.out.println("Member contribution:" + text);
    selectFromRadioButtons(memberContributionTextField,text); 
  } 

  public void selectPIR(final String text) {
    selectFromDropDowns(pirDropDown,text);
  } 

  public void enterKiwiSaveBalance(final String text) {
    enterText(kiwiSaverBalanceTextField,text); 
  } 

  public void enterVoluntaryContribution(final String text) {
    enterText(voluntaryContributionTextField,text); 
  } 

  public void selectVoluntaryContributionFrequncy(final String text) {
    selectFromDropDowns(voluntaryContributionFrequency,text); 
  } 

  public void selectRiskProfile(final String text) {
    selectFromRadioButtons(riskProfileTextField,text);
  } 

  public void enterSavingsGoal(final String text) {
    enterText(savingsGoalTextField,text); 
  }

  public void calculateRetirementProjections() {
    click(calculateButton);
  }

  public String getInformationMessage() {
    //return getElementText(displayedInformation);
    return getElementInnerText(displayedInformation1);
  }

  public void selectAgeInformationIcon() {
    click(currentAgeInformatioIcon);
  }

  public void selectEmpStatusInformationIcon() {
    click(empStatusInformatioIcon);
  }
  public void selectPIRInformationIcon() {
    click(pirInformatioIcon);
  }
  public void selectCurrentBalanceInformationIcon() {
    click(kiwiSaverBalanceInformatioIcon);
  }
  public void selectVoluntaryContributionInformationIcon() {
    click(voluntaryContributionInformatioIcon);
  }
  public void selectRiskProfileInformationIcon() {
    click(riskProfileInformatioIcon);
  }
  public void selectSavingsGoalInformationIcon() {
    click(savingsGoalInformatioIcon);
  }



  public Kiwisaver toPage() {
    navigateTo(baseurl);
    assertThat(getPageTitle(), is(equalTo(title)));
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return this;
  }

  public void switchToSelectedFrame() {
    switchToIframe("iframe:nth-child(1)");
  }

  public void switchBackToDefault() {
    switchToDefault();
  }

  public void enterDetails3(DataModel data) {
    System.out.println(data);
    switchToIframe("iframe:nth-child(1)");
    enterCurrentAge(data.getAge());
    System.out.println("here");
    enterEmpStatus(data.getEmpStatus());
    System.out.println("here1");
    selectPIR(data.getPIR());
    selectRiskProfile(data.getRisk());
    System.out.println("here2");
    if (data.getEmpStatus().equals("Self-employed") || data.getEmpStatus().equals("Not employed") ) {
      System.out.println("here3");
        enterKiwiSaveBalance(data.getBal());
        enterVoluntaryContribution(data.getVolContib());
        selectVoluntaryContributionFrequncy(data.getFrequency());
        enterSavingsGoal(data.getGoal());
        System.out.println("here4");
    }

    if (data.getEmpStatus().equals("Employed")) {
      System.out.println("here5");
      enterAnnualIncome(data.getSalary());
      System.out.println("here7");
      selectMemberContribution(data.getEmpContrib());
      clickButton("button.btn-results-reveal");
      System.out.println("here6");
    }

    //switchToDefault();
  }


  public void enterDetails(List<String> data) {
       System.out.println(data);
       switchToIframe("iframe:nth-child(1)");
       enterTextUsingCssLocators("div.wpnib-field-current-age input", data.get(0));
       selectFromDropDown("div.wpnib-field-employment-status div.control-well", data.get(1));
       if (data.get(1).equals("Employed")) {
         System.out.println("yep" + data.get(8) + data.get(9));
         enterTextUsingCssLocators("div.wpnib-field-annual-income input", data.get(8));
         selectFromRadioButton("div.wpnib-field-kiwisaver-member-contribution input",data.get(9));
       }
       System.out.println("here0");
       selectFromDropDown("div.wpnib-field-pir-rate div.control-well",data.get(2));
       selectFromRadioButton("div.wpnib-field-risk-profile input",data.get(6));
       System.out.println("here");

       if (data.get(1).equals("Self-employed") || data.get(1).equals("Not employed") ) {
        enterTextUsingCssLocators("div.wpnib-field-kiwi-saver-balance input",data.get(3));
        enterTextUsingCssLocators("div.wpnib-field-voluntary-contributions input",data.get(4));
        selectFromDropDown("div.wpnib-field-voluntary-contributions  div.control-well[ng-click]", data.get(5));
        enterTextUsingCssLocators("div.wpnib-field-savings-goal input", data.get(7));
       }
       System.out.println("here1");

      
       
       
       System.out.println("here2");
       if (data.get(1).equals("Employed")) {
       clickButton("button.btn-results-reveal");
       }
       System.out.println("here3");

       assertTrue(elementIsPresent("div.results-field-group-set"));
    
    System.out.println("here4");
    switchToDefault();
    //refresh();
  }

  public void enterDetails2(List<String> data) {
    
    System.out.println(data);
    switchToIframe("iframe:nth-child(1)");
    enterTextUsingCssLocators("div.wpnib-field-current-age input", data.get(0));
    selectFromDropDown("div.wpnib-field-employment-status div.control-well", data.get(1));
    if (data.get(1).equals("Employed")) {
      System.out.println("yep" + data.get(8) + data.get(9));
      enterTextUsingCssLocators("div.wpnib-field-annual-income input", data.get(8));
      selectFromRadioButton("div.wpnib-field-kiwisaver-member-contribution input",data.get(9));
    }
    System.out.println("here0");
    selectFromDropDown("div.wpnib-field-pir-rate div.control-well",data.get(2));
    selectFromRadioButton("div.wpnib-field-risk-profile input",data.get(6));
    System.out.println("here");

    if (data.get(1).equals("Self-employed") || data.get(1).equals("Not employed") ) {
     enterTextUsingCssLocators("div.wpnib-field-kiwi-saver-balance input",data.get(3));
     enterTextUsingCssLocators("div.wpnib-field-voluntary-contributions input",data.get(4));
     selectFromDropDown("div.wpnib-field-voluntary-contributions  div.control-well[ng-click]", data.get(5));
     enterTextUsingCssLocators("div.wpnib-field-savings-goal input", data.get(7));
    }
    System.out.println("here1");

   
    
    
    System.out.println("here2");
    if (data.get(1).equals("Employed")) {
      clickButton("button.btn-results-reveal");
    }
  
    System.out.println("here3");

   
 
 System.out.println("here4");
 
 //refresh();
}

  public void checkResultsArePresent() {
    assertTrue(elementIsPresent("div.results-field-group-set"));
    switchToDefault();
    
  //   try {
	// 	Thread.sleep(30000);
	// } catch (InterruptedException e) {
	// 	// TODO Auto-generated catch block
	// 	e.printStackTrace();
	// }
    // assertTrue(elementIsPresent("div.results-field-group-set"));
    // switchToDefault();
    System.out.println("In then");
    // refresh();

  }

  public void clickInformationIcon() {
      switchToIframe("iframe:nth-child(1)");
      clickButton("div.wpnib-field-current-age div.field-info button");
  }

  public void checkHelpMessage(String expectedMessage) {
    String actualMessage  = "";
    if (waitUntilElementIsRemoved(By.cssSelector("div.wpnib-field-current-age div.field-message.message-info.ng-binding.ng-hide"))) {
        actualMessage  = getElementText("div.wpnib-field-current-age div.message-row p");
    }
    System.out.println("actualMessage"+ actualMessage);
    assertTrue(actualMessage.trim().equals(expectedMessage.trim()));
    switchToDefault();
  }

  public String getLabel(String flag) {
    String parent = "";
    switch (flag)   {
      case "currentAge" :
        parent = "div.wpnib-field-current-age";
        break;
      case "empStatus" :
        parent = "div.wpnib-field-employment-status";
        break;
      case "pir" :
        parent = "div.wpnib-field-pir-rate";
        break;
      default:
        parent = "div.wpnib-field-pir-rate";
    }

    return parent;
    
  }

  public void clickInformationIcon2(String flag) {
    switchToIframe("iframe:nth-child(1)");
    String infoButton = getLabel(flag) +" div.field-info button";
    System.out.println("button clicked"+ infoButton);
    clickButton(infoButton);
  }

  public void checkHelpMessage2(String expectedMessage, String flag) {
    String actualMessage  = "";
    System.out.print("=============:"+ driver.findElement(By.cssSelector( getLabel(flag)+" div.field-message.message-info.ng-binding.ng-hide")).getAttribute("class"));
    if (waitUntilElementIsRemoved(By.cssSelector( getLabel(flag)+" div.field-message.message-info.ng-binding.ng-hide"))) {
        actualMessage  = getElementText(getLabel(flag)+ " div.message-row p");
    }
    System.out.println("actualMessage"+ actualMessage);
    assertTrue(actualMessage.trim().equals(expectedMessage.trim()));
    switchToDefault();
  }


  public void toLogin() {
    clickXpathJs("Log in to Demo");
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public List<DataModel> mapTo(List<List<String>> inputData) {
    List<DataModel> memberDetails = inputData.stream()
              .map(p-> new DataModel(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9)))
              .collect(Collectors.toList());
    return memberDetails;
  }

  public DataModel mapToDataModel(List<String> inputData) {
      DataModel memberDetails = new DataModel(inputData.get(0), 
                                    inputData.get(1), 
                                    inputData.get(2), 
                                    inputData.get(3),
                                    inputData.get(4),
                                    inputData.get(5),
                                    inputData.get(6),
                                    inputData.get(7),
                                    inputData.get(8),
                                    inputData.get(9));
    return memberDetails;
  }

//   private  void typeMap() {
    
//     Map<RetirementCalFields, Runnable> runnableMap = new HashMap<>();
//         put(RetirementCalFields.AGE, () -> selectAgeInformationIcon());
//         put(RetirementCalFields.EMPSTATUS, () -> selectEmpStatusInformationIcon());
//         put(RetirementCalFields.CURRENTBALANCE, () -> selectCurrentBalanceInformationIcon());
//         put(RetirementCalFields.PIR, () -> selectPIRInformationIcon());
//         put(RetirementCalFields.RISKPROFILE, () -> selectRiskProfileInformationIcon());
//         put(RetirementCalFields.SAVINGSGOAL, () -> selectSavingsGoalInformationIcon());
//         put(RetirementCalFields.VOLUNTARYCONTRIBUTIONS, () -> selectVoluntaryContributionInformationIcon());
//     }
// }

private Map<RetirementCalFields, Runnable> typeMap = new HashMap<RetirementCalFields, Runnable>() {
  {
        put(RetirementCalFields.AGE, () -> selectAgeInformationIcon());
        put(RetirementCalFields.EMPSTATUS, () -> selectEmpStatusInformationIcon());
        put(RetirementCalFields.CURRENTBALANCE, () -> selectCurrentBalanceInformationIcon());
        put(RetirementCalFields.PIR, () -> selectPIRInformationIcon());
        put(RetirementCalFields.RISKPROFILE, () -> selectRiskProfileInformationIcon());
        put(RetirementCalFields.SAVINGSGOAL, () -> selectSavingsGoalInformationIcon());
        put(RetirementCalFields.VOLUNTARYCONTRIBUTIONS, () -> selectVoluntaryContributionInformationIcon());
    }
};

public void clickOnInformationIcon(String value) {
  switchToIframe("locator");
  RetirementCalFields infoIcon = RetirementCalFields.valueOf(value);
  if (!typeMap.containsKey(infoIcon)) {
      throw new IllegalArgumentException(String.format("%s is not supported", infoIcon));
  }

  typeMap.get(infoIcon).run();
}

public void checkInfoMessage(String expectedMessage, String label) {
  String actualMessage  = getInformationMessage();


  // System.out.println("actualMessage111: "+ actualMessage);
  // System.out.println("expectedMessage2222: "+ expectedMessage);
   assertTrue(actualMessage.trim().equals(expectedMessage.trim()));
  switchToDefault();
  
  //System.out.print("JSON DATA +_+_+_+__+_+_+: "+ jsonDataProvider.getMessageData(label).getMessage());
  //jsonDataProvider.getMessageData(label);
}

public void takeScreenshot() {
  
    //Take base64Screenshot screenshot.
    String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)driver).
    getScreenshotAs(OutputType.BASE64);

    //Attach the base64Screenshot to extent report
    try {
        Reporter.addScreenCaptureFromPath(base64Screenshot);
			} catch (IOException e) {
				e.printStackTrace();
			}
}

  public void captureBrowserLogs() {
    Reporter.addScenarioLog("=========== Browser Logs ===================");
    
    //Add browser logs  
    LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
    for (LogEntry entry : logEntries) {
        Reporter.addScenarioLog(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
    }
  }







}
