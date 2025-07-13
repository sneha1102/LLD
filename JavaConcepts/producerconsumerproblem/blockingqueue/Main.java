package producerconsumerproblem.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(5);
        Thread producer1 = new Thread(new Producer(sharedQueue), "Producer-1");
        Thread consumer1 = new Thread(new Consumer(sharedQueue), "Consumer-1");
        Thread producer2 = new Thread(new Producer(sharedQueue), "Producer-2");
        Thread consumer2 = new Thread(new Consumer(sharedQueue), "Consumer-2");

        producer1.start();
        consumer1.start();
        producer2.start();
        consumer2.start();
    }
}