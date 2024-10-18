package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReport;
	public ExtentTest test;
	
	String reportName;
	
	public void onStart(ITestContext testContext) {

		String dateAndTime = new SimpleDateFormat("YYYY.MM.DD.HH.mm.ss").format(new Date());
		reportName = "Report For "+ dateAndTime + ".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
		sparkReporter.config().setTheme(Theme.STANDARD);
	    sparkReporter.config().setDocumentTitle("Automation Test Report");
	    sparkReporter.config().setReportName("Sample Extent Report");
	    
	    extentReport = new ExtentReports();
	    extentReport.attachReporter(sparkReporter);

        // Set system information
	    extentReport.setSystemInfo("Application", "Pet Strore App");
	    extentReport.setSystemInfo("Tester", "Vaishali");
	    extentReport.setSystemInfo("Environment", "QA");
	    extentReport.setSystemInfo("Browser", "Chrome");
	    extentReport.setSystemInfo(dateAndTime, dateAndTime);
	}
	
	
    public void onTestSuccess(ITestResult result) {
        // Log the success in the report
    	test = extentReport.createTest(result.getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        // Log the failure in the report
    	test = extentReport.createTest(result.getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.createNode(result.getName());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        // Log the skipped test in the report
    	test = extentReport.createTest(result.getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.createNode(result.getName());
        test.log(Status.SKIP, "Test Failed");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish(org.testng.ITestContext context) {
        // Flush the report once all the tests are done
        extentReport.flush();
    }
}
