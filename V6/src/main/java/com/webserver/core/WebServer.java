package com.webserver.core;

import com.sun.xml.internal.ws.api.message.Header;
import com.webserver.http.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
}
