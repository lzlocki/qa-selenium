package qaselenium.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class exampleNotification {
    public class WorkWithBasicAuth {
        WebDriver driver;

        @BeforeTest
        public void setUp() throws Exception {
        	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        @Test
        public void RetryTestActions() {
            driver.get("http://the-internet.herokuapp.com/notification_message");
            assert retryIfNotificationMessageContains("please try again");
        }

        @AfterTest
        public void tearDown() throws Exception {
            driver.quit();
        }


        public String getNotificationMessage() {
            return driver.findElement(By.id("flash")).getText();
        }

        public boolean retryIfNotificationMessageContains(String message) {
            for (int count = 0; count < 3; count++) {
                if (getNotificationMessage().contains(message))
                    driver.navigate().refresh();
                else
                    return true;
            }
            return false;
        }
    }
}
