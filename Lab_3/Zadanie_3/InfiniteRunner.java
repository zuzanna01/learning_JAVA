package Lab_3.Zadanie_3;

public class InfiniteRunner extends OneRunner {
    @Override
    public void run() {
        while (true) {
            System.out.println(name);
        }
    }
}
