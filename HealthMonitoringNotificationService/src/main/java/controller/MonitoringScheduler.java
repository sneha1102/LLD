package main.java.controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MonitoringScheduler {
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    private final MonitoringController monitoringController;
    public MonitoringScheduler(MonitoringController controller) {
        this.monitoringController = controller;
    }
    public void startMonitoring(){
        Runnable task = monitoringController::startMonitoring;
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS); // run every 10 seconds
    }
    public void stopMonitoring(){
        scheduledExecutorService.shutdown();
    }

}
