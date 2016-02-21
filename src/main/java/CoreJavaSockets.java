import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Marcin Bozek on 2016-02-13.
 */
public class CoreJavaSockets {

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("google.com"), 80)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("GET / HTTP/1.1");
            out.println("Host: google.com");
            out.println("");
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fromServer;
            while((fromServer=in.readLine())!=null) {
                System.out.printf(fromServer);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
