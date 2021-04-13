package com.webserver;

public class test implements Runnable{
    private int num=1;
    @Override
    public void run() {
        while (true){
            synchronized (this){
                notifyAll();//唤醒wait线程
                if(num<=100){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    num++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        test test = new test();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.setName("大爷");
        t2.setName("大妈");
        t2.start();
        t1.start();
    }
}
