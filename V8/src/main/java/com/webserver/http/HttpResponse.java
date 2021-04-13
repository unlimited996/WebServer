package com.webserver.http;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 响应对象
 * 当前类的每一个实例表示给客户端发送的一个HTTP响应内容，包含：状态行、响应头、响应正文
 */
public class HttpResponse {
    //将当前响应对象内容以标准的响应格式发送给客户端
    public void flush(String state) throws IOException {
        sendStatusLine(state);
        sendHanders();
        sendContent();
    }
    private Socket socket;
    private File file;
    private Map<String,String> map=new HashMap<>();
    public HttpResponse(Socket socket) {
        this.socket=socket;
    }
    private void sendStatusLine(String state) throws IOException {
        String line = "HTTP/1.1 "+state;
        println(line);
    }
    private void sendHanders() {
        try {
            map.forEach((k,v)->{
                try {
                    println(k+": "+v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            //单独发送CRLF表示响应头发送完毕
            println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendContent(){
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            OutputStream outputStream = socket.getOutputStream();
            int b;
            byte[] data = new byte[1024 * 10];
            while ((b = fileInputStream.read(data)) != -1) {
                outputStream.write(data, 0, b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void println(String line) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(line.getBytes("ISO8859-1"));
        outputStream.write(13);
        outputStream.write(10);
    }
    public void putHandler(String name,String value){
        this.map.put(name,value);
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
}
