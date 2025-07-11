package main.java.models;

public class Booking {
    private final String id;
    private final String gymId;
    private final String classId;
    private final String customerId;

    public Booking(String id, String gymId, String classId, String customerId) {
        this.id = id;
        this.gymId = gymId;
        this.classId = classId;
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getClassId() {
        return classId;
    }

    public String getGymId() {
        return gymId;
    }

    public String getId() {
        return id;
    }
}