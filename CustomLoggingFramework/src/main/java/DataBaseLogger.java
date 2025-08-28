public class DataBaseLogger implements LogObserver{
    public void log(String message) {
        System.out.println("Logging into database" + message);
    }
}
