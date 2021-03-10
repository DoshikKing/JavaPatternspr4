package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

class PersonalExecutor extends AbstractExecutorService implements ExecutorService {

    private final int Count;
    private boolean shutdown;
    private final List<Thread> threads;

    public PersonalExecutor(int tasks) {
        this.Count = tasks;
        this.threads = new ArrayList<>();
    }

    @Override
    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        if (threads.size() == Count)
            throw new IllegalArgumentException("Final");

        shutdown = false;
        Thread thread = new Thread(command);
        threads.add(thread);
        thread.start();
    }

    @Override
    public List<Runnable> shutdownNow() {
        List<Runnable> list = threads.stream().map(thread -> (Runnable) thread).collect(Collectors.toList());
        this.shutdown();

        return list;
    }

    @Override
    public boolean isShutdown() {
        return shutdown;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public void shutdown() {
        shutdown = true;
        threads.forEach(Thread::interrupt);
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Future<?> submit(Runnable task) {
        if (task == null) throw new NullPointerException();

        RunnableFuture<Void> futureTask = new FutureTask<>(task, null);
        execute(futureTask);

        return futureTask;
    }
}

