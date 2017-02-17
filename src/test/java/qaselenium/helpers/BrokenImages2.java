package qaselenium.helpers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrokenImages2 {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void allImagesLoaded() throws IOException {
        driver.navigate().to("http://the-internet.herokuapp.com/broken_images");
        List brokenImages = new ArrayList();
        List<WebElement> images = driver.findElements(By.tagName("img"));
        for(int image = 0; image < images.size(); image++) {
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(new HttpGet(images.get(image).getAttribute("src")));
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) { brokenImages.add(images.get(image).getAttribute("src")); }
        }

        List emptyCollection = new ArrayList();
        assertThat(brokenImages, is(emptyCollection));
//        Assert.assertEquals(brokenImages.iterator(), emptyCollection.iterator());
    }

}