package com.company;


import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Hello {
    public static void Hello(String index){
        System.out.println("Hello, World!" + index);
        try {
            sleep(2000);
        } catch (Exception e){};

    }
}
