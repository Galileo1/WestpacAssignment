package org.galileo1.bdd.pageobj;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

public abstract class AbstractBasePage {

  @Autowired
  private WebDriver browser;

  private int poolingTime = 20;
  private int waitTime = 10;

  /**
	 * Navigate to a url 
	 * @param url
	 */
  public void navigateTo(final String url) {
    browser.navigate().to(url);
  }

  /**
	 * Get title of current page
	*/
  protected String getPageTitle() {
    return browser.getTitle();
  }

  /**
	 * Returns a boolean flag indicating element is displayed on the page or not
   * @param locator
	 */
  protected boolean elementIsDisplayed(final WebElement locator) {
    final WebElement results = (new FluentWait<>(browser))
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOf(locator));

    return results.isDisplayed();    
  }

  /**
	 * Returns a boolean flag indicating element is displayed on the page or not
   * @param locator
	 */
  protected boolean elementIsPresent(final By locator) {
    final WebElement results = (new FluentWait<>(browser))
    .withTimeout(Duration.ofSeconds(waitTime))
    .pollingEvery(Duration.ofMillis(poolingTime))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.presenceOfElementLocated(locator));

    return results.isDisplayed();    
  }
  
  /**
	 * Wait for page load to complete
	 */
  public void waitForPageLoaded() {
    ExpectedCondition<Boolean> expectation = new
            ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) browser).executeScript("return document.readyState").toString().equals("complete");
                }
            };
    try {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(browser, 30);
        wait.until(expectation);
    } catch (Throwable error) {
        Assert.fail("Timeout waiting for Page Load Request to complete.");
   }
  }

  /**
	 * Switch into of iframe
	 */
  protected void switchToIframe(final int index) {
        browser.switchTo().frame(index);
  }

  /**
	 * Switch out of iframe
	 */
  protected void switchToDefault() {
      browser.switchTo().defaultContent();
  }

  /**
	 * Wait until element has disappeared froom the page
   * @param locator 
	 */
  protected boolean waitUntilElementIsRemoved( final By locator) {
    try {
        boolean present = (new FluentWait<>(browser))
        .withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofMillis(20))
            .ignoring(NoSuchElementException.class)
            .until(ExpectedConditions.invisibilityOfElementLocated(locator));
               
        return present;
    } catch (Exception e) {
        return false;
    } 
  }

 /**
	 * Quit driver session 
	 */
  public void quit() {
    browser.quit();
  }

  /**
	 * Referesh the browser
	 */
  public void refresh() {
    browser.navigate().refresh();
  }

   /**
	 * Clear the Text field and enter the Text
   * 
	 * @param locator
   * @param text
	 */
  protected void enterText(final WebElement locator, final String text) {
    final WebElement element = findElement(locator);
    element.clear();
    element.sendKeys(text);
  }

   /**
	 * Safely click on the WebElement after ensuring that the element is present on the webpage
   * 
	 * @param locator
	 */
  protected void safeClick(final WebElement locator) {
    final WebElement element = findElement(locator);
    element.click();
  }

  /**
	 * Ensures that the WebElement appears on the page with in the given timeout.
   * Returns the WebElement.
   * 
	 * @param locator
	 */
  protected WebElement findElement(final WebElement locator) {
    return (new FluentWait<>(browser))
        .withTimeout(Duration.ofSeconds(20))
        .pollingEvery(Duration.ofMillis(10))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOf(locator));
  }

  /**
	 * Ensures that the list of WebElement are appear on the page with in the given timeout.
   * Returns the list of WebElements.
   * 
	 * @param locator
	 */
  protected List<WebElement> findElements(final List<WebElement> locator) {
    return (new FluentWait<>(browser))
        .withTimeout(Duration.ofSeconds(20))
        .pollingEvery(Duration.ofMillis(10))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfAllElements(locator));
  }

  /**
	 * Select from radio button group  
   * 
	 * @param locator
   * @param item
	 */
  protected void selectFromRadioButtons(final List<WebElement> locator, final String item) {
    final List<WebElement> radioButtonGroup = findElements(locator);

    final WebElement selectedItem = radioButtonGroup.stream()
                                      .filter(e-> e.getAttribute("value").equals(item))
                                      .findAny().get();
    selectedItem.click();
  }

  /**
	 * Select from unordered list of <span> items 
   * 
	 * @param locator
   * @param item
	 */
  protected void selectFromDropDowns(final WebElement locator, final String item) {
    
    //Select the unordered list
    final WebElement unorderList = findElement(locator);
    unorderList.click();

    //Select the list item
    String listItemLocator = "//span[contains(text()," + '"' + item + '"' + ")]";
    final WebElement selectedItem = findElement(browser.findElement(By.xpath(listItemLocator)));
    selectedItem.click();
  }

  /**
	 * Get the text string from a WebElement
   * 
	 * @param loader
	 */
  protected String getElementText(final WebElement locator) {
    final WebElement element = findElement(locator);
    return element.getText();
  }

  /**
	 * Get the text string from a list of WebElements
   * 
	 * @param loader
	 */
  protected String getTextFromElementList(final List<WebElement> locator) {
    StringBuilder sb = new StringBuilder();
    final List<WebElement> elements = findElements(locator);
    for (WebElement element : elements) {
      sb.append(element.getText().trim().replaceAll("\n",""));
    }
    String message = sb.toString();
    return message;
  }

}
