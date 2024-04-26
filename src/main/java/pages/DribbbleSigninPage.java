package pages;

import constants.DribbblePasswordResetPageLocator;
import constants.DribbbleSigninPageLocator;
import constants.DribbbleSignupPageLocator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DribbbleSigninPage {
    private WebDriver driver;
    private By usernameField = By.id(DribbbleSigninPageLocator.USERNAME_FIELD);
    private By passwordField = By.name(DribbbleSigninPageLocator.PASSWORD_FIELD);
    private By signInBtn = By.cssSelector(DribbbleSigninPageLocator.SIGN_IN_BUTTON);
    private By signUpLink= By.cssSelector(DribbbleSigninPageLocator.SIGN_UP_LINK);
    private By forgotLink = By.xpath(DribbblePasswordResetPageLocator.FORGOT_lINK);



    public DribbbleSigninPage(WebDriver driver) {
        this.driver = driver;

    }



    public void fillUsernameField(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignInBtn() {
        driver.findElement(signInBtn).click();
    }




    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public DribbbleSignedInUserHomePage login(String username, String password) {
        fillUsernameField(username);
        fillPasswordField(password);
        clickSignInBtn();
        return new DribbbleSignedInUserHomePage(driver);
    }



    public DribbbleSignupPage clickSignUpLink() {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        WebElement signUpLinkElement = wait.until(ExpectedConditions.elementToBeClickable(signUpLink));
        signUpLinkElement.click();
        return new DribbbleSignupPage(driver);
    }

    public boolean isLoginPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }

    }


    public void clickForgotPasswordLink() {
        driver.findElement(forgotLink).click();

    }}