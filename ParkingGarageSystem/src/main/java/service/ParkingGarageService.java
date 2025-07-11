package main.java.service;

import main.java.model.ParkingTicket;
import main.java.repository.ParkingGarageRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingGarageService {

    private final ReentrantLock lock = new ReentrantLock();

    private ParkingGarageRepository parkingGarageRepository;

    public ParkingGarageService(ParkingGarageRepository parkingGarageRepository) {
        this.parkingGarageRepository = parkingGarageRepository;
    }

    public ParkingTicket parkCar(int userId){
        lock.lock();
        if(parkingGarageRepository.getAvailableSlots().get() > 0){
            String ticketId = UUID.randomUUID().toString();
            ParkingTicket ticket = new ParkingTicket(userId, ticketId, LocalDateTime.now());
            parkingGarageRepository.saveTicket(ticket);
            parkingGarageRepository.getAvailableSlots().decrementAndGet();
            System.out.println("User parked " + userId + " successfully " + ticketId);
            lock.unlock();
            return ticket;
        }else{
            System.out.println("No available parking spots for User " + userId);
            lock.unlock();
            return null;
        }

    }

    public double unparkCar(String ticketId){
        lock.lock();
        ParkingTicket ticket = parkingGarageRepository.deleteTicket(ticketId);
        if(ticket != null){
            parkingGarageRepository.getAvailableSlots().incrementAndGet();
            LocalDateTime endTime = LocalDateTime.now();
            Duration duration = Duration.between(ticket.getStartTime(), endTime);
            double paymentDue = calculatePayment(duration);
            System.out.println("User " + ticket.getUserId() + " left garage. Payment due: $" + paymentDue);
            lock.unlock();
            return paymentDue;
        }else{
            System.out.println("Invalid ticket ID: " + ticketId);
            lock.unlock();
            return -1;
        }
    }

    private double calculatePayment(Duration duration){
        long mins = duration.toSeconds();
        return mins * 0.5;
    }

    public int getAvailableParkingSpots(){
        return parkingGarageRepository.getAvailableSlots().get();
    }

}
