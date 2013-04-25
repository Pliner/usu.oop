package usu.oop.examples.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Producer implements Runnable{
    private BlockingQueue<Integer> queue;
    private int produced;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        this.produced = 0;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                queue.offer(++produced);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
