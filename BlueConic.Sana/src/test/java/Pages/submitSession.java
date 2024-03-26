package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class submitSession {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//input[@name='title']")
	private WebElement title;
	
	@FindBy(xpath = "//input[@name='description']")
	private WebElement description;
	
	@FindBy(xpath = "//input[@name='day']")
	private WebElement day;
	
	@FindBy(xpath = "//input[@name='level']")
	private WebElement level;
	
	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement submitBtn;
	
	@FindBy(xpath = "(//p)[1]")
	private WebElement msg;
	
	public submitSession(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}

	
	public void enterTitle(String Title) {
		
		title.sendKeys(Title);
		
	}
	
   public void enterDescription(String Description) {
		
	description.sendKeys(Description);
		
	}

   public void enterDay(String Day) {
	
	day.sendKeys(Day);
	
    }

   public void enterLevel(String Level) {
	
	level.sendKeys(Level);
	
  }
   
   public void clickSubmitBtn() {
	   
	   submitBtn.click();
   }
   
   
   public String succesMsg() {
	   
	   
	   return msg.getText();
   }
   
   public  boolean containsOnlySpecificDaySession(List<String> list, String day) {
       for (String str : list) {
           if (!str.equals(day)) {
               return false;
           }
       }
       return true;
   }
   
}
