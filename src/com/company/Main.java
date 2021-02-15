package com.company;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor(3);
        myExecutor.submit(() -> Hello.Hello());
        myExecutor.submit(() -> Hello.Hello());
        myExecutor.submit(() -> Hello.Hello());
        myExecutor.submit(() -> Hello.Hello());
        myExecutor.shutdown();
        try {
            System.out.println(myExecutor.awaitTermination(1000, TimeUnit.MILLISECONDS));
        } catch (Exception e){};

    }
}
