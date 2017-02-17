package qaselenium.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class exampleLocatorStrategy {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();

    }

    @Test
    public void locatorStategyCSS() {
        driver.get("http://the-internet.herokuapp.com/download");
        String link = driver.findElement(By.cssSelector("a")).getAttribute("href");
        System.out.println(link);
    }

    @Test
    public void locatorStategyPreciseCSS() {
        driver.get("http://the-internet.herokuapp.com/download");
        String link = driver.findElement(By.cssSelector("#content a")).getAttribute("href");
        System.out.println(link);
    }

    @Test
    public void locatorStategyExactCSS() {
        driver.get("http://the-internet.herokuapp.com/download");
        String link = driver.findElement(By.cssSelector("#content .example a")).getAttribute("href");
        System.out.println(link);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
