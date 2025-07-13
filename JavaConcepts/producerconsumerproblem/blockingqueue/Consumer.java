package producerconsumerproblem.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    BlockingQueue<Integer> sharedQueue;

    public Consumer(BlockingQueue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        try{
            while(true){
                Thread.sleep(1000);
                int item = sharedQueue.take(); // Automatically waits if the queue is empty
                System.out.println(Thread.currentThread().getName() + " Consumed: " + item);
            }
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted.");
        }
    }
}
