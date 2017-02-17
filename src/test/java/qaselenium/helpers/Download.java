package qaselenium.helpers;
// Chrome import statements
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import java.util.HashMap;
//import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Download {
    WebDriver driver;
    File folder;

    @BeforeTest
    public void setUp() throws Exception {
        folder = new File(UUID.randomUUID().toString());
        folder.mkdir();

//        Firefox
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", folder.getAbsolutePath());
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "image/jpeg, application/pdf, application/octet-stream");
        profile.setPreference("pdfjs.disabled", true);
        driver = new FirefoxDriver(profile);

//        Chrome
//        System.setProperty("webdriver.chrome.driver", "vendor/chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.default_content_settings.popups", 0);
//        prefs.put("download.default_directory", folder.getAbsolutePath());
//        options.setExperimentalOption("prefs", prefs);
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        driver = new ChromeDriver(capabilities);
    }

    @AfterTest(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
        for (File file: folder.listFiles()) {
            file.delete();
        }
        folder.delete();
    }

    @Test
    public void download() throws Exception {
        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a")).click();
        // Wait 2 seconds to download file
        Thread.sleep(2000);
        File[] listOfFiles = folder.listFiles();
        // Make sure the directory is not empty
        Assert.assertNotEquals(listOfFiles.length, 0);
        for (File file : listOfFiles) {
            // Make sure the downloaded file(s) is(are) not empty
        	Assert.assertTrue(listOfFiles.length > 0);
        }
    }

}