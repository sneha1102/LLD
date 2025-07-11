package main.java.service;

 import main.java.models.Booking;
 import main.java.models.Gym;
 import main.java.models.GymClass;
 import main.java.repository.BookingRepository;
 import main.java.repository.GymRepository;

 import java.util.*;
 import java.util.concurrent.locks.Lock;
 import java.util.concurrent.locks.ReentrantLock;
 import java.time.LocalTime;
 import java.time.format.DateTimeFormatter;
 import java.time.format.DateTimeParseException;

public class GymService {
    private final GymRepository gymRepository;
    private final BookingRepository bookingRepository;
    private final Lock lock = new ReentrantLock();
    private static final LocalTime GYM_OPEN_TIME = LocalTime.of(6, 0); // 6:00 AM
    private static final LocalTime GYM_CLOSE_TIME = LocalTime.of(20, 0); // 8:00 PM
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public GymService(GymRepository gymRepository, BookingRepository bookingRepository) {
        this.gymRepository = gymRepository;
        this.bookingRepository = bookingRepository;
    }

    public String addGym(String name, String location, int maxAccommodation) {
        // Input validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Gym name cannot be null or empty");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Gym location cannot be null or empty");
        }
        if (maxAccommodation <= 0) {
            throw new IllegalArgumentException("Max accommodation must be greater than 0");
        }

        String gymId = UUID.randomUUID().toString();
        Gym gym = new Gym(gymId, name.trim(), location.trim(), maxAccommodation);
        gymRepository.saveGym(gym);
        return gymId;
    }

    public void removeGym(String gymId) {
        lock.lock();
        try {
            if (gymId == null || gymId.trim().isEmpty()) {
                throw new IllegalArgumentException("Gym ID cannot be null or empty");
            }

            Gym gym = gymRepository.getGymById(gymId);
            if (gym == null) {
                throw new IllegalArgumentException("Gym ID not found: " + gymId);
            }

            // Remove all bookings for this gym
            List<Booking> bookingsToRemove = new ArrayList<>();
            for (Booking booking : bookingRepository.getAllBookings()) {
                if (booking.getGymId().equals(gymId)) {
                    bookingsToRemove.add(booking);
                }
            }
            
            for (Booking booking : bookingsToRemove) {
                bookingRepository.deleteBooking(booking.getId());
            }

            gymRepository.deleteGym(gymId);
        } finally {
            lock.unlock();
        }
    }

    private void validateTime(String time) {
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("Time cannot be null or empty");
        }
        
        try {
            LocalTime parsedTime = LocalTime.parse(time.trim(), TIME_FORMATTER);
            if (parsedTime.isBefore(GYM_OPEN_TIME) || parsedTime.isAfter(GYM_CLOSE_TIME)) {
                throw new IllegalArgumentException("Time must be between " + GYM_OPEN_TIME.format(TIME_FORMATTER) + 
                    " and " + GYM_CLOSE_TIME.format(TIME_FORMATTER));
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Use HH:mm format (e.g., 06:00, 14:30)");
        }
    }

    private void validateTimeRange(String startTime, String endTime) {
        LocalTime start = LocalTime.parse(startTime.trim(), TIME_FORMATTER);
        LocalTime end = LocalTime.parse(endTime.trim(), TIME_FORMATTER);
        
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
    }

    public String addClass(String gymId, String classType, int maxLimit, String startTime, String endTime) {
        // Input validation
        if (gymId == null || gymId.trim().isEmpty()) {
            throw new IllegalArgumentException("Gym ID cannot be null or empty");
        }
        if (classType == null || classType.trim().isEmpty()) {
            throw new IllegalArgumentException("Class type cannot be null or empty");
        }
        if (maxLimit <= 0) {
            throw new IllegalArgumentException("Max limit must be greater than 0");
        }

        // Validate time format and range
        validateTime(startTime);
        validateTime(endTime);
        validateTimeRange(startTime, endTime);

        Gym gym = gymRepository.getGymById(gymId);
        if (gym == null) {
            throw new IllegalArgumentException("Gym ID not found: " + gymId);
        }
        if (maxLimit > gym.getMaxAccommodation()) {
            throw new IllegalArgumentException("Class max limit (" + maxLimit + 
                ") exceeds gym's maximum accommodation (" + gym.getMaxAccommodation() + ")");
        }

        String classId = UUID.randomUUID().toString();
        gym.addClass(classId, classType.trim(), maxLimit, startTime.trim(), endTime.trim());
        return classId;
    }

    public void removeClass(String gymId, String classId) {
        if (gymId == null || gymId.trim().isEmpty()) {
            throw new IllegalArgumentException("Gym ID cannot be null or empty");
        }
        if (classId == null || classId.trim().isEmpty()) {
            throw new IllegalArgumentException("Class ID cannot be null or empty");
        }

        Gym gym = gymRepository.getGymById(gymId);
        if (gym == null) {
            throw new IllegalArgumentException("Gym ID not found: " + gymId);
        }

        // Remove all bookings for this class
        List<Booking> bookingsToRemove = new ArrayList<>();
        for (Booking booking : bookingRepository.getAllBookings()) {
            if (booking.getClassId().equals(classId)) {
                bookingsToRemove.add(booking);
            }
        }
        
        for (Booking booking : bookingsToRemove) {
            bookingRepository.deleteBooking(booking.getId());
        }

        gym.removeClass(classId);
    }

    public String bookClass(String customerId, String gymId, String classId) {
        lock.lock();
        try {
            // Input validation
            if (customerId == null || customerId.trim().isEmpty()) {
                throw new IllegalArgumentException("Customer ID cannot be null or empty");
            }
            if (gymId == null || gymId.trim().isEmpty()) {
                throw new IllegalArgumentException("Gym ID cannot be null or empty");
            }
            if (classId == null || classId.trim().isEmpty()) {
                throw new IllegalArgumentException("Class ID cannot be null or empty");
            }

            Gym gym = gymRepository.getGymById(gymId);
            if (gym == null) {
                throw new IllegalArgumentException("Gym ID not found: " + gymId);
            }
            GymClass gymClass = gym.getClassById(classId);
            if (gymClass == null) {
                throw new IllegalArgumentException("Class ID not found: " + classId);
            }
            if (gymClass.isFullyBooked()) {
                throw new IllegalArgumentException("Class is fully booked");
            }
            if (gymClass.hasCustomerBooked(customerId)) {
                throw new IllegalArgumentException("Customer has already booked this class");
            }

            String bookingId = UUID.randomUUID().toString();
            Booking booking = new Booking(bookingId, gymId, classId, customerId);
            gymClass.addBooking(customerId);
            bookingRepository.saveBooking(booking);
            return bookingId;
        } finally {
            lock.unlock();
        }
    }

    public List<Booking> getAllBookingsByCustomer(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }

        List<Booking> customerBookings = new ArrayList<>();
        for (Booking booking : bookingRepository.getAllBookings()) {
            if (booking.getCustomerId().equals(customerId)) {
                customerBookings.add(booking);
            }
        }
        return customerBookings;
    }

    public void cancelBooking(String bookingId) {
        lock.lock();
        try {
            if (bookingId == null || bookingId.trim().isEmpty()) {
                throw new IllegalArgumentException("Booking ID cannot be null or empty");
            }

            Booking booking = bookingRepository.getBookingById(bookingId);
            if (booking == null) {
                throw new IllegalArgumentException("Booking ID not found: " + bookingId);
            }

            Gym gym = gymRepository.getGymById(booking.getGymId());
            if (gym != null) {
                GymClass gymClass = gym.getClassById(booking.getClassId());
                if (gymClass != null) {
                    gymClass.removeBooking(booking.getCustomerId());
                }
            }
            bookingRepository.deleteBooking(bookingId);
        } finally {
            lock.unlock();
        }
    }
}
