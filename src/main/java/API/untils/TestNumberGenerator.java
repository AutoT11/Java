package API.untils;

import  java.util.concurrent.ThreadLocalRandom;

//Рандомный "tittle"
public class TestNumberGenerator {

    // Метод для названий (например, для кредитных карт).
    public static String uniqueTitle() {
        int randomNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return "Кредитка особенная_АТ_" + randomNumber;

    }

    public static String uniqueUserName() {
        int randomNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return "Test user_АТ_" + randomNumber;
    }

    public static String uniqueUserEmail() {
        int randomNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return "testuser_АТ_" + randomNumber + "@gmail.com";
    }


}
