public class DataBaseLogger implements LogObserver{
    public void log(String message) {
        System.out.println("Logging into database" + message);
    }

    // override so duplicate observer is not added for same level
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true; // All DataBaseLogger instances are considered equal
    }

    @Override
    public int hashCode() {
        return DataBaseLogger.class.hashCode(); // Unique hash code for DataBaseLogger class
    }
}
