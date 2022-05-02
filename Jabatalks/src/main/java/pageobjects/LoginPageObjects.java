package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class LoginPageObjects 
{
	@FindBy(xpath="//h2[text()='Create Your Account']")
	public static WebElement createAccountTitleText;

	@FindBy(xpath="//a[@class='ui-select-choices-row-inner']")
	public static List<WebElement> dropDownElements;
	
	@FindBy(xpath="//span[text()='English']")
	public static WebElement defaultDropDown;
	
	@FindBy(xpath="//input[@id='name']")
	public static WebElement fullNameTextBox;
	
	@FindBy(xpath="//input[@id='orgName']")
	public static WebElement organizationNameTextBox;
	
	@FindBy(xpath="//input[@id='singUpEmail']")
	public static WebElement emailTextBox;
	
	@FindBy(xpath="//span[text()='I agree to the']")
	public static WebElement termsConditionCheckBox;
	
	@FindBy(xpath="//button[text()='Get Started']")
	public static WebElement getStartedButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-custom']//span[@class='ng-binding']")
	public static WebElement confirmationText;
	
}
