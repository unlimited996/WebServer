package com.webserver.http;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * 此类包含所有与HTTP协议相关的内容
 */
public class httpContext {
    private static HashMap<String,String> hashMap =new HashMap<>();
    static {
        loadhashMap();
    }
    //将web.xml数据格式放入hashMap和静态代码块
    public static void loadhashMap() {
        try {
            //获取sax解析器对象
            SAXReader reader = new SAXReader();
            //获取文件的Document对象
            Document read = reader.read("web.xml");
            //获取根元素
            Element root = read.getRootElement();
            //获取根元素下面的List元素并存入集合
            List<Element> list = root.elements("mime-mapping");
            //遍历List集合元素放入hashMap
            for (Element e:list){
                String key=e.elementText("extension");
                String value=e.elementText("mime-type");
                hashMap.put(key,value);
            }
            System.out.println(hashMap.size());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static String cut(File file){
        //获取文件后缀名
        String strings=file.getName().substring(file.getName().lastIndexOf(".")+1);
        return hashMap.get(strings);
    }
}
