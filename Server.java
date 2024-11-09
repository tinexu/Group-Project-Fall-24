import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException  {
        ServerSocket serverSocket = new ServerSocket(123);
        Socket socket = serverSocket.accept();

        SocialMediaPlatform smp = new SocialMediaPlatform();

        //ArrayList<String> information =

    }
}
