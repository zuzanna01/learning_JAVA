package Lab_5.Zadanie_2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa PhoneBookClient implementuje klienta książki telefonicznej.
 * Łączy się on z serwerem na "localhost" na porcie 12129 i
 * pozwala użytkownikowi na interakcję z książką telefoniczną
 * poprzez wysyłanie żądań "put" i "get" do serwera.
 * Przy dodawaniu nowego hasło w książce sprawdzana jest poprawność numeru
 * (9 cyfr)
 */

public class PhoneBookClient extends  Thread{
    Socket socket ;
    public PhoneBookClient(Socket socket){
        this.socket=socket;
    }

    public static void main(String[] args)  throws IOException {
        Socket socket = new Socket("localhost", 12129);
        PhoneBookClient client = new PhoneBookClient(socket);
        client.start();
        try {
            client.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    Scanner scanner = new Scanner(System.in);

    /**
     * Metoda run() odpowiada za interakcję z
     * użytkownikiem oraz komunikację z serwerem.
     * Użytkownik ma do wyboru trzy żąania:
     * "put" dodanie nowego numeru telefonu,
     * "get" odnalezienie numeru telefonu
     * "close" zamknięcie połaczenia
     */
    @Override
    public void run() {
        try(OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            boolean close = false;
            while (!close) {
                int option = 0;
                String request;

                while (!(option == 1 || option == 2 || option == 3)) {
                    System.out.print("Choose option: (1) PUT (2) GET (3) close \n");
                    try {
                        option = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                    } catch (Exception e) {
                    }
                }

                String name = "";
                String number = "";

                String pattern = "^\\d{9}$";
                Pattern r = Pattern.compile(pattern);
                Matcher m;

                switch (option) {
                    case 1:
                        System.out.print("name: ");
                        name = scanner.nextLine().trim();
                        boolean correct = false;
                        while (!correct) {
                            System.out.print("number: ");
                            number = scanner.nextLine().trim();
                            m = r.matcher(number);
                            if (m.find()) {
                                correct = true;
                            } else {
                                correct = false;
                            }
                        }
                        request = "put " + name + " " + number;
                        pw.println(request);
                        System.out.println(br.readLine());
                        break;
                    case 2:
                        System.out.print("name:");
                        name = scanner.nextLine();
                        request = "get " + name;
                        pw.println(request);
                        String line=br.readLine();
                        String split_line[]=line.split(" ");
                        int i=1;
                        for(String l:split_line){
                            if(i%2==1){
                                System.out.print(l+" ");
                            }
                            else{
                                System.out.println(l);
                            }
                            i++;
                        }
                        System.out.println(line);
                        break;
                    case 3:
                        pw.println("close client");
                        close = true;
                        break;
                    default:
                        System.out.println("You shouldnt be here");
                }
            }
        }catch(IOException e){
            System.out.println("CLIENT ERROR");
        }

    }
}
