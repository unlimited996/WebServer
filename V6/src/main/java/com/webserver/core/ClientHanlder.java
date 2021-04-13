package com.webserver.core;//package com.webserver.core;

import com.webserver.http.HttpRequest;

import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientHanlder implements Runnable{
    private Socket socket;
    public ClientHanlder(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            HttpRequest httpRequest = new HttpRequest(socket);
            String path=httpRequest.getUri();
            File file = new File("./Webapps/" +path);
            readUri(file,socket);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void readUri(File file,Socket socket){
        try {
            String state;
            if(file.exists()&&file.isFile()) {
                state = "200 OK";
            }else {
                state="404 Not Found";
                file=new File("./Webapps/myweb/404.html");
                System.out.println("mmm");
            }
                OutputStream outputStream = socket.getOutputStream();
                //1发送状态行
                String line = "HTTP/1.1 "+state;
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);//发送一个回车符
                outputStream.write(10);//发送一个换行符
                //发送响应头
                line = "Content-Type: text/html";
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);
                line = "" + line.length();
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);
                //单独发送CRLF表示响应头发送完毕
                outputStream.write(13);
                outputStream.write(10);
                //发送响应正文
                FileInputStream fileInputStream = new FileInputStream(file);
                int b;
                byte[] data = new byte[1024 * 10];
                while ((b = fileInputStream.read(data)) != -1) {
                    outputStream.write(data, 0, b);
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
