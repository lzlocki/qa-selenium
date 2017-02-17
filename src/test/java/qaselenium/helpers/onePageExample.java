package qaselenium.helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class onePageExample {

    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void workWithBasicAuthTest() {
        GoogleSearchOnePage google = new GoogleSearchOnePage(driver);
        google.searchFor("elemental selenium tips");
        boolean result = google.searchResultPresent("Receive a Free, Weekly Tip");
        assert (result);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}

class GoogleSearchOnePage extends Base {

    WebDriver driver;

    By searchBox = By.name("q");
    By searchBoxSubmit = By.name("q");
    By topSearchResult = By.cssSelector("#rso .g");

    public GoogleSearchOnePage(WebDriver driver) {
        super(driver, "http://www.google.com");
        visit();
        verifyPage();
    }


    public void searchFor(String searchTerm) {
        clear(searchBox);
        type(searchBox, searchTerm);
        clickOn(searchBoxSubmit);
    }

    public boolean searchResultPresent(String searchResult) {
        waitFor().until(displayed(topSearchResult));
        return textOf(topSearchResult).contains(searchResult);
    }


    public void verifyPage() {
        assert (title().contains("google"));
    }
}

class Base {

    private String BASE_URL;
    private final WebDriver driver;

    public Base(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.BASE_URL = baseUrl;
    }

    public void visit() {
        driver.get(BASE_URL);
    }

    public void visit(String url) {
        driver.get(BASE_URL + url);
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void clear(By locator) {
        find(locator).clear();
    }

    public void type(By locator, String input) {
        find(locator).sendKeys(input);
    }

    public void clickOn(By locator) {
        find(locator).click();
    }

    public ExpectedCondition displayed(By locator) {
        return ExpectedConditions.presenceOfElementLocated(locator);
    }

    public String textOf(By locator) {
        return find(locator).getText();
    }

    public String title() {
        return driver.getCurrentUrl();
    }

    public WebDriverWait waitFor() {
        return new WebDriverWait(driver, 5);
    }
}