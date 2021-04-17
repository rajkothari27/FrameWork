package ProjectReports;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ProjectReports
{
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeTest
    public void setExtent() {
        // specify location of the report
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/Amazon.html");

        htmlReporter.config().setDocumentTitle("Amazon Report"); // Title of report
        htmlReporter.config().setReportName("Front end Testing"); // Name of the report
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Passing General information
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environemnt", "QA");
        extent.setSystemInfo("user", "Raj");
    }

    @AfterMethod
    public void tearDown(ITestResult result)  {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
        }

    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }
}