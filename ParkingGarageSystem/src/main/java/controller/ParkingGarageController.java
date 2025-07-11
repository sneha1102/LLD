package main.java.controller;

import main.java.model.ParkingTicket;
import main.java.model.User;
import main.java.repository.ParkingGarageRepository;
import main.java.service.ParkingGarageService;

import java.util.concurrent.atomic.AtomicInteger;

public class ParkingGarageController{
    public static void main(String[] args) {
        ParkingGarageRepository parkingGarageRepository = new ParkingGarageRepository(1);
        ParkingGarageService parkingGarageService = new ParkingGarageService(parkingGarageRepository);
        AtomicInteger userId = new AtomicInteger(0);

        Thread thread1 = new Thread(() -> {
            User user1 = new User(userId.incrementAndGet());
            ParkingTicket ticket = parkingGarageService.parkCar(user1.getUserId());
            if(ticket != null){
                try {
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                parkingGarageService.unparkCar(ticket.getTicketId());
            }
        });

        Thread thread2 = new Thread(() -> {
            User user2 = new User(userId.incrementAndGet());
            ParkingTicket ticket = parkingGarageService.parkCar(user2.getUserId());
            if(ticket != null){
                try {
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                parkingGarageService.unparkCar(ticket.getTicketId());
            }
        });

        Thread thread3 = new Thread(() -> {
            User user2 = new User(userId.incrementAndGet());
            ParkingTicket ticket = parkingGarageService.parkCar(user2.getUserId());
            if(ticket != null){
                try {
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                parkingGarageService.unparkCar(ticket.getTicketId());
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Available parking spots: " + parkingGarageService.getAvailableParkingSpots());
    }
}
