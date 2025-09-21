public class ConsoleLogger implements LogObserver{
    public void log(String message) {
        System.out.println("Logging into Console: " + message);
    }

    // override so duplicate observer is not added for same level
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true; // All ConsoleLogger instances are considered equal
    }

    @Override
    public int hashCode() {
        return ConsoleLogger.class.hashCode(); // Unique hash code for ConsoleLogger class
    }
}
