package main.java.repository;

import main.java.model.ParkingTicket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingGarageRepository {

    private final ConcurrentHashMap<String, ParkingTicket> activeTickets;
    private final AtomicInteger availableSlots;

    public ParkingGarageRepository( int totalSpots) {
        this.activeTickets = new ConcurrentHashMap<>();
        this.availableSlots = new AtomicInteger(totalSpots);
    }
    public ConcurrentHashMap<String, ParkingTicket> getActiveUsers() {
        return activeTickets;
    }

    public AtomicInteger getAvailableSlots() {
        return availableSlots;
    }

    public void saveTicket(ParkingTicket ticket){
        activeTickets.put(ticket.getTicketId(), ticket);
    }

    public ParkingTicket deleteTicket(String ticketId){
        return activeTickets.remove(ticketId);
    }

    public boolean isTicketExists(int ticketId){
        return activeTickets.containsKey(ticketId);
    }

}
