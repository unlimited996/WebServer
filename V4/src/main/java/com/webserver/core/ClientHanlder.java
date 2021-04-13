package com.webserver.core;//package com.webserver.core;

import com.webserver.http.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
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
