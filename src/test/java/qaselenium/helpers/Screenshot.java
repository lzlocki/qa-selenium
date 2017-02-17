package qaselenium.helpers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
//import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Screenshot extends TestListenerAdapter {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
    }
    
//    @AfterTest
    public void tearDown() {
    	driver.quit();
    }
    
    @Override
    public void onTestFailure(ITestResult tr) {
    	getScreenshot(tr, "fail");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
    	getScreenshot(tr, "success");
    }
    @Override
    public void onFinish(ITestContext tc) {
    	driver.quit();
    }
    public void getScreenshot(ITestResult tr, String status) {
    	System.out.println(tr.getName());
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/" + status + "_" + tr.getClass().getName() + "_" + tr.getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
//    public TestRule watcher = new TestWatcher() {
//        @Override
//        protected void failed(Throwable throwable, Description description) {
//            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//            try {
//                FileUtils.copyFile(scrFile,
//                        new File("failshot_"
//                                + description.getClassName()
//                                + "_" + description.getMethodName()
//                                + ".png"));
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//
//        @Override
//        protected void finished(Description description) {
//            driver.quit();
//        }
//    };

    @Test
    public void OnError() {
        driver.get("http://the-internet.herokuapp.com");
        Assert.assertTrue(false);
    }

}
