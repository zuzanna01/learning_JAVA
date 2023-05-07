package Lab_4.Zadanie_1;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

//książki są czytane przez każdego czytelnika w kolejności jakiej zostały napisane

public class Library {
    static AtomicInteger book_number = new AtomicInteger(1);
    public static int number_of_all_books;
    Reader[] readers;
    Writer[] writers;
    CopyOnWriteArrayList<String> al;

    public Library( int W, int R, int B) {
        al=new CopyOnWriteArrayList<String>();
        number_of_all_books =B;
        readers = new Reader[R];
        writers = new Writer[W];
        startThreads();
    }

    public void startThreads() {
        for (int i = 0; i < writers.length; i++) {
            writers[i] = new Writer(al);
            writers[i].t.start();
        }
        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Reader(al);
            readers[i].t.start();
        }
    }


    public static void main(String[] args) {

        Integer W = Integer.parseInt(args[0]);//writerNumber
        Integer R = Integer.parseInt(args[1]);//readerNumber
        Integer B = Integer.parseInt(args[2]);//finalNumberOfBooks
        new Library(W, R, B);
    }

}
