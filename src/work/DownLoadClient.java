package work;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

class DownLoadClient {
    private Socket socket = null;

    DownLoadClient() {
        try {
            socket = new Socket("localhost", DownLoadServer.port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void download(String name, String path) {
        try {
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            write2OutputStream(path,name, out, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void write2OutputStream(String path, String name, OutputStream out, InputStream in) throws IOException {
        FileOutputStream fis ;
        byte[] buf = new byte[1024];
        String fileName ;
        //ҵ���߼�
        try {
            //1.д���ļ���
            fileName = name;
            out.write(fileName.getBytes(UTF_8));
            out.write('\n');
            //2.д���ļ�����
            fis = new FileOutputStream(path);
            int len;
            while ((len = in.read(buf)) != -1) {
                fis.write(buf, 0, len);
            }
            out.flush();
            //�쳣����
        } catch (IOException e) {
            e.printStackTrace();
            //�ر���Դ
        }
    }
}
