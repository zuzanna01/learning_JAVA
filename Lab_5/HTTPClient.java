package Lab_5;


import java.io.*;
import java.net.Socket;

public class HTTPClient {
    public static void main(String[] args) throws IOException {
        String host = args[0];
        int port  = Integer.parseInt(args[1]);
        String url= args[2];
        String request ="GET "+url+" HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "Connection: close\r\n\r\n";;
        int c;

        Socket socket = new Socket(host, port);

        InputStream in =socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(out, true);

        pw.println(request);
        pw.flush();

        while ((c = in.read()) != -1)
            System.out.print((char) c);
        socket.close();

    }
}
