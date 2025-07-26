package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

import java.time.Duration;

import utilities.StepNameTracker;


public class LoginSteps {

    @Given("User is on Login Page")
    public void user_is_on_login_page() {
        StepNameTracker.setStepName("User is on Login Page");
        WebDriver driver = TestHooks.getDriver();
        driver.get("http://localhost:3000");
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        StepNameTracker.setStepName("User enters username and password");
        WebDriver driver = TestHooks.getDriver();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("Clicks on login button")
    public void clicks_on_login_button() {
        StepNameTracker.setStepName("Clicks on login button");
        WebDriver driver = TestHooks.getDriver();
        driver.findElement(By.id("loginBtn")).click();
    }

    @Then("User should be navigated to dashboard")
    public void user_should_be_navigated_to_dashboard() throws InterruptedException {
        StepNameTracker.setStepName("User should be navigated to dashboard");
        WebDriver driver = TestHooks.getDriver();
        Thread.sleep(1000); // Wait for dashboard to load
        assertTrue(driver.getPageSource().contains("ToDo Dashboard"));
    }

    @Then("User should see an alert {string}")
    public void user_should_see_an_alert(String expectedAlert) {
        StepNameTracker.setStepName("User should see an alert");
        WebDriver driver = TestHooks.getDriver();

        // Wait up to 5 seconds for alert to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String actualAlert = alert.getText();
        assertEquals(expectedAlert, actualAlert);
        alert.accept();
    }


}
