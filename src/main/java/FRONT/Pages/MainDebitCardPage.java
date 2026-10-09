package FRONT.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

public class MainDebitCardPage extends BasePage {

    private final By formTitleMainDebitCard = By.xpath("//h1");
    private final By getCodeButton = By.xpath("//button[text()='Получить код']");
    private final By allErrorMessage = By.xpath("//div[contains(@id, '-error')]");
    //Поля формы
    private final By fullName = By.xpath("//input[@aria-label='Фамилия, имя и отчество']");
    private final By birthDate = By.xpath("//input[@aria-label='Дата рождения']");
    private final By phoneNumber = By.xpath("//input[@aria-label='Мобильный телефон, на этот номер вам придет код в смс']");


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

    //Покажи ошибки. Какие из них видны? У каких есть текст? Их уже столько, сколько я жду?
    public List<String> waitForValidationMessages(int expectedCount) {

        return wait.until(ExpectedConditions.refreshed(currentDriver -> {
            List<WebElement> visibleMessages = currentDriver.findElements(allErrorMessage)
                    .stream()
                    .filter(WebElement::isDisplayed)
                    .toList();

            List<String> texts = visibleMessages.stream()
                    .map(WebElement::getText)
                    .filter(text -> !text.isBlank())
                    .toList();

            return texts.size() == expectedCount ? texts : null;

        }));

    }

    //Пока что не используется
//    public boolean areValidationMessagesDisplayed() {
//
//        return driver.findElements(allErrorMessage)
//                .stream()
//                .anyMatch(WebElement::isDisplayed);

//    }

    public void enterFullName(String name) {

        WebElement fullNameInput =
                wait.until(ExpectedConditions.elementToBeClickable(fullName));

        fullNameInput.sendKeys(name);
    }

    public void enterBirthDate(String data) {

        WebElement birthDateInput =
                wait.until(ExpectedConditions.elementToBeClickable(birthDate));

        birthDateInput.click();
        birthDateInput.sendKeys(data);
    }

    public void enterPhoneNumber(String number) {

        WebElement phoneNumberInput =
                wait.until(ExpectedConditions.elementToBeClickable(phoneNumber));

        phoneNumberInput.click();
        phoneNumberInput.sendKeys(number);
    }


}
