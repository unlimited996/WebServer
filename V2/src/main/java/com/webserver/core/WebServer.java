package com.webserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * WebServer是一个网络容器，实现了Tomcat的基础功能
 * 职责1.与客户端(通常是浏览器)基于TCP协议连接并使用HTTP协议进行交互
 * 职责2.管理多个Webapp(网络应用，网站的所有内容)，使客户端可以远程访问每个Webapp中的内容
 * （1）请求行是一行字符串= CR回车【ASC=13】+LF换行【ASC=10】=CRLF
 *     请求方式(SP)抽象路径(SP)协议版本(CRLF)
 *     GET /ww/ww.html HTTP/1.1
 * （2）消息头
 *  (3)消息正文
 *  交互流程（1）解析请求
 *        （2）处理请求
 *        （3）发送响应
 */
public class WebServer {
    private ServerSocket serverSocket;
    public  WebServer() {
        try {
            serverSocket = new ServerSocket(8088);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(){
        try {
            System.out.println("等待F2连接");
            Socket socket = serverSocket.accept();
            System.out.println("星界之门已开启");
            ClientHanlder clientHanlder = new ClientHanlder(socket);
            Thread thread = new Thread(clientHanlder);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.start();
    }
    public class ClientHanlder implements Runnable{
        private Socket socket;
        public ClientHanlder(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                //读取客户端发送过来的一行字符串内容(以CRLF结尾)
                StringBuilder builder = new StringBuilder();
                InputStream in = socket.getInputStream();
                int d;
                char pre='a',cur='a';//pre表示上次读取到的字符,cur表示本次读取到的字符
                while((d = in.read()) != -1){
                    cur = (char)d;//将读取的每个字节强转为一个字符(英文,数字,符号都是单字节编码)
                    if(pre==13&&cur==10){//是否连读取到了回车+换行
                        break;//停止读取工作
                    }
                    builder.append(cur);//将本次读取到的字符拼接到StringBuilder中
                    pre = cur;//下次读取前将本次读取的字符记录为上次读取的字符
                }
                String line = builder.toString().trim();//trim的目的是去除最后的回车符
                System.out.println(line);
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
    }
}
