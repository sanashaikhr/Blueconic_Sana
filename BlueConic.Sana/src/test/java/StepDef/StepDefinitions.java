package StepDef;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.submitSession;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;


public class StepDefinitions {
	
	

	WebDriver driver;
	
	submitSession sessionSubmit;
	
	
	@Given("^I start the browser$")
	public void i_start_the_browser() throws Throwable {
		
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(options);
		sessionSubmit = new submitSession(driver);
	}

	@When("^I navigate to \"([^\"]*)\"$")
	public void i_navigate_to(String arg1) throws Throwable {
		
		driver.get(arg1);
		
	}

	@When("^I click on the \"([^\"]*)\" tab$")
	public void i_click_on_the_tab(String arg1) throws Throwable {
		
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    	WebElement e = driver.findElement(By.xpath("(//a[text()='"+arg1+"'])[2]"));
    	
    	 //WebDriverWait wait = new WebDriverWait(driver, 10); // Wait up to 10 seconds
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
         WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//a[text()='"+arg1+"'])[2]"))));

         element.click();
		
	}

	@When("^I click on the \"([^\"]*)\" button$")
	public void i_click_on_the_button(String arg1) throws Throwable {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	WebElement element = driver.findElement(By.xpath("//a[text()='"+arg1+"']"));
    	element.click();
		
	}

	
	@Then("^I should be able filter session by \"([^\"]*)\"$")
	public void i_should_be_able_filter_session_by(String arg1) throws Throwable {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//button[text()='"+arg1+"']")).click();
		
		 File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
         String screenshotPath = "Screenshots/viewSessionPage.png";
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));
		List<WebElement> el = driver.findElements(By.xpath("//h5[@data-cy='day']"));
		List<String> days = new ArrayList<String>();
		
		for(int i=0;i < el.size();i++) {
			
			String day = el.get(i).getText().split(":")[1].trim();
			System.out.println(day);
			
			days.add(day);
			
		}
		
		Assert.assertTrue(sessionSubmit.containsOnlySpecificDaySession(days,arg1));
    	driver.quit();
		
	
		
	}
	
	@Then("^I should be able to create a session with details as  \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_create_a_session_with_details_as_and(String Title, String Description, String Day, String Level) throws Throwable {
		
		sessionSubmit.enterTitle(Title);
		sessionSubmit.enterDescription(Description);
		sessionSubmit.enterDay(Day);
		sessionSubmit.enterLevel(Level);
		sessionSubmit.clickSubmitBtn();
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "Screenshots/submitSession.png";
       FileUtils.copyFile(screenshotFile, new File(screenshotPath));
		
		Assert.assertTrue(sessionSubmit.succesMsg().equals("Session Submitted Successfully!"));
		
    	driver.quit();
		
	}
	
	



}
