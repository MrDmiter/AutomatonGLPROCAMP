package com.test;

import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.MyAccountPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @Test
    public void testPageObjectTest() {
        driver.get("http://automationpractice.com/index.php");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickOnStickyLoginBtn();
        loginPage.enterLogin();
        loginPage.enterPass();
        System.out.println("entered creds in");
        MyAccountPage myAccountPage = loginPage.clickOnSignInBtn();
        System.out.println("logged in");
        myAccountPage.verifyName();
        LoginPage loginPageAfterLogout = myAccountPage.logoutMethod();
        loginPageAfterLogout.verifyPresenceOnLoginPage();

    }

    @After
    public void tearDown() {
    driver.quit();
    }
}
