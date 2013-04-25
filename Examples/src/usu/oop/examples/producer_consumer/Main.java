package usu.oop.examples.producer_consumer;

import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        ExecutorService producerService = Executors.newSingleThreadExecutor();
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        producerService.execute(new Producer(queue));
        ExecutorService consumersService = Executors.newFixedThreadPool(2);
        consumersService.execute(new Consumer(queue, "consumer1"));
        consumersService.execute(new Consumer(queue, "consumer2"));
    }
}
