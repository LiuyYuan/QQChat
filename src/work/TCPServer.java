package work;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class TCPServer {
    static int port = 3322;
    private List<PrintWriter> clients;
    private List<String>clientsc = new ArrayList<>();

    public static void main(String[] args) {
        new TCPServer();
    }

    private TCPServer() {
        try {
            clients = new ArrayList<>();
            ServerSocket server = new ServerSocket(port);

            while (true) {
                System.out.println("正在等待连接");
                Socket socket = server.accept();
                System.out.println("已连接");
                PrintWriter ax = new  PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                clients.add(ax);
                Mythread mythread = new Mythread(socket);
                mythread.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class Mythread extends Thread {
        Socket ssocket;
        private BufferedReader br;
        private PrintWriter pw;
        String msg;

        Mythread(Socket s) {
            ssocket = s;
        }

        public void run() {
            try {
                br = new BufferedReader(new InputStreamReader(ssocket.getInputStream()));
                String sc = br.readLine();
                clientsc.add(sc);
                while ((msg=br.readLine()) != null) {
                    System.out.println(msg);
                    if (msg.startsWith("@")){
                        int index = msg.indexOf(":");
                        if (index >= 0){
                            String id = msg.substring(1,index);
                            String message = msg.substring(index+1);
                            sendtosomeone(id,message);
                            continue;
                        }
                    }
                    sendtoall(msg);
                }
            } catch (Exception ex) {
                System.out.println("连接已断开");
            }
        }

        synchronized void sendtosomeone(String id,String message) {
            try {
                    int ki = clientsc.indexOf(id);
                    pw = clients.get(ki);
                    if (pw!=null)pw.println(message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        synchronized void sendtoall(String message) {
            try {
                for (int i =0;i<clients.size();i++ ){
                pw = clients.get(i);
                pw.println(message);}
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}