package com.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitersPracticeTest {
    private WebDriver driver;
    private WebDriverWait waiter;
    final static String USERNAME = "sebastian";
    final static String PASS = "qwerty12345";

    /**
     * Set up method for initializing driver and wait
     */
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        waiter = new WebDriverWait(driver, 10);
    }

    /**
     * Test which verify log in with non-valid data, ignoring iframe
     */
    @Test
    public void testS1Demo() {
        //Open the site
        driver.get("https://s1.demo.opensourcecms.com/s/44");
        //Switch to particular iframe
        driver.switchTo().frame("preview-frame");
        //Wait untill the following elements are present
        waiter.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='txtUsername']")),
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Submit']"))));
        //Enter username
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        usernameField.sendKeys(USERNAME);
        //Enter password
        driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys(PASS);
        driver.findElement(By.xpath("//input[@name='Submit']")).click();
        //Verify text of the warning message when creds are wrong
        Assert.assertEquals("Invalid credentials", driver.findElement(By.xpath("//span[@id='spanMessage']")).getText());
        driver.findElement(By.xpath("//input[@name='Submit']")).click();
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='spanMessage']")));
        //Verify text of the warning message when fields are empty
        Assert.assertEquals("Username cannot be empty", driver.findElement(By.xpath("//span[@id='spanMessage']")).getText());
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='spanMessage']")));
        //Attempt to log in only with username
        driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys(USERNAME);
        driver.findElement(By.xpath("//input[@name='Submit']")).click();
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='spanMessage']")));
        //Verify text of the warning message
        Assert.assertEquals("Password cannot be empty", driver.findElement(By.xpath("//span[@id='spanMessage']")).getText());
        //Return back from the iframe
        driver.switchTo().defaultContent();
        //Close frame
        driver.findElement(By.xpath("//span[contains(text(),'Remove Frame')]")).click();
        //Check whether frame is closed
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//iframe")));

        //Another way to verify presence of the iframe after closing
//        try {
//            driver.findElement(By.tagName("iframe"));
//            System.out.println("Is still present");
//        } catch (NoSuchElementException e) {
//            System.out.println(e);
//        }
    }

    /**
     * Close browser
     */
    @After
    public void tearDown() {
        driver.quit();
    }
}
