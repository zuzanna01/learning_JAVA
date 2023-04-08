package Lab_3.Zadanie_4;

//jeden z filozofów (przedostatni) jako pierwszy bierze widelec lewy, a nie prawy
//dzięki temu unikamy zakleszczenia
public class Main {
    public static void main(String[] args) {
        String arg = args[0];
        Integer number = Integer.parseInt(arg);

        Philosopher[] philosophers = new Philosopher[number];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == philosophers.length - 1) {
                // The last philosopher picks up the right fork first
                philosophers[i] = new Philosopher(rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }
            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}

