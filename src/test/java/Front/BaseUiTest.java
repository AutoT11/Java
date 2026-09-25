package Front;

import FRONT.Pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseUiTest {

    protected WebDriver driver;

    protected MainPage mainPage;

    @BeforeEach
    void setUp() {

        driver = new ChromeDriver();
        mainPage = new MainPage(driver);

    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }

    }




}
