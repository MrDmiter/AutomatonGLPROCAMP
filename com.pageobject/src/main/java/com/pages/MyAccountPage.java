package com.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }


    @FindBy(xpath = "//div[@class='header_user_info']/descendant::span")
    private WebElement name;
    @FindBy(xpath = "//div/a[@class='logout']")
    private WebElement logoutBtn;

    public void verifyName() {
        Assert.assertEquals(name.getText(), "Dmytro Terentyev");
    }

    public LoginPage logoutMethod() {
        logoutBtn.click();
        return new LoginPage(driver);
    }


}
