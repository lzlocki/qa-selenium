package qaselenium.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class exampleDynamicPages {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();

    }

    @Test
    public void noSuchElementErrorTest() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish")).getText(), "Hello World!");
    }

    @Test
    public void webDriverWait8Test() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();
        new WebDriverWait(driver, 8).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish")).getText(), "Hello World!");
    }

    @Test
    public void webDriverWaitTimeOutErrorTest() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();
        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish")).getText(), "Hello World!");
    }

    @Test
    public void cleanUpTest() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();
        waitFor(By.cssSelector("#finish"));
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish")).getText(), "Hello World!");
    }

    public void waitFor(By locator) {
        new WebDriverWait(driver, 8).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
