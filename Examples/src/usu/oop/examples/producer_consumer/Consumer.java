package usu.oop.examples.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Consumer implements Runnable{
    private BlockingQueue<Integer> queue;
    private String name;

    public Consumer(BlockingQueue<Integer> queue, String name) {
        this.queue = queue;
        this.name = name;
    }


    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
                Integer consumed = queue.poll();
                if(consumed != null) {
                    System.out.println(name + " " + consumed);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }
}
