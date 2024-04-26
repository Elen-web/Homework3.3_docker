package pages;


import constants.DribbbleHiringLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DribbbleHiringPage extends BasePage {

    public DribbbleHiringPage(WebDriver driver) {
        super(driver);
    }

    public void clickPostYourJob() {
        driver.findElement(By.cssSelector(DribbbleHiringLocator.POST_YOUR_JOB_BUTTON)).click();
    }


}
