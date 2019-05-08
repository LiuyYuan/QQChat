package work;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Date;

class TCPClient extends Thread{
    private static Socket socket;
    private static PrintWriter printWriter;
    private String aa;

    TCPClient(JTextArea showArea, String scnumber) {
        aa = scnumber;
        try {
            System.out.println("��������");
            int port = TCPServer.port;
            socket = new Socket("localhost", port);
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            System.out.println("�����ѽ���");
        } catch (Exception e) {
            System.out.println("�Բ�������ʧ��\n");
            e.printStackTrace();
        }
    }
        public void run() {
            try {
                String msg ;
                printWriter.println(aa);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while ((msg = bufferedReader.readLine())!=null) {
                    ClientFrame.showArea.append(msg  + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    void send(String str) {
        printWriter.println(str);
    }
}


