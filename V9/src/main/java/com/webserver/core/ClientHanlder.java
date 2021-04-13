package com.webserver.core;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;
import com.webserver.http.httpContext;
import java.io.*;
import java.net.Socket;

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

            response.putHandler("Content-Type", httpContext.cut(file));
            response.putHandler("Content-Length",file.length()+"");
            //1发送状态行
            response.setFile(file);
            //发送响应头
            response.flush(state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
