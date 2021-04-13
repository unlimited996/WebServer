package com.webserver.core;//package com.webserver.core;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

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
    private void readUri(File file,Socket socket)  {
        String state;
        if (file.exists() && file.isFile()) {
            state = "200 OK";

        } else {
            state = "404 Not Found";
            file = new File("./Webapps/myweb/404.html");
        }
        try {
            HttpResponse response = new HttpResponse(socket);
            response.putHandler("Content-Type",cut(file));
            response.putHandler("Content-Length",file.length()+"");
            //1发送状态行
            response.setFile(file);
            //发送响应头
            response.flush(state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String cut(File file){
        String strings=file.getName().substring(file.getName().lastIndexOf(".")+1);
        HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("html", "text/html");
            hashMap.put("css", "text/css");
            hashMap.put("js", "application/javascript");
            hashMap.put("png", "image/png");
            hashMap.put("gif", "image/gif");
            hashMap.put("jpg", "image/jpeg");
            return hashMap.get(strings);
    }
}
