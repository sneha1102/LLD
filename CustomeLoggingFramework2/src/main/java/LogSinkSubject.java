import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogSinkSubject {
    private final Map<LogLevel, List<LogObserver>> observers = new HashMap<>();

//    public void addObserver(LogLevel level, LogObserver logObserver) {
//        List<LogObserver> logObservers = observers.getOrDefault(level, new ArrayList<>());
//        logObservers.add(logObserver);
//        observers.put(level, logObservers);
//    }

// Add an observer, preventing duplicates for the same LogLevel
    public void addObserver(LogLevel level, LogObserver logObserver) {
        List<LogObserver> logObservers = observers.getOrDefault(level, new ArrayList<>());

        // Check if the observer is already in the list to avoid duplicates
        if (!logObservers.contains(logObserver)) {
            logObservers.add(logObserver);
            observers.put(level, logObservers);  // Update the map with the new observer list
            System.out.println("Added observer: " + logObserver.getClass().getSimpleName() + " for level: " + level.getLevel());
        } else {
            System.out.println("Observer already exists for level: " + level.getLevel());
        }
    }


    public void notifyObservers(LogLevel level, String message) {
        for (Map.Entry<LogLevel, List<LogObserver>> entry: observers.entrySet()) {
           if (entry.getKey().getLevel() == level.getLevel()) {
               entry.getValue().forEach(observers -> observers.log(message));
           }
        }
    }
}
