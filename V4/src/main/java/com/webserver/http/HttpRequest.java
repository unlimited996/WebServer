package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    //请求行相关信息
    private String method;//请求方式
    private String uri;//抽象路径
    private String protocol;//协议版本
    private Map<String,String> headers = new HashMap<>();
    private Socket socket;
    //实例化HttpRequest的过程就是解析的过程，通过给定的Socket读取客户端发送的请求
    public HttpRequest(Socket socket) {
        this.socket=socket;
        try {
            parseRequestLine();
            parseHeaders();
            parseContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String readLine() throws IOException {
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
        return builder.toString().trim();//trim的目的是去除最后的回车符
    }
    private void parseRequestLine() throws IOException {
        //读取客户端发送过来的一行字符串内容(以CRLF结尾)
        String line = readLine();
        System.out.println(line);
        String[] data = line.split("\\s");//按照空格拆分
        method = data[0];
        uri = data[1];
        protocol = data[2];
        //测试路径如果是:http://localhost:8088/myweb/index.html
        System.out.println("method:"+method);//method:GET
        System.out.println("uri:"+uri);//uri:/myweb/index.html
        System.out.println("protocol:"+protocol);// protocol:HTTP/1.1
    }
    //解析消息头
    private void parseHeaders() throws IOException {
        //读取客户端发送过来的一行字符串内容(以CRLF结尾)
        while (true) {
            String line = readLine();
            if (line.isEmpty()) {
                break;
            }
            System.out.println(line);
            //拆分消息头后存入Map
            String[] arr = line.split(":\\s");
            headers.put(arr[0],arr[1]);
        }
        System.out.println(headers);
    }
    private void parseContent(){}

    public String getMethod() {
        return method;
    }
    public String getUri() {
        return uri;
    }
    public String getProtocol() {
        return protocol;
    }
    public String getHeaders(String name) {
        return headers.get(name);
    }
}
