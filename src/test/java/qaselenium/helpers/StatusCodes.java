package qaselenium.helpers;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StatusCodes {

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
    public void ResourceNotFound() throws MalformedURLException, IOException {
        driver.navigate().to("http://the-internet.herokuapp.com/status_codes/404");
        int code = getStatusCode("http://the-internet.herokuapp.com/status_codes/404");
        Assert.assertEquals(code, 404);
    }
    
    
    public int getStatusCode(String url) throws ClientProtocolException, IOException {
    	HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet(url));
        int responseCode = response.getStatusLine().getStatusCode();
        return responseCode;
    }

}
