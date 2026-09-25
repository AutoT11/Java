package FRONT.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import org.openqa.selenium.WebElement;

public class MainDebitCardPage extends BasePage {

    private final By formTitleMainDebitCard = By.xpath("//h1");
    private final By getCodeButton = By.xpath("//button[text()='Получить код']");
    private final By allErrorMessage = By.xpath("//div[contains(@id, '-error')]");



    public MainDebitCardPage(WebDriver driver) {
        super(driver);
    }

    public String getFormTitle() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(formTitleMainDebitCard))
                .getText();

    }


    public void clickGetCode() {

        wait.until(ExpectedConditions.elementToBeClickable(getCodeButton)).click();

    }


    public List<String> waitForValidationMessages(int expectedCount) {
        List<WebElement> messageElements = wait.until(
                ExpectedConditions.numberOfElementsToBe(
                        allErrorMessage,
                        expectedCount
                )
        );

        return messageElements.stream()
                .map(WebElement::getText)
                .toList();

    }


    public boolean areValidationMessagesPresent() {

        return !driver.findElements(allErrorMessage).isEmpty();

    }


}
