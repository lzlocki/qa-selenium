package qaselenium.helpers;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Grid {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        //  capabilities.setBrowserName("firefox");
        //  capabilities.setPlatform(Platform.LINUX);
        //  capabilities.setVersion("3.6")
        //  capabilities.setCapability(,);

        String url = "http://localhost:4444/wd/hub";
        driver = new RemoteWebDriver(new URL(url), capabilities);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void gridTest() {
        // Before running make sure Selenium Grid is running and it has at least one node with desired browser
        // launch hub       java -jar ./vendor/selenium-server-standalone.jar -role hub
        // register node    java -jar ./vendor/selenium-server-standalone.jar -role node -hub http://localhost:4444/grid/register

        driver.get("http://the-internet.herokuapp.com/");
//        assertThat(driver.getTitle(), is(equalTo("The Internet")));
        Assert.assertEquals(driver.getTitle(), "The Internet");
    }

}
