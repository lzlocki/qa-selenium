package qaselenium.helpers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;

public class BlackList {

    WebDriver driver;
    BrowserMobProxy proxy;

    @BeforeTest
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        driver = new FirefoxDriver(capabilities);
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.blacklistRequests("http:\\/\\/the-internet.herokuapp.com\\/slow_external", 404);
    }

    @AfterTest
    public void tearDown() throws Exception {
        proxy.stop();
        driver.quit();
    }

    @Test
    public void slowLoadingResourceBlocked() throws IOException {
        HttpClient client = HttpClientBuilder.
                                create().
                                setProxy(new HttpHost("127.0.0.1",proxy.getPort())).
                                build();
        HttpResponse response = client.execute(
                                    new HttpGet("http://the-internet.herokuapp.com/slow_external"));
        assertThat(response.getStatusLine().getStatusCode(), is(404));
    }

}
