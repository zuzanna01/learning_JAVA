package Lab_3.Zadanie_3;

public class OneRunner  implements Runnable {
    String name;
    public static Integer number = 0;

    public OneRunner() {
        number++;
        this.name = "Wątek " + number.toString();
    }

    @Override
    public void run() {
            System.out.println(name);
    }


}
