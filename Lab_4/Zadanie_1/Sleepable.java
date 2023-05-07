package Lab_4.Zadanie_1;

import java.util.concurrent.ThreadLocalRandom;

public interface Sleepable extends Runnable{

        default void sleepRandom(long t1,long t2) {
            sleep(ThreadLocalRandom.current().nextLong(t1, t2));
        }

        default void sleep(long t) {
            try {
                Thread.sleep(t);
            } catch (InterruptedException ignore) {
            }
        }

}
