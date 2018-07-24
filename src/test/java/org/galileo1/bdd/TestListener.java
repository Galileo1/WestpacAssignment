package org.galileo1.bdd;

import org.testng.IExecutionListener;


public class TestListener  implements IExecutionListener {
    @Override
    public void onExecutionStart() {
        System.out.println("TestNG is staring the execution");
    }
    @Override
    public void onExecutionFinish() {
        System.out.println("Generating the Masterthought Report");
        //GenerateReport.GenerateMasterthoughtReport();
        System.out.println("TestNG has finished, the execution");
	}
}

	// private static String getTestMethodName(ITestResult iTestResult) {
	// 	return iTestResult.getMethod().getConstructorOrMethod().getName();
	// }

	// //Before starting all tests, below method runs.
	// @Override
	// public void onStart(ITestContext iTestContext) {
		
	// 	// ExtentTestManager.startClass(iTestContext.getClass().getName(),"");
	// 	// iTestContext.setAttribute("WebDriver", this.driver);
	// }

	// //After ending all tests, below method runs.
	// @Override
	// public void onFinish(ITestContext iTestContext) {
		
	// 	//Do tier down operations for extentreports reporting!
	// 	// ExtentTestManager.endTest();
	// 	// ExtentManager.getReporter().flush();
		
	// }

	// @Override
	// public void onTestStart(ITestResult iTestResult) {
	// 	//Start operation for extentreports.
    //     //ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"");
    //     System.out.println("Starting test " + iTestResult.getMethod().getMethodName());

	// }

	// @Override
	// public void onTestSuccess(ITestResult iTestResult) {
	// 	//Extentreports log operation for passed tests.
    //     //ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    //     System.out.println("Finished successfully " + iTestResult.getMethod().getMethodName());
	// }

	// @Override
	// public void onTestFailure(ITestResult iTestResult) {
	// 	//Get driver  and assign to local webdriver variable.
	// 	WebDriver webDriver = DriverFactory.getInstance().getDriver();

	// 	//Take base64Screenshot screenshot.
	// 	String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
	// 			getScreenshotAs(OutputType.BASE64);

	// 	//Extentreports log and screenshot operations for failed tests.
	// 	// ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
	// 	// 		ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	// }

	// @Override
	// public void onTestSkipped(ITestResult iTestResult) {
	// 	//Extentreports log operation for skipped tests.
	// 	//ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	// }

	// @Override
	// public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	// 	System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	// }
//}