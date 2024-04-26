
import messages.AssertionMessages;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.junit.Assert;
import pages.DribbbleHomePage;
import pages.DribbbleSignedInUserHomePage;
import pages.DribbbleSigninPage;
import pages.DribbbleSignupPage;

public class SignupTest extends BaseTest {

    @Before
    public void setUp() {
        // Replace "path_to_your_chromedriver" with the actual path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Additional initialization code can go here
    }



    @Test
    public void navigateToResetPasswordPage() {
        DribbbleSignupPage signupPage = homePage.clickSignUpButton();
        signupPage.clickContinueWithEmailButton();

        DribbbleSigninPage signinPage = signupPage.clickSignInLink(); // Assuming clickSignInLink() returns a DribbbleSigninPage
        signinPage.clickForgotPasswordLink();

        Assert.assertTrue("Reset password form should be visible", driver.findElement(By.id("reset-password-form")).isDisplayed());
    }






//
//    @Test
//    public void navigateToResetPasswordPage() {
//
//        WebDriver driver = new ChromeDriver();
//        DribbbleHomePage homePage = new DribbbleHomePage(driver);
//
//        homePage.clickSignUpButton();
//        DribbbleSignupPage signupPage = new DribbbleSignupPage(driver);
//        signupPage.clickContinueWithEmailButton();
//
//        DribbbleSigninPage signinPage = new DribbbleSigninPage(driver);
//        signinPage.clickForgotPasswordLink();
//
//
//        driver.quit();
//    }

}






