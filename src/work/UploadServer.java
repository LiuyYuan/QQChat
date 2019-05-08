package work;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadServer extends Thread{
    static int port = 55558;

    public void run(){
        ServerSocket serverSocket = null;
        Socket transSocket ;
        int i=0;
        try {
            serverSocket=new ServerSocket(port);
            while(true){
                System.out.println("文件上传服务器等待连接……");
                transSocket=serverSocket.accept();
                i++;
                System.out.println("第"+i+"个连接");
                new UploadThread(transSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class UploadThread extends Thread{
    private Socket socket ;
    UploadThread(Socket socket){
        this.socket=socket;
    }
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            down(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void down(InputStream in) throws IOException{
        byte sh;
        byte[] buf=new byte[1024];
        byte[] nam=new byte[1024];
        String name;
        OutputStream out = null;
        try {
            int i=0;
            while((sh = (byte) in.read())!='\n'){
                nam[i++]= sh;
            }
            name=new String(nam).trim();
            System.out.println("要下载的文件为："+name);
            File file = new File("src\\clouddisk\\"+name);
            i = 1;
            while (file.exists()){
                file = new File("src\\clouddisk\\"+i+name);
                i++;
            }
            out=new FileOutputStream(file);
            int len;
            while((len=in.read(buf))!=-1){
                out.write(buf,0,len);
            }
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                in.close();
                assert out != null;
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String []args){
        new UploadServer().run();
    }
}