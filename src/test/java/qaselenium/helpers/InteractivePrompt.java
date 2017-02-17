package qaselenium.helpers;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InteractivePrompt {

    public static void main(String[] args) {
        WebDriver driver = getDriver();
        while (true) {
            String userInput = readUserInput();
            if (userInput.toLowerCase().equals("q")) {
                System.out.println("Quitting...");
                driver.quit();
                System.exit(0);
            }
        }
    }

    private static WebDriver getDriver() {
    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        return new FirefoxDriver();
    }

    private static String readUserInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}