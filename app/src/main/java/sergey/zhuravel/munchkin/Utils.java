package sergey.zhuravel.munchkin;

import java.util.Random;


public class Utils {
    static Random secureRandom = new Random();


    public static int generateId() {
        return secureRandom.nextInt(100000);
    }
}
