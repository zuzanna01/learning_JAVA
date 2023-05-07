package Lab_4.Zadanie_1;

import java.util.concurrent.CopyOnWriteArrayList;

public class Writer implements Sleepable {

    private final CopyOnWriteArrayList<String> al;
    static int writer_number = 1;
    String writer_name;
    String book_name;

    Thread t;

    public Writer(CopyOnWriteArrayList<String> al) {
        this.al = al;
        writer_name = "Writer " + String.valueOf(writer_number);
        t = new Thread(this, writer_name);
        writer_number++;
    }

    private boolean timeToQuit = false;

    @Override
    public void run() {

        while (!timeToQuit) {
            write();
            stopRunning();
        }

    }

    public void write() {

        book_name = "Book " + String.valueOf(Library.book_number.getAndIncrement());
        System.out.println(writer_name + " starts writing " + book_name + ".");
        sleepRandom(4000, 6000);
        System.out.println(writer_name + " finished writing " + book_name + ".");
        al.add(book_name);

    }

    public void stopRunning() {

        if (Library.book_number.get() > Library.number_of_all_books)
            timeToQuit = true;
    }

}