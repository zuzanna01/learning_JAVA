package Lab_3.Zadanie_3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OneRunner r;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Opcje:");
        System.out.println("1  Każdy wątek wyświetla swoją nazwę tylko raz.");
        System.out.println("2. Każdy wątek wyświetla swoją nazwę w pętli skończonej, np. p, p=10,11,… razy.");
        System.out.println("3. Każdy wątek wyświetla swoją nazwę w pętli nieskończonej.");
        int choise = scanner.nextInt();

        System.out.println("Ile wątków uruchomić ? :");
        int N = scanner.nextInt();

        if (choise == 1) {
            for (int i = 0; i < N; i++) {
                r = new OneRunner();
                Thread t = new Thread(r);
                t.start();
            }
        } else if (choise == 2) {
            System.out.println("Ile razy wątek wyświetla nazwę ? :");
            int p = scanner.nextInt();
            for (int i = 0; i < N; i++) {

                r = new pTimesRunner(p);
                Thread t = new Thread(r);
                t.start();
            }
        } else if (choise == 3) {
            for (int i = 0; i < N; i++) {
                r = new InfiniteRunner();
                Thread t = new Thread(r);
                t.start();
            }

        } else {
            System.out.println("Błedna operacja");
        }


    }
}
