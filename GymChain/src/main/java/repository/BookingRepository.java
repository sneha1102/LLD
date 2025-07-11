package main.java.repository;

import main.java.models.Booking;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BookingRepository {
    private final ConcurrentMap<String, Booking> bookings = new ConcurrentHashMap<>();

    public void saveBooking(Booking booking) {
        bookings.put(booking.getId(), booking);
    }

    public Booking getBookingById(String bookingId) {
        return bookings.get(bookingId);
    }

    public void deleteBooking(String bookingId) {
        bookings.remove(bookingId);
    }

    public Collection<Booking> getAllBookings() {
        return bookings.values();
    }
}
