package TCPServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

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
        Date date;
        System.out.println("Starting new TCP-server on port" + this.port);
        while (runMe){
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("GOT A CONNECTION FROM: " + socket.getRemoteSocketAddress().toString());
            date = new Date();
            Long epochtime = date.getTime();
            Writer writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(epochtime.toString()+".txt"),"UTF-8"));


            while(!socket.isClosed()){
                try {
                    clientData = bufferedReader.readLine();
                    if(clientData == null){break;}
                    System.out.println("GOT: " + clientData);
                    writer.write(clientData);
                    writer.write("\n");
                    outputStream.writeBytes("OK\n\r");
                }catch (SocketException e){
                    socket.close();
                }catch (NullPointerException e){
                    writer.close();
                }

            }
            writer.close();
            socket.close();
            System.out.println("GOT A DISCONNECT...");

        }
    }

}
