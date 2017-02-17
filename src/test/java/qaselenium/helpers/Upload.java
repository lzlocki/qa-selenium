package qaselenium.helpers;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Upload {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    // If uploading to a Grid node or Sauce Labs, check out driver.setFileDetector()
    // https://saucelabs.com/resources/articles/selenium-file-upload

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void uploadFile() throws Exception {
        String filename = "plik.txt";
        File file = new File(filename);
        String path = file.getAbsolutePath();
        driver.get("http://the-internet.herokuapp.com/upload");
        driver.findElement(By.id("file-upload")).sendKeys(path);
        driver.findElement(By.id("file-submit")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("uploaded-files")));
        String text = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(text, filename);
    }

}