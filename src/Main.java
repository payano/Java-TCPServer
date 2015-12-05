import TCPServer.TCPServer;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by johan on 2015-12-05.
 */
public class Main {
    public static void main(String[] args) {
        try {
            TCPServer tcpServer = new TCPServer(1234);
            tcpServer.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
