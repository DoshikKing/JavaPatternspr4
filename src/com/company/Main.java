package com.company;

import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor(3);
        myExecutor.submit(() -> Hello.Hello("1"));
        myExecutor.submit(() -> Hello.Hello("2"));
        myExecutor.submit(() -> Hello.Hello("3"));
        myExecutor.submit(() -> Hello.Hello("4"));

        myExecutor.shutdown();
        try {
            System.out.println(myExecutor.awaitTermination(1000, TimeUnit.MILLISECONDS));
            sleep(2000);
        } catch (Exception e){};

    }
}
