package org.galileo1.bdd.pageobj;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.galileo1.bdd.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public abstract class AbstractPage {

  public void navigateTo(final String value) {
    DriverFactory.getInstance().getDriver().navigate().to(value);
  }

  protected String getPageTitle() {
    return DriverFactory.getInstance().getDriver().getTitle();
  }

  protected void acceptAlert() {
    (new FluentWait<>(DriverFactory.getInstance().getDriver()))
        .withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.alertIsPresent());
    DriverFactory.getInstance().getDriver().switchTo().alert().accept();
  }

  protected boolean isThere(final String name) {
    final List<WebElement> listTitles = DriverFactory.getInstance().getDriver()
        .findElements(By.xpath("//h2[contains(text(), ' " + name + " ')]"));
    return listTitles.size() == 1;
  }

  protected void editText(final String id, final String value) {
    final WebElement element = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
        .withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    element.sendKeys(value);
  }

  protected void clickId(final String id) {
    final WebElement button = DriverFactory.getInstance().getDriver().findElement(By.id(id));
    final JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance()
        .getDriver();
    executor.executeScript("arguments[0].click();", button);
  }

  protected void clickXpathJs(final String value) {
    final WebElement button = DriverFactory.getInstance().getDriver()
        .findElement(By.xpath("//a[contains(text(), '" + value + "')]"));
    final JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance()
        .getDriver();
    executor.executeScript("arguments[0].click();", button);
  }

  protected void clickXpath(final String value) {
    DriverFactory.getInstance().getDriver()
        .findElement(By.xpath("//*[contains(text(), '" + value + "')]")).click();
  }

  protected boolean hasErrors() {
    final List<WebElement> errors = DriverFactory.getInstance().getDriver()
        .findElements(By.className("error"));
    return (errors.size() > 0) && errors.get(0).isDisplayed();
  }

  protected void enterTextUsingCssLocators(final String locator, final String text) {
    final WebElement element = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
    element.sendKeys(text);
  }


  protected void selectFromDropDown(final String locator, final String value) {
    final WebElement elementUl = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

        elementUl.click();

       String cont = "//span[contains(text()," + '"' + value + '"' + ")]";
       System.out.println(cont);
    
      final WebElement  elementUl3 = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
      .withTimeout(Duration.ofSeconds(10))
      .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cont)));
    
    //final List<WebElement> empStatus = elementUl2.findElements(By.cssSelector("li span"));
    //final WebElement ele = elementUl3.stream().filter(e-> e.getText() == value).findFirst().get();
    elementUl3.click();
  }

  protected void selectFromRadioButton(final String locator, final String value) {
    final List<WebElement> memberContribution = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locator)));

    final WebElement ele = memberContribution.stream()
                    .filter(e-> e.getAttribute("value").equals(value))
                    .findAny().get();
    ele.click();
  }

  protected boolean elementIsPresent(final String locator) {
    final WebElement results = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));

    return results.isDisplayed();    
  }

  protected void switchToIframe(final String locator) {
    // final WebElement element1 = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
    //     .withTimeout(10, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS)
    //     .ignoring(NoSuchElementException.class)
    //     .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));

        // WebDriver dr= DriverFactory.getInstance().getDriver();
        // int size = dr.findElements(By.tagName("iframe")).size();
        // for(int i=0; i<=size; i++){
        //   dr.switchTo().frame(i);
        //   int total=dr.findElements(By.cssSelector("html.wf-westpachelpaweb-n4-active body div.wpnib-field-current-age")).size();
        //   System.out.println(total);
        //       dr.switchTo().defaultContent();
        // }

        DriverFactory.getInstance().getDriver().switchTo().frame(0);
  }

  protected void switchToDefault() {
      DriverFactory.getInstance().getDriver().switchTo().defaultContent();
  }

  protected void clickButton(final String locator) {
    final WebElement buttonEle = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

        buttonEle.click();
  }

 protected boolean waitUntilElementIsRemoved( final By locator) {
    try {
        boolean present = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
        .withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofMillis(20))
            .ignoring(NoSuchElementException.class)
            .until(ExpectedConditions.invisibilityOfElementLocated(locator));
               
        return present;
    } catch (Exception e) {
        return false;
    } 
}

protected String getElementText(final String locator) {
  final WebElement element = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
  .withTimeout(Duration.ofSeconds(10))
  .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

        return element.getText();
}

  public void quit() {
    DriverFactory.getInstance().getDriver().quit();
  }

  public void refresh() {
    DriverFactory.getInstance().getDriver().navigate().refresh();
  }

  protected void enterText(final WebElement locator, final String text) {
    final WebElement element = findElement(locator);
    element.sendKeys(text);
  }

  protected void click(final WebElement locator) {
    final WebElement element = findElement(locator);
    element.click();
  }

  protected WebElement findElement(final WebElement locator) {
    return (new FluentWait<>(DriverFactory.getInstance().getDriver()))
        .withTimeout(Duration.ofSeconds(20))
        .pollingEvery(Duration.ofMillis(10))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOf(locator));
  }

  protected List<WebElement> findElements(final List<WebElement> locator) {
    return (new FluentWait<>(DriverFactory.getInstance().getDriver()))
        .withTimeout(Duration.ofSeconds(20))
        .pollingEvery(Duration.ofMillis(10))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfAllElements(locator));
  }


  protected void selectFromRadioButtons(final List<WebElement> locator, final String value) {
    final List<WebElement> elements = findElements(locator);

    final WebElement selectedElement = elements.stream()
                    .filter(e-> e.getAttribute("value").equals(value))
                    .findAny().get();
                    selectedElement.click();
  }

  protected void selectFromDropDowns(final WebElement locator, final String value) {
    System.out.println(locator);
    final WebElement elementDropDown = findElement(locator);
    elementDropDown.click();

    String listItems = "//span[contains(text()," + '"' + value + '"' + ")]";
    System.out.println(listItems);
    
    final WebElement listElement = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
      .withTimeout(Duration.ofSeconds(10))
      .pollingEvery(Duration.ofMillis(20))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(listItems)));

    listElement.click();
  }

  protected String getElementText(final WebElement locator) {
    final WebElement element = findElement(locator);
    return element.getText();
  }

  protected String getElementInnerText(final List<WebElement> locator) {
    
    String message = "";
    StringBuilder sb = new StringBuilder();
    List<String> allText = new ArrayList<String>();
    final List<WebElement> elements =
    findElements(locator);
    for (WebElement element : elements) {
      //System.out.println(element.getText());
      allText.add(element.getText());
      message += element.getText().replaceAll("\n","");
      sb.append(element.getText().trim().replaceAll("\n",""));
    }
    String res = sb.toString();
    // System.out.println("list Items: " + allText.stream()
    // .distinct()
    // .collect(Collectors.toList()));

    //System.out.println("lmessage : " + message);
    //System.out.println("stringbuilder : " + res);

    return res;
  }

}
