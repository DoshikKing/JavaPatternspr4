package com.company;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;



public class MyExecutor extends Thread implements ExecutorService {

    boolean isShutdown = false;
    int signals;
    //Thread[] thread;
    Future[] futures;

    public MyExecutor(int signals){
        this.signals = signals;
        //this.thread = new Thread[signals];
        this.futures = new FutureTask[signals];
    }

    @Override
    public void shutdown() {
        for (int i = 0; i < this.signals; i++) {
            if(!(this.futures[i] == null || (this.futures[i].isDone() || this.futures[i].isCancelled()))){
                this.futures[i].cancel(false);
                this.isShutdown = true;
            }
        }
    }

    @Override
    public List<Runnable> shutdownNow() {
        for (int i = 0; i < this.signals; i++) {
            if(!(this.futures[i] == null || (this.futures[i].isDone() || this.futures[i].isCancelled()))){
                this.futures[i].cancel(true);
                this.isShutdown = true;
            }
        }
        return null;
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    @Override
    public boolean isTerminated() {
        return this.isShutdown;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        if (this.isShutdown) throw new NullPointerException();

        boolean working = false;
        for (int i = 0; i < this.signals; i++) {
            if(!(this.futures[i] == null || (this.futures[i].isDone() || this.futures[i].isCancelled()))){
                working = true;
                break;
            }
        }

        boolean stop = true;
        if (working)
        {
            sleep(unit.convert(timeout, TimeUnit.MILLISECONDS));
            for (int i = 0; i < this.signals; i++) {
                if(!(this.futures[i] == null || (this.futures[i].isDone() || this.futures[i].isCancelled()))){
                    stop = false;
                    break;
                }
            }
        }

        return stop;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task)
    {
        if (task == null) throw new NullPointerException();

        for (int i = 0; i < this.signals; i++) {
            if(this.futures[i] == null || this.futures[i].isDone() || this.futures[i].isCancelled()){
                RunnableFuture<T> ftask = new FutureTask<T>(task);
                this.futures[i] = ftask;
                execute(ftask);
                return ftask;
            }
        }
        if(true) throw new NullPointerException();
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        if (task == null) throw new NullPointerException();

        for (int i = 0; i < this.signals; i++) {
            if(this.futures[i] == null || this.futures[i].isDone() || this.futures[i].isCancelled()){
                RunnableFuture<T> ftask = new FutureTask<T>(task, result);
                this.futures[i] = ftask;
                execute(ftask);
                return ftask;
            }
        }
        if(true) throw new NullPointerException();
        return null;
    }


    @Override
    public Future<?> submit(Runnable task) {
        if (task == null) throw new NullPointerException();

        for (int i = 0; i < this.signals; i++) {
            if(this.futures[i] == null || this.futures[i].isDone() || this.futures[i].isCancelled()){
                RunnableFuture<Void> ftask = new FutureTask<Void>(task, null);
                this.futures[i] = ftask;
                execute(ftask);
                return ftask;
            }
        }
        if(true) throw new NullPointerException();
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
