package Lab_4.Zadanie_1;

import java.util.concurrent.CopyOnWriteArrayList;

public class Reader implements Sleepable {

    private final CopyOnWriteArrayList<String> al;

    static int reader_number = 1;
    String reader_name;
    private boolean time_to_quit = false;

    private int book_idx = 0;

    Thread t;

    public Reader(CopyOnWriteArrayList<String> al) {

        this.al = al;
        reader_name = "Reader " + String.valueOf(reader_number);
        t = new Thread(this, reader_name);
        reader_number++;

    }

    private boolean timeToQuit = false;
    String book;

    @Override
    public void run() {
        while (!timeToQuit) {
            try {
                book = al.get(book_idx);
                synchronized (al.get(book_idx)) {
                    read();
                }
                book_idx++;
                stopRunning();
            } catch (ArrayIndexOutOfBoundsException e) {
                sleep(2000);
            }

        }

    }

    public void read() {

        System.out.println(reader_name + " starts reading " + book);
        sleepRandom(1000, 3000);
        System.out.println(reader_name + " finished reading " + book);

    }

    public void stopRunning() {
        if (book_idx >= Library.number_of_all_books)
            timeToQuit = true;
    }

}
