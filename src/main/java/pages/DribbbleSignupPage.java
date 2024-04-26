package pages;

import constants.DribbbleSignupPageLocator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DribbbleSignupPage extends DribbbleSignupPageLocator {

    private WebDriver driver;
    private DribbbleSignupPageLocator locator;
    private By signinlink = By.cssSelector(DribbbleSignupPage.SIGN_IN_LINK);
    private By signupTitle = By.tagName(DribbbleSignupPage.SIGN_UP_TITLE);



    public DribbbleSignupPage(WebDriver driver) {
        this.driver = driver;
        this.locator = new DribbbleSignupPageLocator();
    }

   



    public void clickSignUpWithGoogleButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.SIGN_UP_WITH_GOOGLE_BUTTON)));
        button.click();
    }


    public void clickContinueWithEmailButton() {
        driver.findElement(By.xpath(locator.CONTINUE_WITH_EMAIL_BUTTON)).click();
    }

    public DribbbleSignedInUserHomePage invalidRegistration(String validName, String validPassword, String invalidEmail) {
        fillNameField(validName);
        fillPasswordField(validPassword);
        fillEmailField(invalidEmail);
        return submitSignUp();
    }

    private void fillNameField(String validName) {
        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys(validName);
    }

    private void fillPasswordField(String validPassword) {
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys(validPassword);
    }

    private void fillEmailField(String invalidEmail) {
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.clear();
        emailField.sendKeys(invalidEmail);
    }

    private DribbbleSignedInUserHomePage submitSignUp() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        return new DribbbleSignedInUserHomePage(driver);
    }

    public String getErrorMessage() {
        WebElement errorMessageElement = driver.findElement(By.cssSelector("YOUR_ERROR_MESSAGE_CSS_SELECTOR"));
        return errorMessageElement.getText();
    }


        public DribbbleSigninPage clickSignInLink() {

            WebElement signInLink = driver.findElement(signinlink);
            signInLink.click();

            return new DribbbleSigninPage(driver);
        }
    public boolean isSignUpPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(signupTitle));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }

    }
}


