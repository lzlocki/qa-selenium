package qaselenium.helpers;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginGhostDriver {

    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("phantomjs.binary.path",
                System.getProperty("user.dir") +
                "/vendor/phantomjs-2.1.1-windows/bin/phantomjs.exe");
        driver = new PhantomJSDriver();
        // Alternatively, you can connect to it with Selenium Remote
        // You'll first need to launch the binary with the --webdriver flag and a port number
        // e.g., ./phantomjs --webdriver=8001
        // Then it's just a matter of using DesiredCapabilities and RemoteWebDriver
        //
        // DesiredCapabilities caps = new DesiredCapabilities();
        // caps.setBrowserName("phantomjs");
        // driver = new RemoteWebDriver(new URL("http://localhost:8001"), caps);
        //
        // Alternatively, PhantomJS can be connected as a node to a Selenium Grid
        // http://se.tips/ghost-driver-grid
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void withValidCredentials() throws IOException {
        driver.navigate().to("http://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button")).click();
        WebElement successMessage = driver.findElement(By.cssSelector(".flash.success"));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/screenshot.png"));
        FileUtils.copyFile(screenshot, new File("D:/screenshot.png"));
        Assert.assertTrue(successMessage.isDisplayed());
    }

}