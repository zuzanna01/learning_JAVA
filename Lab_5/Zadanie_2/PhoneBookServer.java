package Lab_5.Zadanie_2;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Klasa PhoneBookServer implementuje serwer książki telefonicznej.
 * Używa on ConcurrentHashMap do przechowywania numerów telefonów
 * i nazw, a dane są zapisywane do pliku o nazwie "phone_book.txt".
 * Serwer nasłuchuje na porcie 12129 i obsługuje żądania klientów,
 * czytając dane wejściowe od klienta, wykonując żądaną operację i
 * zwracając wynik klientowi.
 */
public class PhoneBookServer {
// rozszerzenie_1:numery i telefony są zapisywane do pliku phone_book.txt
// jeśli jakiś numer został zmieniony jest on aktualizowany w pliku przy zamknięciu klienta
// rozszerzenie_2:wersja rozmyta, serwer zwraca wszystkie nazwy rozpoczynające się na ciąg znaków podany w zapytaniu
// rozszerzenie_3: umieszczenie klienta w wątku

    ServerSocket serverSocket;
    ConcurrentHashMap<String, String> phoneBook;

    public PhoneBookServer() {
        phoneBook = loadFile();
        try {
            serverSocket = new ServerSocket(12129);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Kończy działanie serwera poprzez zamknięcia gniazda swerwera.
     *
     * @throws IOException
     */
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Rozpoczyna działanie serwera i nasłuchuje połączeń klientów.
     * Dla każdego połączenia klienta tworzy nowy wątek do obsługi żądań klienta.
     */
    public void start() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                new Thread(() -> {
                    try {
                        handleClient(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * Uruchamia serwer PhoneBookServer
     *
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost.getHostAddress() = " + localHost.getHostAddress());
        System.out.println("localHost.getHostName() = " + localHost.getHostName());
        PhoneBookServer server = new PhoneBookServer();
        server.start();
        server.stop();

    }

    /**
     * Metoda handleClient obsługuje żądania klientów,
     * możliwe operację to:
     * "put" dodanie nowego numeru telefonu,
     * "get" odnalezienie numeru telefonu i zwrócenie go
     * "close" zamknięcie połaczenia i zapisanie zmian w pliku
     *
     * @param socket  The socket representing the client connection.
     *
     * @throws IOException
     */
    public void handleClient(Socket socket) throws IOException {
        String fileName = "phone_book.txt";
        File file;
        file = new File(fileName);

        try (InputStream is = socket.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is));
             OutputStream os = socket.getOutputStream();
             PrintWriter pw = new PrintWriter(os, true)) {
            String request;
            boolean end = false;
            while (!end) {
                try {
                    request = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String[] split_request = request.split(" ");
                switch (split_request[0]) {
                    case "put":
                        phoneBook.put(split_request[1], split_request[2]);
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
                            bw.write(split_request[1] + " " + split_request[2] + "\n");
                        } catch (IOException e) {
                            System.err.println("Server exception: " + e);
                        }
                        pw.println(split_request[1] + " " + split_request[2]);
                        break;
                    case "get":

                        List<Map.Entry<String, String>> answer = phoneBook.entrySet().stream()
                                .filter(entry -> entry.getKey().startsWith(split_request[1]))
                                .collect(Collectors.toList());

                        if (answer.size() == 0) {
                            pw.println("--- no name: " + split_request[1] + " ---");
                        } else {
                            String answer_str = "";
                            for (Map.Entry<String, String> entry : answer) {
                                answer_str += entry.getKey() + " " + entry.getValue() + " ";
                            }

                            pw.println(answer_str);
                        }
                        break;
                    case "close":
                        end = true;
                        file.delete();
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
                            for (String key : phoneBook.keySet()) {
                                bw.write(key + " " + phoneBook.get(key) + "\n");
                            }
                        } catch (IOException e) {
                            System.err.println("Server exception: " + e);
                        }
                        break;
                    default:
                        pw.println("bad request");
                        break;
                }
            }
        }
    }

    public static ConcurrentHashMap<String, String> loadFile() {
        ConcurrentHashMap<String, String> phoneBook = new ConcurrentHashMap<>();
        String fileName = "phone_book.txt";
        File file;
        file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(fileName + " has been created!");
            } catch (IOException e) {
                System.err.println("Server exception: " + e);
            }
        } else {
            try {
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                for (String line : lines) {
                    String[] split_line = line.split(" ");
                    phoneBook.put(split_line[0], split_line[1]);
                }
            } catch (IOException e) {
                System.err.println("Server exception: " + e);
            }
        }
        return phoneBook;
    }
}