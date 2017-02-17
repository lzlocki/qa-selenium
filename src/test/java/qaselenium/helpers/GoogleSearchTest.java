package qaselenium.helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleSearchTest {

    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void workWithBasicAuthTest() {
        GoogleSearch google = new GoogleSearch(driver);
        google.searchFor("elemental selenium tips");
        boolean result = google.searchResultPresent("Receive a Free, Weekly Tip");
        Assert.assertTrue(result);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}

class GoogleSearch {

    private final String BASE_URL = "http://www.google.com";
    WebDriver driver;

    By searchBox = By.name("q");
    By searchBoxSubmit = By.name("q");
    By topSearchResult = By.cssSelector("#rso .g:nth-child(1)");

    public GoogleSearch(WebDriver driver) {
        this.driver = driver;
        visit();
        Assert.assertTrue(verifyPage());
    }

    public void visit() {
        driver.get(this.BASE_URL);
    }

    public void searchFor(String searchTerm) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(searchTerm);
        driver.findElement(searchBoxSubmit).click();
    }

    public boolean searchResultPresent(String searchResult) {
        waitFor(topSearchResult);
        return driver.findElement(topSearchResult).getText().contains(searchResult);
    }
    
    public void waitFor(By locator) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean verifyPage() {
    	System.out.println(driver.getCurrentUrl());
        return driver.getCurrentUrl().contains("google");
    }
}
