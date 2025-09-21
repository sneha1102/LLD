public class FileLogger implements LogObserver{
    public void log(String message) {
        System.out.println("Logging into file: " + message);
    }

    // override so duplicate observer is not added for same level
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true; // All FileLogger instances are considered equal
    }

    // override so duplicate observer is not added for same level
    @Override
    public int hashCode() {
        return FileLogger.class.hashCode(); // Unique hash code for FileLogger class
    }
}
