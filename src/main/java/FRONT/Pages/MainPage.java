package FRONT.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;


public class MainPage extends BasePage {

    private final By cookieButton = By.xpath("//button[text()='Хорошо']");
    private final By menuButton = By.xpath("//div[contains(@class, 'MenuIcon-header')]");
    private final By menuCards = By.xpath("//a[@data-ym-menu-sidebar-title='Карты']");
    private final By debitCardsMenuItem = By.xpath("//a[@data-ym-menu-product-title='Дебетовые карты']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static final String PAGE_URL = "https://www.vtb.ru/";

    public void open() {
        driver.get(PAGE_URL);
    }

    public void acceptCookies() {

        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));

    }

    public void openMenu() {

        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();

    }

    public void openMenuCards() {

        wait.until(ExpectedConditions.elementToBeClickable(menuCards)).click();

    }

    public void hoverCardsMenu() {

        WebElement cardsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(menuCards));

            new Actions(driver)
                 .moveToElement(cardsMenu)
                 .perform();

    }

    public DebitCardsPage openDebitCardsMenuItem() {

        wait.until(ExpectedConditions.elementToBeClickable(debitCardsMenuItem)).click();

        return new DebitCardsPage(driver);

    }







}
