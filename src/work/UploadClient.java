package work;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class UploadClient{
    private Socket socket = null;

    UploadClient(){
        try{
            socket = new Socket("localhost", UploadServer.port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void upload(String name) {
        try {
            OutputStream out = socket.getOutputStream();
            write2OutputStream(name, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void upload(File file) {
        try {
            OutputStream out = socket.getOutputStream();
            write2OutputStream(file, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void write2OutputStream(String path, OutputStream out) throws IOException {
        FileInputStream fis = null;
        byte[] buf = new byte[1024];
        String fileName;
        //业务逻辑
        try {
            //1.写入文件名
            fileName = path.substring(path.lastIndexOf('\\') + 1).trim();
            System.out.println("您要上传的文件名为：" + fileName);
            out.write(fileName.getBytes(StandardCharsets.UTF_8));
            out.write('\n');
            //2.写入文件内容
            fis = new FileInputStream(path);
            int len;
            while ((len = fis.read(buf)) != -1) {
                out.write(buf, 0,len);
            }
            out.flush();
            //异常处理
        } catch (IOException e) {
            e.printStackTrace();
            //关闭资源
        } finally {
            assert fis != null;
            fis.close();
            out.close();
        }
    }
    private void write2OutputStream(File file, OutputStream out) throws IOException {
        FileInputStream fis = null;
        byte[] buf = new byte[1024];
        String fileName;
        try {
            fileName = file.getName();
            System.out.println("您要上传的文件名为：" + fileName);
            out.write(fileName.getBytes());
            out.write('\n');
            fis = new FileInputStream(file);
            int len;
            while ((len = fis.read(buf)) != -1) {
                out.write(buf, 0,len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            //关闭资源
        } finally {
            assert fis != null;
            fis.close();
            out.close();
        }
    }
}