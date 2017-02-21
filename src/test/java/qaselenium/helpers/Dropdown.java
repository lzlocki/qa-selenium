package qaselenium.helpers;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Dropdown {
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

//    @Test
    public void dropdownTest() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        WebElement dropdownList = driver.findElement(By.id("dropdown"));
        List<WebElement> options = dropdownList.findElements(By.tagName("option"));
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getText().equals("Option 1")) {
                options.get(i).click();
            }
        }
        String selectedOption = "";
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).isSelected()) {
                selectedOption = options.get(i).getText();
            }
        }
        Assert.assertEquals(selectedOption, "Option 1");
    }

    @Test
    public void dropdownTestRedux() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        Select selectList = new Select(driver.findElement(By.id("dropdown")));
        selectList.selectByVisibleText("Option 1");
        // You could also use select.selectByValue("1");
//        assertThat(selectList.getFirstSelectedOption().getText(), is(equalTo("Option 1")));
        Assert.assertEquals(selectList.getFirstSelectedOption().getText(), "Option 1");
    }

}