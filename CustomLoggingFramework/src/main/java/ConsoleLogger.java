public class ConsoleLogger implements LogObserver{
    public void log(String message) {
        System.out.println("Loggint into Console: " + message);
    }
}
