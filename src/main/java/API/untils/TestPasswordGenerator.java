package API.untils;

import  java.util.concurrent.ThreadLocalRandom;

//Рандомный "password"
public class TestPasswordGenerator {

    public static String uniquePassword() {
        int randomNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return "PkDf34FsUi+!_" + randomNumber;

    }
}
