import main.java.controller.MonitoringController;
import main.java.controller.MonitoringScheduler;
import main.java.model.Notification;
import main.java.repository.NotificationRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        NotificationRepository notificationRepository = new NotificationRepository();
        MonitoringController monitoringController = new MonitoringController(notificationRepository);
        MonitoringScheduler monitoringScheduler = new MonitoringScheduler(monitoringController);

        // Add sample users and metrics
        monitoringController.addUser("1", "Alice", "alice@example.com", "1234567890");
        monitoringController.addUser( "2", "Bob", "bob@example.com", "0987654321");

        monitoringController.addHealthMetric("1", "heart-rate", 120.0); // Exceeds threshold
        monitoringController.addHealthMetric("2", "pressure", 135.0);  // Below threshold
        monitoringController.addHealthMetric("2", "pressure", 160.0);  // Exceeds threshold

        // Start Monitoring
        monitoringScheduler.startMonitoring();

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        monitoringScheduler.stopMonitoring();
        // Print Notifications
        System.out.println("\nNotifications Log:");
        for (Notification notification : monitoringController.getNotifications()) {
            System.out.println(notification.getUserId() + " " + notification.getMessage());
        }
    }
}