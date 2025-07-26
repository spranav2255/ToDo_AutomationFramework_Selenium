package stepDefinitions;

import com.aventstack.extentreports.*;
import io.cucumber.java.*;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import utilities.ExtentReportManager;
import utilities.ScreenshotUtil;
import utilities.StepNameTracker;
import utilities.WebDriverManagerUtil;

public class TestHooks {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> scenarioThread = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest scenarioTest = extent.createTest(scenario.getName());
        scenarioThread.set(scenarioTest);
        WebDriver driver = WebDriverManagerUtil.getDriver();
        driverThread.set(driver);
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        WebDriver driver = driverThread.get();
        if (driver == null) return;

        String scenarioName = scenario.getName();
        String stepName = StepNameTracker.getStepName();
        boolean isFailed = scenario.isFailed();

        try {
            // Handle alert before screenshot if it exists
            if (isAlertPresent(driver)) {
                driver.switchTo().alert().accept();
            }
        } catch (NoAlertPresentException ignored) {
        }

        String relativePath = ScreenshotUtil.captureScreenshot(driver, scenarioName, stepName, isFailed);
        String link = "<a href='" + relativePath + "' target='_blank'>View Screenshot</a>";

        if (isFailed) {
            scenarioThread.get().fail(stepName + " - " + link);
        } else {
            scenarioThread.get().info(stepName + " - " + link);
        }
    }

    private boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }



    @After
    public void afterScenario() {
        WebDriverManagerUtil.closeDriver();
    }

    @AfterAll
    public static void afterAll() {
        extent.flush();
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static ExtentTest getLogger() {
        return scenarioThread.get();
    }
}
