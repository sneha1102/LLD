package producerconsumerproblem.multiple;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private int maxSize;

    public SharedQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void produce(int item) throws InterruptedException{
        while (queue.size() == maxSize){
            // Wait until there is space to produce
            wait();
        }
        queue.add(item);
        System.out.println("Produced: " + item + " by thread " + Thread.currentThread().getName());
        // Notify all consumer that an item has been produced
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException{
        while(queue.isEmpty()){
            wait();
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item + " by thread " + Thread.currentThread().getName());
        // Notify all producer that an item has been consumed
        notifyAll();
        return item;
    }
}
