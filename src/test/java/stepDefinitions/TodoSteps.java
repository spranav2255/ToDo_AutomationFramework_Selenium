package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import static org.junit.Assert.*;
import utilities.StepNameTracker;

public class TodoSteps {

    @Given("User is logged in and on Dashboard")
    public void user_is_logged_in_and_on_dashboard() throws InterruptedException {
        StepNameTracker.setStepName("User is logged in and on Dashboard");
        WebDriver driver = TestHooks.getDriver();
        driver.get("http://localhost:3000");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.id("loginBtn")).click();
        Thread.sleep(1000);
    }

    @When("User adds a new todo with title {string}")
    public void user_adds_a_new_todo_with_title(String title) throws InterruptedException {
        StepNameTracker.setStepName("User adds a new todo with title: " + title);
        WebDriver driver = TestHooks.getDriver();
        WebElement input = driver.findElement(By.xpath("//input[@placeholder='New ToDo']"));
        input.clear();
        input.sendKeys(title);
        driver.findElement(By.xpath("//button[text()='Add']")).click();
        Thread.sleep(1000);
    }

    @Then("Todo with title {string} should be visible")
    public void todo_with_title_should_be_visible(String title) {
        StepNameTracker.setStepName("Todo with title should be visible: " + title);
        WebDriver driver = TestHooks.getDriver();
        assertTrue(driver.getPageSource().contains(title));
    }

    @When("User edits todo {string} to {string}")
    public void user_edits_todo_to(String oldTitle, String newTitle) throws InterruptedException {
        StepNameTracker.setStepName("User edits todo " + oldTitle + " to " + newTitle);
        WebDriver driver = TestHooks.getDriver();
        WebElement todo = driver.findElement(By.xpath("//li[contains(.,'" + oldTitle + "')]"));
        todo.findElement(By.xpath(".//button[text()='Edit']")).click();
        WebElement input = todo.findElement(By.xpath(".//input"));
        input.clear();
        input.sendKeys(newTitle);
        todo.findElement(By.xpath(".//button[text()='Save']")).click();
        Thread.sleep(1000);
    }

    @When("User deletes todo with title {string}")
    public void user_deletes_todo_with_title(String title) throws InterruptedException {
        StepNameTracker.setStepName("User deletes todo with title: " + title);
        WebDriver driver = TestHooks.getDriver();
        WebElement todo = driver.findElement(By.xpath("//li[contains(.,'" + title + "')]"));
        todo.findElement(By.xpath(".//button[text()='Delete']")).click();
        Thread.sleep(1000);
    }

    @Then("Todo with title {string} should not be visible")
    public void todo_with_title_should_not_be_visible(String title) {
        StepNameTracker.setStepName("Todo with title should not be visible: " + title);
        WebDriver driver = TestHooks.getDriver();
        assertFalse(driver.getPageSource().contains(title));
    }
}
