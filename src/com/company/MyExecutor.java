package com.company;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;



public class MyExecutor extends Thread implements ExecutorService {

    int signals;
    Thread[] thread;

    public MyExecutor(int signals){
        this.signals = signals;
        this.thread = new Thread[signals];
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task)
    {
        if (task == null) throw new NullPointerException();
        RunnableFuture<T> ftask = new FutureTask<T>(task);
        execute(ftask);
        return ftask;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }


    @Override
    public Future<?> submit(Runnable task) {
        Future<String> future = new FutureTask<String>(() ->"doing...");
        for (int i = 0; i < this.signals; i++) {
            if(this.thread[i] != null){
                this.thread[i] = new Thread(task);
                if(this.thread[i].getState() != State.TERMINATED)
                {
                    return future;
                }
            }
        }
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
