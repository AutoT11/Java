package untils;

import  java.util.concurrent.ThreadLocalRandom;

//Рандомный "tittle"
public class TestNumberGenerator {

    public static String uniqueTitle() {
        int randomNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return "Кредитка особенная_АТ_" + randomNumber;

    }
}
