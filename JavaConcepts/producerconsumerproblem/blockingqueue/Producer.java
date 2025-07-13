package producerconsumerproblem.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    BlockingQueue<Integer> sharedQueue;
    public Producer(BlockingQueue<Integer> sharedQueue){
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        int item = 0;
        try{
            while(item <= 5){
                Thread.sleep(5000);
                sharedQueue.put(++item); // Automatically waits if the queue is full
                System.out.println(Thread.currentThread().getName() + " Produced: " + item);
            }
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted");
        }

    }
}
