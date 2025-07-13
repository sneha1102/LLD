package producerconsumerproblem;

public class Consumer implements Runnable{
    SharedQueue sharedQueue;

    public Consumer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        try{
            while(true){
                Thread.sleep(1000);
                sharedQueue.consume();
            }
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted.");
        }
    }
}
