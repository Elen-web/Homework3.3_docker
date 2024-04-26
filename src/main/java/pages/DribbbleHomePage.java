package pages;

import constants.DribbbleHomePageLocator;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DribbbleHomePage extends BasePage {
    private By searchField = By.cssSelector(DribbbleHomePageLocator.SEARCH_FIELD);
    private By getStartedButton = By.cssSelector(DribbbleHomePageLocator.GET_STARTED_BUTTON);
    private By signUpButton = By.cssSelector(DribbbleHomePageLocator.SIGN_UP_BUTTON);
    private By logInButton = By.cssSelector(DribbbleHomePageLocator.LOG_IN_BUTTON);

    public DribbbleHomePage(WebDriver driver) {
        super(driver);
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dribbble.com/");  // Ensure this is the correct URL
        DribbbleHomePage homePage = new DribbbleHomePage(driver);
    }


    public void search(String query) {
        driver.findElement(searchField).sendKeys(query);
        driver.findElement(searchField).submit();
    }

    public DribbbleSignupPage clickSignUpButton() {
        driver.findElement(signUpButton).click();
        return new DribbbleSignupPage(driver);
    }


    public DribbbleSigninPage clickLoginButton() {
        driver.findElement(logInButton).click();
        return new DribbbleSigninPage(driver);
    }

    public DribbbleSignupPage clickGetStartedButton() {
            driver.findElement(getStartedButton).click();
            return new DribbbleSignupPage(driver);
        }


    public DribbbleHiringPage clickFindDesigners() {
        driver.findElement(By.cssSelector(DribbbleHomePageLocator.FIND_DESIGNERS_LINK)).click();
        return new DribbbleHiringPage(driver);
    }

    }

