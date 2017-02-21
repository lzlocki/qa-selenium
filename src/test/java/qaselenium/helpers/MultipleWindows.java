package qaselenium.helpers;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MultipleWindows {
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void multipleWindows() {
        driver.get("http://the-internet.herokuapp.com/windows");
        driver.findElement(By.cssSelector(".example a")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfWindowsToBe(2));
        Object[] allWindows = driver.getWindowHandles().toArray();
        System.out.println(driver.getWindowHandles());
        driver.switchTo().window(allWindows[0].toString());
        Assert.assertNotEquals(driver.getTitle(), "New Window");
        driver.switchTo().window(allWindows[1].toString());
        Assert.assertEquals(driver.getTitle(), "New Window");
    }

    @Test
    public void multipleWindowsRedux() {
        driver.get("http://the-internet.herokuapp.com/windows");

        // Get initial window handle
        String firstWindow = driver.getWindowHandle();
        // Create a newWindow variable
        String newWindow = "";
        // Trigger new window to open
        driver.findElement(By.cssSelector(".example a")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfWindowsToBe(2));
        // Grab all window handles
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println(allWindows);
        // Iterate through window handles collection
        // Find the new window handle, storing it in the newWindow variable
        for (String window : allWindows) {
            if (!window.equals(firstWindow)) {
                newWindow = window;
            }
        }

        // Switch to the first window & verify
        driver.switchTo().window(firstWindow);
        Assert.assertNotEquals(driver.getTitle(), "New Window");

        // Switch to the new window & verify
        driver.switchTo().window(newWindow);
        Assert.assertEquals(driver.getTitle(), "New Window");
    }

}