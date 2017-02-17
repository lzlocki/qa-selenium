package qaselenium.helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KeyboardKeys {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void KeyboardKeysExample() throws Exception {
        driver.get("http://the-internet.herokuapp.com/key_presses");
        // Option 1
        driver.findElement(By.id("content")).sendKeys(Keys.SPACE);
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: SPACE");
        // Option 2
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.LEFT).build().perform();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: LEFT");
    }

}
