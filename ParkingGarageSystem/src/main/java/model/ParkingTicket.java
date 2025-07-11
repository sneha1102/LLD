package main.java.model;

import java.time.LocalDateTime;

public class ParkingTicket {

    private int userId;
    private String ticketId;
    private LocalDateTime startTime;


    public ParkingTicket(int userId, String ticketId, LocalDateTime startTime) {
        this.userId = userId;
        this.ticketId = ticketId;
        this.startTime = LocalDateTime.now();
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


}
