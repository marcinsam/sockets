import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * Created by Marcin Bozek on 2016-02-13.
 */
public class CoreJavaServerSocket {

    public final static String HTML = "<html>\n" +
            "<header><title>This is title</title></header>\n" +
            "<body>\n" +
            "Hello world\n" +
            "</body>\n" +
            "</html>";

    public static void main(String[] args) {

        try (
                ServerSocket serverSocket = new ServerSocket(1900);
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            while(true) {
                System.out.println("Client connected");

                String read;
                while((read=in.readLine())!= null) {
                    System.out.println(read);
                    if(read.isEmpty())
                        break;
                }
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                out.write("HTTP/1.1 200 OK\r\n");
                out.write("Date: " + LocalDateTime.now()+"\r\n");
                out.write("Content-type: text/html\r\n");
                out.write("Content-Length: 80\r\n");
                out.write("\r\n");
                out.write(HTML);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
