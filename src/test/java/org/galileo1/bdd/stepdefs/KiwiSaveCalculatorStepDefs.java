package org.galileo1.bdd.stepdefs;

import java.io.IOException;

import org.galileo1.bdd.TestApplication;
import org.galileo1.bdd.pageobj.KiwiSaverCalculator;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@ContextConfiguration(classes = TestApplication.class)

public class KiwiSaveCalculatorStepDefs {

    /**
    * Dependency Injection
    */
   @Autowired
   private KiwiSaverCalculator kiwiSaverPage;
   @Autowired
   private WebDriver driver;

    /**
    * Local variables
    */
   private static boolean flag = false;
   private static boolean flag2 = false;

   /**
    * Cucumber Hooks
    */
    @After
    public void afterWeAreDone(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            
           //take base64Screenshot screenshot
           kiwiSaverPage.takeScreenshot();
           //capture browser logs 
           kiwiSaverPage.captureBrowserLogs();
           //switching out of frame
           driver.switchTo().defaultContent();
        }
    }

    /**
    * Step Definitions 
    */
    @Given("^a user wants to see their kiwisaver retirement projections$")
    public void a_user_wants_to_see_their_kiwisaver_retirement_projections() throws Throwable {
        if(flag==false) {
            flag=true;
            kiwiSaverPage.toPage();
        }
    }
    
    @Then("^user is able to see the projected value$")
    public void user_is_able_to_see_the_projected_value() throws Throwable {
        
        kiwiSaverPage.checkResultsArePresent();
        kiwiSaverPage.switchBackToDefault();
    }

    @When("^user selects the \"([^\"]*)\" information icon$")
    public void user_selects_the_information_icon(String arg1) throws Throwable {
        kiwiSaverPage.clickOnInformationIcon(arg1);
    }

    @Then("^user is able to see the information \"([^\"]*)\" below \"([^\"]*)\" field$")
    public void user_is_able_to_see_the_information_below_field(String arg1, String arg2) throws Throwable {
        kiwiSaverPage.checkInfoMessage(arg1, arg2);
    }


    @Given("^user who is \"([^\"]*)\" years old$")
    public void user_who_is_years_old(String arg1) throws Throwable { 
        kiwiSaverPage.switchToSelectedFrame();
        kiwiSaverPage.enterCurrentAge(arg1);
    }

    @Given("^has been \"([^\"]*)\"$")
    public void has_been(String arg1) throws Throwable {
        kiwiSaverPage.enterEmpStatus(arg1);
    }

    @Given("^has a prescribed intrest rate of \"([^\"]*)\"$")
    public void has_a_prescribed_intrest_rate_of(String arg1) throws Throwable {
        kiwiSaverPage.selectPIR(arg1);
    }

    @Given("^already have \"([^\"]*)\" in kiwisaver balance$")
    public void already_have_in_kiwisaver_balance(String arg1) throws Throwable {
        kiwiSaverPage.enterKiwiSaveBalance(arg1);
    }

    @Given("^is willing to contribute \"([^\"]*)\" dollar \"([^\"]*)\"$")
    public void is_willing_to_contribute_dollar(String arg1, String arg2) throws Throwable {
        kiwiSaverPage.enterVoluntaryContribution(arg1);
        kiwiSaverPage.selectVoluntaryContributionFrequncy(arg2);
    }

    @Given("^bears a \"([^\"]*)\" risk profile$")
    public void bears_a_risk_profile(String arg1) throws Throwable {
        kiwiSaverPage.selectRiskProfile(arg1);
    }

    @Given("^is expecting to save \"([^\"]*)\" at retiremnet$")
    public void is_expecting_to_save_at_retiremnet(String arg1) throws Throwable {
        kiwiSaverPage.enterSavingsGoal(arg1);
    }

    @When("^user enters these details in retirement calculator$")
    public void user_enters_these_details_in_retirement_calculator() throws Throwable {
        if(flag2==false) {
            flag2=true;
            kiwiSaverPage.calculateRetirementProjections();
        }
    }

    @Given("^has been earning \"([^\"]*)\" annually$")
    public void has_been_earning_annually(String arg1) throws Throwable {
        kiwiSaverPage.enterAnnualIncome(arg1);
    }

    @Given("^current makes a contribution of \"([^\"]*)\" percent$")
    public void current_makes_a_contribution_of_percent(String arg1) throws Throwable {
        kiwiSaverPage.selectMemberContribution(arg1);
    }
}
