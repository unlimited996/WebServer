//package com.webserver.core;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.Socket;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ClientHanlder implements Runnable{
//    private Socket socket;
//    public ClientHanlder(Socket socket) {
//        this.socket = socket;
//    }
//    @Override
//    public void run() {
//
//        try {
//            String line;
//            //读取客户端发送过来的一行字符串内容(以CRLF结尾)
//            Map headers = new HashMap();
//            while (true) {
//                line = readLine();
//                if (line.isEmpty()) {
//                    break;
//                }
//                System.out.println(line);//消息头
//                String[] arr=line.split("\\s");
//                headers.put(arr[0],arr[1]);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    private String readLine() throws IOException {
//        StringBuilder builder = new StringBuilder();
//        InputStream in = socket.getInputStream();
//        int d;
//        char pre='a',cur='a';//pre表示上次读取到的字符,cur表示本次读取到的字符
//        while((d = in.read()) != -1){
//            cur = (char)d;//将读取的每个字节强转为一个字符(英文,数字,符号都是单字节编码)
//            if(pre==13&&cur==10){//是否连读取到了回车+换行
//                break;//停止读取工作
//            }
//            builder.append(cur);//将本次读取到的字符拼接到StringBuilder中
//            pre = cur;//下次读取前将本次读取的字符记录为上次读取的字符
//        }
//        return builder.toString().trim();//trim的目的是去除最后的回车符
//    }
//}
