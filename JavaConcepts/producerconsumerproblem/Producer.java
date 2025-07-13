package producerconsumerproblem;

public class Producer implements Runnable{
    SharedQueue sharedQueue;
    public Producer(SharedQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        int item = 0;
        try{
            while(item <= 5){
                Thread.sleep(5000);
                sharedQueue.produce(++item);
            }
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted");
        }

    }
}
