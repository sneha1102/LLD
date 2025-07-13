package producerconsumerproblem;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private int maxSize;

    public SharedQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized int produce(int item) throws InterruptedException{
        while (queue.size() == maxSize){
            // Wait until there is space to produce
            wait();
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        // Notify consumer that an item has been produced
        notify();
        return item;
    }

    public synchronized void consume() throws InterruptedException{
        while(queue.isEmpty()){
            wait();
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item);
        // Notify producer that an item has been consumed
        notify();
    }
}
