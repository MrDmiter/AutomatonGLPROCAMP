package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    @FindBy(xpath="//div/a[@class='login']")
    private WebElement stickySignInBtn;
    private WebDriver driver;
    private WebDriverWait wait;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(divPage));
    }

    public LoginPage clickOnStickyLoginBtn(){
        stickySignInBtn.click();
        return new LoginPage(driver);
    }
}
