package main.java.models;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GymClass {
    private final String id;
    private final String classType;
    private final int maxLimit;
    private final String startTime;
    private final String endTime;
    private final Set<String> bookings = ConcurrentHashMap.newKeySet();

    public GymClass(String id, String classType, int maxLimit, String startTime, String endTime) {
        this.id = id;
        this.classType = classType;
        this.maxLimit = maxLimit;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public boolean isFullyBooked() {
        return bookings.size() >= maxLimit;
    }

    public boolean hasCustomerBooked(String customerId) {
        return bookings.contains(customerId);
    }

    public void addBooking(String customerId) {
        bookings.add(customerId);
    }

    public void removeBooking(String customerId) {
        bookings.remove(customerId);
    }
}