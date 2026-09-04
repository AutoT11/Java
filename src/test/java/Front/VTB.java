package Front; //+ тест ГИТА вынести все элементы xpath в отдельный класс папку paje (для каждой страницы свов класс) и создать в папку мейн+тесты отдельно, классы со старницами отдельно

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VTB {

    @Test
    @DisplayName("Проверка оформления кредита")
    void openBR() {

        WebDriver driver = new ChromeDriver(); //Нужно ли в каждом @TEST ???

        driver.get("https://www.vtb.ru/");
        String title = driver.getTitle();
        assertEquals("Банк ВТБ (ПАО) ⚡— дебетовые и кредитные карты, ипотека, кредиты, вклады для физических и юридических лиц по всей России", title);

        // Нажимает на КУКИ банер и проверяет закрытие
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
           WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(

                    By.xpath("//button[@class='common__Button-foundation-kit__sc-aiok6k-0 neeKt common__StyledButton-cookies__sc-1umui0r-1 dCZCxt']"))
          );
       assertEquals("Хорошо", cookieButton.getText());
       cookieButton.click();
       wait.until(ExpectedConditions.invisibilityOf(cookieButton));


        // Нажимает на "Меню", выбирает "Кредиты" и проверяет урл
        WebElement menuButton =
                driver.findElement(By.xpath("//div[@class='common__MenuIcon-header__sc-ovcagu-2 kGEUEf']"));
        menuButton.click();

        WebElement menu =
                driver.findElement(By.xpath("//a[@data-ym-menu-sidebar-title='Кредиты']"));
        menu.click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.vtb.ru/personal/kredit/", currentUrl);



        // Нажимает на "Под залог"
        WebElement zalog = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-ym-tab-title='Под залог']")
                )
        );

        wait.until(ExpectedConditions.elementToBeClickable(zalog));

        new Actions(driver)
                .moveToElement(zalog)
                .pause(Duration.ofMillis(300))
                .click()
                .perform();

        //После нажатия "Под залог" появился именно нужный контент
        WebElement contblock = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[@data-ym-typography-text='Кредит под залог автомобиля']")
                )
        );
        assertEquals(
                "Кредит под залог автомобиля",
                contblock.getText()
        );


        // Нажимает на "Подробнее"
        WebElement Podrobnee = wait.until(ExpectedConditions.elementToBeClickable(

                By.xpath("//a[@href='/personal/kredit/pod-zalog-avto/' and @data-ym-button='true']"))
        );

        new Actions(driver)
                .moveToElement(Podrobnee)
                .pause(Duration.ofMillis(300))
                .click()
                .perform();

        currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.vtb.ru/personal/kredit/pod-zalog-avto/", currentUrl);


        // Нажимает на "Оформить заявку"
        WebElement zajavka = wait.until(ExpectedConditions.elementToBeClickable(

                By.xpath("//a[@data-ym-hero-banner-product-button-title='Оформить заявку']")
        ));
        zajavka.click();

       wait.until(ExpectedConditions.urlToBe("https://xn--d1abkf4ap.xn--90ab2c.xn--p1ai/"));


        // Нажимает на КУКИ банер и проверяет закрытие
        WebElement cookieButton1 = wait.until(ExpectedConditions.elementToBeClickable(

                By.xpath("//button[text()='Хорошо']"))
        );
        assertEquals("Хорошо", cookieButton1.getText());
        cookieButton1.click();
        wait.until(ExpectedConditions.invisibilityOf(cookieButton1));


        // В первом шаге формы "Кредит наличными" вписываем "Сумму кредита"
        WebElement firstshag = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@aria-label='Сумма кредита']")
        ));

        firstshag.click();
        firstshag.sendKeys(Keys.CONTROL + "a");
        firstshag.sendKeys("6000666");

        assertEquals("6 000 666 ₽ ", firstshag.getDomProperty("value"));


        // Выбираем срок кредита "3 года" и проверяем перерасчёт платежа
        WebElement payment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='Платёж']/preceding-sibling::div//p")
        ));

        String paymentBefore = payment.getText();

        WebElement threegoda = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='3 года']]")
        ));
        threegoda.click();

        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.textToBe(
                                By.xpath("//p[text()='Платёж']/preceding-sibling::div//p"),
                                paymentBefore
                        )
                )
        );

        String paymentAfter = driver.findElement(
                By.xpath("//p[text()='Платёж']/preceding-sibling::div//p")
        ).getText();

        System.out.println("Сумма ДО: " + paymentBefore);
        System.out.println("Сумма ПОСЛЕ: " + paymentAfter);

        assertNotEquals(paymentBefore, paymentAfter);


        // Нажимаем свитчер "Я получаю зарплату на карту ВТБ" и проверяем перерасчёт платежа+ставки
        WebElement paymentt = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='Платёж']/preceding-sibling::div//p")
        ));

        WebElement stavka = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='Ставка']/preceding-sibling::div//p")
        ));

        String paymenttBefore = paymentt.getText();
        String stavkaBefore = stavka.getText();

        WebElement zpVTB = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='SalaryClient']")
        ));
        zpVTB.click();

        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.textToBe(
                                By.xpath("//p[text()='Платёж']/preceding-sibling::div//p"),
                                paymenttBefore
                        )
                )
        );
        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.textToBe(
                                By.xpath("//p[text()='Ставка']/preceding-sibling::div//p"),
                                stavkaBefore
                        )
                )
        );

        String paymenttAfter = driver.findElement(
                By.xpath("//p[text()='Платёж']/preceding-sibling::div//p")
        ).getText();
        String stavkaAfter = driver.findElement(
                By.xpath("//p[text()='Ставка']/preceding-sibling::div//p")
        ).getText();

//        System.out.println("Сумма ДО: " + paymenttBefore);
//        System.out.println("Сумма ПОСЛЕ: " + paymenttAfter);
//        System.out.println("Ставка ДО: " + stavkaBefore);
//        System.out.println("Ставка ПОСЛЕ: " + stavkaAfter);

        assertNotEquals(paymenttBefore, paymenttAfter);


        // Нажимаем "Заполнить заявку"
        WebElement ZapolnitZajavky = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Заполнить заявку']")
        ));
        ZapolnitZajavky.click();


        // Заполняем форму шага 1.1
        // Вписываем номер
        WebElement Nomer = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@data-test-id='phone_input']")
        ));

        Nomer.click();
        Nomer.sendKeys("9999999999");
        assertEquals("+7 999 999-99-99", Nomer.getDomProperty("value"));

        //Заполняем "Дату рождения"
        WebElement data = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@data-test-id='b-day_input']")
        ));

        data.click();
        data.sendKeys("01012000");
        assertEquals("01.01.2000", data.getDomProperty("value"));

        //Заполняем "Электронная почта"
        WebElement pochta = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@data-test-id='email_input']")
        ));

        pochta.click();
        pochta.sendKeys("rogt@mail.ru");
        assertEquals("rogt@mail.ru", pochta.getDomProperty("value"));


        //Нажимаем "Продожить" и проверяем обязательность чекбокса и уведомление под ним
        By prodoljitLocator = By.xpath("//button[text()='Продолжить']");
        WebElement prodoljit = wait.until(ExpectedConditions.elementToBeClickable(prodoljitLocator));

        prodoljit.click();
        By errorMessage = By.xpath("//div[text()='Без согласия мы не можем рассмотреть заявку на кредит']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

        WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@aria-label='Соглашаюсь с условиями обработки  персональных данных']")
        ));

        checkBox.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(errorMessage));

        prodoljit.click();

        //driver.quit();

    }

}
