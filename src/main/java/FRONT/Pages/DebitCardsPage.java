package FRONT.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DebitCardsPage extends BasePage {

    private final By heroApplyDebitCardLink = By.xpath(
            "//div[contains(@class, 'ButtonBox-hero-block')]" +
                    "//a[@href='/r/online/debit-card/step1/multicard-ready']"
    );

    public DebitCardsPage(WebDriver driver) {
        super(driver);
    }

    public MainDebitCardPage applyDebitCard() {

        String originalWindow = driver.getWindowHandle();

        wait.until(ExpectedConditions.elementToBeClickable(heroApplyDebitCardLink)).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {

            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        return new MainDebitCardPage(driver);

    }



}
