package main.java;

import main.java.models.Booking;
import main.java.repository.BookingRepository;
import main.java.repository.GymRepository;
import main.java.service.GymService;

import java.util.List;

public class DriverClass {
    public static void main(String[] args) {
        GymRepository gymRepository = new GymRepository();
        BookingRepository bookingRepository = new BookingRepository();
        GymService gymService = new GymService(gymRepository, bookingRepository);

        System.out.println("=== GymChain Booking System Demo ===\n");

        try {
            // Admin adds a gym
            System.out.println("1. Admin adding gym...");
            String gymId = gymService.addGym("Gym1", "Indira Nagar", 100);
            System.out.println("✅ Gym added successfully: " + gymId);

            // Admin adds multiple classes to the gym
            System.out.println("\n2. Admin adding classes...");
            String classId1 = gymService.addClass(gymId, "Cardio", 20, "06:00", "07:00");
            System.out.println("✅ Cardio class added: " + classId1);
            
            String classId2 = gymService.addClass(gymId, "Weights", 15, "14:00", "15:00");
            System.out.println("✅ Weights class added: " + classId2);
            
            String classId3 = gymService.addClass(gymId, "Yoga", 10, "18:00", "19:00");
            System.out.println("✅ Yoga class added: " + classId3);

            // Customer bookings
            System.out.println("\n3. Customer bookings...");
            String bookingId1 = gymService.bookClass("Customer1", gymId, classId1);
            System.out.println("✅ Customer1 booked Cardio: " + bookingId1);
            
            String bookingId2 = gymService.bookClass("Customer2", gymId, classId1);
            System.out.println("✅ Customer2 booked Cardio: " + bookingId2);
            
            String bookingId3 = gymService.bookClass("Customer1", gymId, classId2);
            System.out.println("✅ Customer1 booked Weights: " + bookingId3);

            // Customer views all bookings
            System.out.println("\n4. Customer viewing bookings...");
            List<Booking> customer1Bookings = gymService.getAllBookingsByCustomer("Customer1");
            System.out.println("✅ Customer1 has " + customer1Bookings.size() + " bookings");

            // Cancel a booking
            System.out.println("\n5. Customer canceling booking...");
            gymService.cancelBooking(bookingId1);
            System.out.println("✅ Booking canceled successfully");

            // Verify booking count after cancellation
            customer1Bookings = gymService.getAllBookingsByCustomer("Customer1");
            System.out.println("✅ Customer1 now has " + customer1Bookings.size() + " bookings");

            // Test error cases
            System.out.println("\n6. Testing error cases...");
            
            try {
                gymService.addGym("", "Location", 100);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Expected error: " + e.getMessage());
            }
            
            try {
                gymService.addClass(gymId, "Invalid", 5, "05:00", "06:00"); // Before 6am
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Expected error: " + e.getMessage());
            }
            
            try {
                gymService.addClass(gymId, "Invalid", 5, "21:00", "22:00"); // After 8pm
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Expected error: " + e.getMessage());
            }
            
            try {
                gymService.addClass(gymId, "Invalid", 5, "07:00", "06:00"); // End before start
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Expected error: " + e.getMessage());
            }
            
            try {
                gymService.bookClass("Customer1", gymId, classId1); // Try to book same class again
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Expected error: " + e.getMessage());
            }

            // Test gym removal (should cancel all bookings)
            System.out.println("\n7. Testing gym removal...");
            String gymId2 = gymService.addGym("Gym2", "Koramangala", 50);
            String classId4 = gymService.addClass(gymId2, "Pilates", 8, "10:00", "11:00");
            String bookingId4 = gymService.bookClass("Customer3", gymId2, classId4);
            System.out.println("✅ Created Gym2 with booking: " + bookingId4);
            
            // Check booking exists
            List<Booking> customer3Bookings = gymService.getAllBookingsByCustomer("Customer3");
            System.out.println("✅ Customer3 has " + customer3Bookings.size() + " bookings before gym removal");
            
            // Remove gym
            gymService.removeGym(gymId2);
            System.out.println("✅ Gym2 removed successfully");
            
            // Check booking is gone
            customer3Bookings = gymService.getAllBookingsByCustomer("Customer3");
            System.out.println("✅ Customer3 has " + customer3Bookings.size() + " bookings after gym removal");

            System.out.println("\n=== Demo completed successfully! ===");

        } catch (IllegalArgumentException ex) {
            System.err.println("❌ Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("❌ Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
