package utils;

public class DelayLoading {

    public static void delayFiveSecond(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
