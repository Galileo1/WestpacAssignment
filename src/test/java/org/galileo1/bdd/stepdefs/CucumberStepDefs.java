package org.galileo1.bdd.stepdefs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.galileo1.bdd.DriverFactory;
import org.galileo1.bdd.TestApplication;
import org.galileo1.bdd.datamodel.DataModel;
import org.galileo1.bdd.pageobj.Kiwisaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@ContextConfiguration(classes = TestApplication.class)

public class CucumberStepDefs {

  @Autowired
  private Kiwisaver kiwisavePage;
  
  
  private static boolean flag = false;
  private static boolean flag2 = false;

    @After
    public void afterWeAreDone(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            
           //Take base64Screenshot screenshot
           kiwisavePage.takeScreenshot();
           //capture browser logs 
           kiwisavePage.captureBrowserLogs();
           //switching out of frame
           DriverFactory.getInstance().getDriver().switchTo().defaultContent();
        }
    }

    @Given("^a user wants to see their kiwisaver retirement projections$")
    public void a_user_wants_to_see_their_kiwisaver_retirement_projections() throws Throwable {
        if(flag==false) {
            flag=true;
            kiwisavePage.toPage();
        }
    }

    @When("^user enters their details$")
    public void user_enters_their_details(DataTable empData) throws Throwable {
        List<List<String>> data = empData.raw();
        List<DataModel> memberDetails = kiwisavePage.mapTo(data);
        for (DataModel details : memberDetails) {
        System.out.println(details.getAge() + details.getBal() );
            kiwisavePage.enterDetails3(details);
        }
    }
    
    @Then("^user is able to see the projected value$")
    public void user_is_able_to_see_the_projected_value() throws Throwable {
        
        kiwisavePage.checkResultsArePresent();
        kiwisavePage.switchBackToDefault();
    }

    @When("^user enters their \"([^\"]*)\" in calculator$")
        public void user_enters_their_in_calculator(String arg1) throws Throwable {
        List<String> memberData = Arrays.asList(arg1.split(";"));
        DataModel memberDetails = kiwisavePage.mapToDataModel(memberData);
        //System.out.println("memberDetails:  "+memberDetails.getAge());
        kiwisavePage.enterDetails3(memberDetails);
    }

@When("^user selects the information icon$")
public void user_selects_the_information_icon() throws Throwable {
    
    kiwisavePage.clickInformationIcon();
}

@Then("^user is able to see the information \"([^\"]*)\"$")
public void user_is_able_to_see_the_information(String arg1, String arg2) throws Throwable {
    
    System.out.println(arg1);
    System.out.println(arg2);
    kiwisavePage.checkHelpMessage(arg2);
  
}

@When("^user selects the \"([^\"]*)\" information icon$")
public void user_selects_the_information_icon(String arg1) throws Throwable {
    
    kiwisavePage.clickOnInformationIcon(arg1);
}

@Then("^user is able to see the information \"([^\"]*)\" below \"([^\"]*)\" field$")
public void user_is_able_to_see_the_information_below_field(String arg1, String arg2) throws Throwable {
    
    //throw new PendingException();
    //kiwisavePage.checkHelpMessage2(arg1,arg2);
    kiwisavePage.checkInfoMessage(arg1, arg2);
}


@Given("^user who is \"([^\"]*)\" years old$")
public void user_who_is_years_old(String arg1) throws Throwable {
    
    kiwisavePage.switchToSelectedFrame();
    kiwisavePage.enterCurrentAge(arg1);
}

@Given("^has been \"([^\"]*)\"$")
public void has_been(String arg1) throws Throwable {
    
    kiwisavePage.enterEmpStatus(arg1);
}

@Given("^has a prescribed intrest rate of \"([^\"]*)\"$")
public void has_a_prescribed_intrest_rate_of(String arg1) throws Throwable {
    
    kiwisavePage.selectPIR(arg1);
}

@Given("^already have \"([^\"]*)\" in kiwisaver balance$")
public void already_have_in_kiwisaver_balance(String arg1) throws Throwable {
    
    kiwisavePage.enterKiwiSaveBalance(arg1);
}

@Given("^is willing to contribute \"([^\"]*)\" dollar \"([^\"]*)\"$")
public void is_willing_to_contribute_dollar(String arg1, String arg2) throws Throwable {
    
    kiwisavePage.enterVoluntaryContribution(arg1);
    kiwisavePage.selectVoluntaryContributionFrequncy(arg2);
}

@Given("^bears a \"([^\"]*)\" risk profile$")
public void bears_a_risk_profile(String arg1) throws Throwable {
    
    kiwisavePage.selectRiskProfile(arg1);
}

@Given("^is expecting to save \"([^\"]*)\" at retiremnet$")
public void is_expecting_to_save_at_retiremnet(String arg1) throws Throwable {
    
    kiwisavePage.enterSavingsGoal(arg1);
}

@When("^user enters these details in retirement calculator$")
public void user_enters_these_details_in_retirement_calculator() throws Throwable {
    
    System.out.print("flag-==-=-=-=-=-=-=-=-=-=-=-=-=--= " +flag);
    if(flag2==false) {
      flag2=true;
      kiwisavePage.calculateRetirementProjections();
      
    }
  }

  
@Given("^has been earning \"([^\"]*)\" annually$")
public void has_been_earning_annually(String arg1) throws Throwable {
    
    kiwisavePage.enterAnnualIncome(arg1);
}

@Given("^current makes a contribution of \"([^\"]*)\" percent$")
public void current_makes_a_contribution_of_percent(String arg1) throws Throwable {
    
    kiwisavePage.selectMemberContribution(arg1);
}
    




// @Before
// public void setUp() throws Throwable {
//   kiwisavePage.toPage();
// }

  // @After
  // public void cleanupWindows() throws Throwable {
  //   loginPage.quit();
  // }
}
