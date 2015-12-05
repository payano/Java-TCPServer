package TCPServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by johan on 2015-12-05.
 */
public class TCPServer {
    private int port;
    private boolean runMe;
    ServerSocket serverSocket;
    public TCPServer(int port) throws IOException {
        this.port = port;
        this.runMe = true;
        serverSocket = new ServerSocket(port);
    }
    public void startServer() throws IOException {
        String clientData;
        System.out.println("Starting new TCP-server on port" + this.port);
        while (runMe){
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("GOT A CONNECTION FROM: " + socket.getRemoteSocketAddress().toString());
            while(!socket.isClosed()){
                try {
                    clientData = bufferedReader.readLine();
                    System.out.println("GOT: " + clientData);
                    outputStream.writeBytes("OK\n\r");
                }catch (SocketException e){
                    socket.close();
                }
            }
            socket.close();
            System.out.println("GOT A DISCONNECT...");

        }
    }

}
