package main.java.models;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Gym {
    private final String id;
    private final String name;
    private final String location;
    private final int maxAccommodation;
    private final Map<String, GymClass> classes = new ConcurrentHashMap<>();

    public Gym(String id, String name, String location, int maxAccommodation) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.maxAccommodation = maxAccommodation;
    }

    public String getId() {
        return id;
    }

    public int getMaxAccommodation() {
        return maxAccommodation;
    }

    public String addClass(String id, String classType, int maxLimit, String startTime, String endTime) {
        GymClass gymClass = new GymClass(id, classType, maxLimit, startTime, endTime);
        classes.put(id, gymClass);
        return id;
    }

    public void removeClass(String classId) {
        classes.remove(classId);
    }

    public GymClass getClassById(String classId) {
        return classes.get(classId);
    }

    public Map<String, GymClass> getClasses() {
        return classes;
    }
}
