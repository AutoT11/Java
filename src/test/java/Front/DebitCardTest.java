package Front;

import FRONT.Pages.DebitCardsPage;
import FRONT.Pages.MainDebitCardPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DebitCardTest extends BaseUiTest {

    @Test
    @DisplayName("Переход к форме оформления дебетовой карты и проверка загрузки формы")
    void openDebitCardApplicationForm() {

        MainDebitCardPage mainDebitCardPage = openMainDebitCardPage();

        assertEquals(
                "Карта для жизни",
                mainDebitCardPage.getFormTitle(),
                "Открылась некорректная форма оформления карты"
        );



    }

    @Test
    @DisplayName("Проверка корректности валидации обязательных полей формы дебетовой карты")
    void validationFormDebitCardPage() {
        MainDebitCardPage mainDebitCardPage = openMainDebitCardPage();

        List<String> expectedValidationMessages = List.of(
                "Укажите фамилию, имя и отчество",
                "Укажите дату рождения",
                "Введите номер телефона"
                );

        assertFalse(
                mainDebitCardPage.areValidationMessagesPresent(),
                "Сообщения ошибки валидации отображаются до отправки формы"
        );

        mainDebitCardPage.clickGetCode();

        assertEquals(
                expectedValidationMessages,
                mainDebitCardPage.waitForValidationMessages(
                        expectedValidationMessages.size()
                ),
                "Отображается некорректный набор сообщений валидации"

        );







    }


    //Дойти до формы оформления заявки на главную дебитовую карту
    private MainDebitCardPage openMainDebitCardPage() {

        mainPage.open();
        mainPage.acceptCookies();
        mainPage.openMenu();
        mainPage.hoverCardsMenu();
        DebitCardsPage debitCardsPage = mainPage.openDebitCardsMenuItem();
        return debitCardsPage.applyDebitCard();


    }

}
