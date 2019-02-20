package com.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailTextField;
    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passTextField;
    @FindBy(xpath = "//button[@id='SubmitLogin']/span")
    private WebElement signInBtn;
    private WebDriver driver;
    private WebDriverWait wait;


    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }
    public void enterLogin(){
        emailTextField.sendKeys("gavuyabavu@digitalmail.info");
    }
    public void enterPass(){
        passTextField.sendKeys("12345");
    }

    public MyAccountPage clickOnSignInBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
        signInBtn.click();
    return new MyAccountPage(driver);
    }
    public void verifyPresenceOnLoginPage(){
        Assert.assertEquals(driver.getTitle(),"Login - My Store");
    }
}
