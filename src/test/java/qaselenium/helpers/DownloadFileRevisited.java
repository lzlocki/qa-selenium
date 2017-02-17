package qaselenium.helpers;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DownloadFileRevisited {
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
    public void downloadFileRevisitedTest() throws Exception {
        driver.get("http://the-internet.herokuapp.com/download");
        String link = driver.findElement(By.cssSelector(".example a:nth-of-type(1)")).
                                            getAttribute("href");

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpHead request = new HttpHead(link);
        HttpResponse response = httpClient.execute(request);
        String contentType = response.getFirstHeader("Content-Type").getValue();
        int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());

        Assert.assertEquals(contentType, "application/octet-stream");
        Assert.assertTrue(contentLength > 0);
    }

}