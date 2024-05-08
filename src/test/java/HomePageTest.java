import messages.AssertionMessages;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class HomePageTest extends BaseTest {



    @Test
    public void testNavigateAndPostJob() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://dribbble.com/");
            DribbbleHomePage homePage = new DribbbleHomePage(driver);
            DribbbleHiringPage hiringPage = homePage.clickFindDesigners();
            hiringPage.clickPostYourJob();


            String expectedUrl = "https://dribbble.com/jobs/new";
            Assert.assertEquals("Should navigate to post job page", expectedUrl, driver.getCurrentUrl());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testHomePageToLoginToSignUp() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("https://dribbble.com/");

            DribbbleHomePage homePage = new DribbbleHomePage(driver);
            DribbbleSigninPage signinPage = homePage.clickLoginButton();


            Assert.assertTrue("Login page should be displayed", signinPage.isLoginPageDisplayed());
            DribbbleSignupPage signupPage = signinPage.clickSignUpLink();


        } finally {
            driver.quit();
        }
    }


//
//    @Test
//    public void testNavigateAndPostJob() throws MalformedURLException {
//
//        WebDriver driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), DesiredCapabilities.chrome());
//
//        try {
//            driver.get("https://dribbble.com/");
//            DribbbleHomePage homePage = new DribbbleHomePage(driver);
//            DribbbleHiringPage hiringPage = homePage.clickFindDesigners();
//            hiringPage.clickPostYourJob();
//
//
//            String expectedUrl = "https://dribbble.com/jobs/new";
//            Assert.assertEquals("Should navigate to post job page", expectedUrl, driver.getCurrentUrl());
//        } finally {
//            driver.quit();
//        }
//    }




    @Test
    public void navigateToSignUpPage() {
        homePage.clickSignUpButton();
        String expectedUrl = "https://dribbble.com/signup/new";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("URL mismatch for sign-up page", expectedUrl, actualUrl);
    }

    @Test
    public void verifySearchFunctionality() {
        homePage.search("Test");
        Assert.assertTrue(AssertionMessages.SEARCH_RESULTS_PAGE_NOT_DISPLAYED, driver.getCurrentUrl().contains("/search"));
    }

    @Test
    public void verifyGetStartedButton() {
        homePage.clickGetStartedButton();
        Assert.assertTrue(AssertionMessages.SIGN_UP_PAGE_NOT_DISPLAYED, driver.getCurrentUrl().contains("/signup"));
    }

    @Test
    public void verifySignUpButton() {
        homePage.clickSignUpButton();
        Assert.assertTrue(AssertionMessages.SIGN_UP_PAGE_NOT_DISPLAYED, driver.getCurrentUrl().contains("/signup"));
    }

    @Test
    public void verifyLogInButton() {
        homePage.clickLoginButton();
        Assert.assertTrue(AssertionMessages.LOGIN_PAGE_NOT_DISPLAYED, driver.getCurrentUrl().contains("/session"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            if (driver instanceof TakesScreenshot) {
                TakesScreenshot camera = (TakesScreenshot) driver;
                File screenshot = camera.getScreenshotAs(OutputType.FILE);
                String screenshotDirectory = "screenshots/";
                String screenshotName = "failure_" + System.currentTimeMillis() + ".png";
                String screenshotPath = screenshotDirectory + screenshotName;
                try {
                    Files.createDirectories(Paths.get(screenshotDirectory));
                    Files.move(screenshot.toPath(), new File(screenshotPath).toPath());
                    System.out.println("Screenshot saved for failed test: " + screenshotPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            driver.quit();
        }
    }
}
