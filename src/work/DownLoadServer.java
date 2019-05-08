package work;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DownLoadServer extends Thread{
    static int port = 44447;

    public void run(){
        ServerSocket serverSocket = null;
        Socket transSocket ;
        int i=0;
        try {
            serverSocket=new ServerSocket(port);
            while (true) {
                System.out.println("文件下载服务器等待连接……");
                transSocket = serverSocket.accept();
                i++;
                System.out.println("第" + i + "个连接");
                new DownloadThread(transSocket).start();
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
class DownloadThread extends Thread{
    private Socket socket ;
    DownloadThread(Socket socket){
        this.socket=socket;
    }
    public void run() {

        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            setout(in, out);
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
    private void setout(InputStream in, OutputStream out) throws IOException{
        byte sh;
        byte[] buf=new byte[1024];
        byte[] nam=new byte[1024];
        FileInputStream fis;
        String name;
        try {
            int i=0;
            while((sh = (byte) in.read())!='\n'){
                nam[i++]= sh;
            }
            name=new String(nam).trim();
            System.out.println("要下载的文件为："+ name);
            File file = new File("src\\clouddisk\\"+name);
            fis = new FileInputStream(file);
            int len;
            while((len=fis.read(buf))!=-1){
                out.write(buf,0,len);
            }
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                in.close();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String []ae){
        new DownLoadServer().run();
    }
}
