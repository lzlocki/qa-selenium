package qaselenium.helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeDriverExample {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "vendor/chromedriver.exe");
        driver = new ChromeDriver();

//    Option 2
//    Start the chromedriver in your terminal
//    Connect to it via Selenium Remote, like so:
//        driver = new RemoteWebDriver(new URL("http://localhost:9515"), DesiredCapabilities.chrome());
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void chromeDriverTest() {
        driver.get("http://the-internet.herokuapp.com/");
        Assert.assertTrue(driver.getTitle().equals("The Internet"));
    }

}