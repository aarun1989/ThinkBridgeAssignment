package testcases;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import pageobjects.LoginPageObjects;
public class RegistrationPageTest extends CommonFunctions
{
	@Test
	public void RegistrationTest()
	{
		HomePageValidation();
		VerifyLanguageDropdown(); 		
		ValidateEmailTest();
	}

	public void ValidateEmailTest()
	{		
		Random r=new Random();
		int x = r.nextInt(1000);   
		String expectedConfirmationText="A welcome email has been sent. Please check your email.";
		String expectedConfirmationText1="A user already has registered as an admin for this company. A notification has been sent to the admin for approval.";
		String expectedConfirmationText2="The request is invalid.";
		LoginPageObjects.fullNameTextBox.clear();
		LoginPageObjects.fullNameTextBox.sendKeys(prop.getProperty("fullname"));
		LoginPageObjects.organizationNameTextBox.clear();
		LoginPageObjects.organizationNameTextBox.sendKeys(prop.getProperty("organisation")+x);
		LoginPageObjects.emailTextBox.clear();
		LoginPageObjects.emailTextBox.sendKeys(prop.getProperty("email"));
		LoginPageObjects.termsConditionCheckBox.click();
		Assert.assertTrue(LoginPageObjects.getStartedButton.isEnabled());
		LoginPageObjects.getStartedButton.click();
		Assert.assertTrue(LoginPageObjects.confirmationText.isDisplayed());
		String actualConfirmationText=LoginPageObjects.confirmationText.getText();		
		if(actualConfirmationText.equals(expectedConfirmationText1))
		{
			System.out.println("User has already registered and notify is sent to Admin for approval");
		}
		else if(actualConfirmationText.equals(expectedConfirmationText))
		{
			System.out.println("Confirmation Email has been sent to user");
		}
		else if(actualConfirmationText.equals(expectedConfirmationText2))
		{
			System.out.println("The request is invalid");
		}							
	}
	
	//Method used to Validate whether dropdown values Dutch and English are displayed
	public void VerifyLanguageDropdown()
	{
		String dropdownText;
		
		LoginPageObjects.defaultDropDown.click();

		List<WebElement> language=LoginPageObjects.dropDownElements;

		for(int i=0;i<language.size();i++)
		{
			 dropdownText=language.get(i).getText();

			if(dropdownText.equals("English")||dropdownText.equals("Dutch"))
			{
				System.out.println("Dropdown Language "+dropdownText+" is present in dropdown");
			}
			else
			{
				System.out.println("Dropdown Language "+dropdownText+" is not dropdown");	
			}
		}

	}

	//method used to check whether User is successfully navigated to landing page
	public void HomePageValidation()
	{
		PageFactory.initElements(driver, LoginPageObjects.class);

		Assert.assertFalse(LoginPageObjects.getStartedButton.isEnabled());

		if(LoginPageObjects.createAccountTitleText.isDisplayed())
		{
			String actualTitle=LoginPageObjects.createAccountTitleText.getText();

			Assert.assertEquals(actualTitle,"Create Your Account");

			if(actualTitle.equals("Create Your Account"))
			{
				System.out.println("Jabatalks Create Account Page is displayed");
			}
			else
			{
				System.out.println("Jabatalks Create Account Page is not displayed");
			}
		}
	}
}
