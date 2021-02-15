package com.company;

public class Main {

    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor(3);
        myExecutor.submit(() -> Hello.Hello());
    }
}
