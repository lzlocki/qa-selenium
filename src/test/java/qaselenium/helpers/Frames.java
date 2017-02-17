package qaselenium.helpers;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Frames {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void nestedFrames() throws Exception {
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        Assert.assertEquals(driver.findElement(By.id("content")).getText(), "MIDDLE");
    }

    @Test
    public void iFrames() throws Exception {
        driver.get("http://the-internet.herokuapp.com/tinymce");
        driver.switchTo().frame("mce_0_ifr");
        WebElement editor = driver.findElement(By.id("tinymce"));
        String beforeText = editor.getText();
        editor.clear();
        editor.sendKeys("Hello World!");
        String afterText = editor.getText();
        assertThat(afterText, not(equalTo((beforeText))));
        driver.switchTo().defaultContent();
//        assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("An iFrame containing the TinyMCE WYSIWYG Editor"));
        Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "An iFrame containing the TinyMCE WYSIWYG Editor");
    }

}