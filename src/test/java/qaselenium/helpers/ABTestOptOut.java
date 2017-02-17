package qaselenium.helpers;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ABTestOptOut {

    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void WithCookieAfterVisitingPage() {
        driver.get("http://the-internet.herokuapp.com/abtest");
        String headingText = driver.findElement(By.tagName("h3")).getText();
        Assert.assertTrue(headingText.startsWith("A/B Test"));
        driver.manage().addCookie(new Cookie("optimizelyOptOut", "true"));
        driver.navigate().refresh();
        headingText = driver.findElement(By.cssSelector("h3")).getText();
        Assert.assertTrue(headingText.equals("No A/B Test"));
    }

    @Test
    public void WithCookieBeforeVisitingPage() {
        driver.get("http://the-internet.herokuapp.com");
        driver.manage().addCookie(new Cookie("optimizelyOptOut", "true"));
        driver.get("http://the-internet.herokuapp.com/abtest");
        Assert.assertTrue(driver.findElement(By.cssSelector("h3")).getText().equals("No A/B Test"));
    }

    @Test
    public void WithOptOutUrl() {
        driver.get("http://the-internet.herokuapp.com/abtest?optimizely_opt_out=true");
        driver.switchTo().alert().dismiss();
        Assert.assertTrue(driver.findElement(By.cssSelector("h3")).getText().equals("No A/B Test"));
    }

}
