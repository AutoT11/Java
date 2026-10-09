package Playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

public class PlaywrightBasicsTest {


    @Test
    void opensPage() {

        try (Playwright playwright = Playwright.create()) {

           try (Browser browser = playwright.chromium().launch()) {


           }

        }

    }




}
