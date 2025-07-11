package main.java;

import main.java.booking.Booking;
import main.java.booking.BookingStatus;
import main.java.seat.Seat;
import main.java.seat.SeatStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MovieTicketBookingSystem {
    private static MovieTicketBookingSystem instance;
    private final List<Movie> movies;
    private final List<Theater> theaters;
    private final Map<String, Show> shows;
    private final Map<String, Booking> bookings;

    private static final String BOOKING_ID_PREFIX = "BKG";
    private static final AtomicLong bookingCounter = new AtomicLong(0);
    public static  synchronized MovieTicketBookingSystem getInstance(){
        if(instance == null){
            instance = new MovieTicketBookingSystem();
        }
        return instance;
    }
    private MovieTicketBookingSystem() {
        movies = new ArrayList<>();
        theaters = new ArrayList<>();
        shows = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
        System.out.println("MovieTicketBookingSystem initiated");
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }
    public void addTheater(Theater theater) {
        theaters.add(theater);
    }

    public void addShow(Show show) {
        shows.put(show.getId(), show);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Theater> getTheaters() {
        return theaters;
    }

    public Show getShow(String showId) {
        return shows.get(showId);
    }
    private void markSeatAsBooked(Show show, List<Seat> selectedSeats){
        for(Seat seat: selectedSeats){
            Seat showSeat = show.getSeats().get(seat.getId());
            showSeat.setStatus(SeatStatus.BOOKED);
        }
    }

    private boolean areSeatAvailable(Show show, List<Seat> selectedSeats){
        for(Seat seat: selectedSeats){
            Seat showSeat = show.getSeats().get(seat.getId());
            if(showSeat == null || showSeat.getStatus() != SeatStatus.AVAILABLE){
                return false;
            }
        }
        return true;
    }

    private double calculatePrice(List<Seat> selectedSeat){
        return selectedSeat.stream().mapToDouble(Seat::getPrice).sum();
    }

    private String generateBookingId(){
        long bookingNumber = bookingCounter.incrementAndGet();
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return BOOKING_ID_PREFIX + timeStamp + String.format("%06d", bookingNumber);
    }

    public synchronized Booking booktickets(User user, Show show, List<Seat> selectedSeat){
        if(areSeatAvailable(show, selectedSeat)){
            markSeatAsBooked(show, selectedSeat);
            double totalPrice = calculatePrice(selectedSeat);
            String bookingId = generateBookingId();
            Booking booking = new Booking(bookingId, user, show, selectedSeat, BookingStatus.PENDING, totalPrice);
            bookings.put(bookingId, booking);
            return booking;
        }
        System.out.println("Seats are not available");
        return null;
    }

    public synchronized void confirmBooking(String bookingId){
        Booking booking = bookings.get(bookingId);
        if(booking != null  && booking.getStatus() == BookingStatus.PENDING){
            booking.setStatus(BookingStatus.CONFIRMED);
            System.out.println("Booking is confirmed with bookingId: " + bookingId);
        }
    }

    public synchronized  void cancelBooking(String bookingId){
        Booking booking = bookings.get(bookingId);
        if(booking != null  && booking.getStatus() == BookingStatus.CONFIRMED){
            booking.setStatus(BookingStatus.CANCELLED);
            markSeatsAsAvailable(booking.getShow(), booking.getSeats());
            System.out.println("Booking is cancelled with bookingId: " + bookingId);
        }
    }

    private void markSeatsAsAvailable(Show show, List<Seat> seats) {
        for (Seat seat : seats) {
            Seat showSeat = show.getSeats().get(seat.getId());
            showSeat.setStatus(SeatStatus.AVAILABLE);
        }
    }


}
