package qaselenium.helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DisabledElements {
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
    public void test() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
//        assertThat(dropdown.getOptions().get(0).isEnabled(), is(false));
        Assert.assertFalse(dropdown.getOptions().get(0).isEnabled());
    }

}
