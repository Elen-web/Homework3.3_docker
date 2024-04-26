import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.DribbbleHomePage;
import pages.DribbbleSigninPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BaseTest {
    protected WebDriver driver;
    protected DribbbleHomePage homePage;
    protected DribbbleSigninPage signinPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new DribbbleHomePage(driver);
        signinPage = new DribbbleSigninPage(driver);
        driver.get("https://dribbble.com/");
    }



    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Check if driver is not null and is an instance of TakesScreenshot
            if (driver != null && driver instanceof TakesScreenshot) {
                TakesScreenshot camera = (TakesScreenshot) driver;
                File screenshot = camera.getScreenshotAs(OutputType.FILE);
                try {
                    File screenshotDirectory = new File("resources/screenshots");
                    if (!screenshotDirectory.exists()) {
                        screenshotDirectory.mkdirs();
                    }
                    Files.copy(screenshot.toPath(), new File(screenshotDirectory.getPath() + "/" + result.getName() + ".png").toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

