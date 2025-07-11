import main.java.*;
import main.java.booking.Booking;
import main.java.seat.Seat;
import main.java.seat.SeatStatus;
import main.java.seat.SeatType;

import java.time.LocalDateTime;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        MovieTicketBookingSystem bookingSystem = MovieTicketBookingSystem.getInstance();
        // Add movies
        Movie movie1 = new Movie("M1", "Movie 1", "Description 1", 120);
        Movie movie2 = new Movie("M2", "Movie 2", "Description 2", 135);
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);

        // Add theaters
        Theater theater1 = new Theater("T1", "Theater 1", "Location 1", new ArrayList<>());
        Theater theater2 = new Theater("T2", "Theater 2", "Location 2", new ArrayList<>());
        bookingSystem.addTheater(theater1);
        bookingSystem.addTheater(theater2);

        // Add Shows
        Show show1 = new Show("S1", movie1, theater1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie1.getDurationInMinutes()),createSeat(10, 10));
        Show show2 = new Show("S2", movie2, theater2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie2.getDurationInMinutes()),createSeat(8, 8));

        // Book tickets
        User user = new User("U1", "John Doe", "john@example.com");
        List<Seat> selectedSeats1 = Arrays.asList(show1.getSeats().get("1-5"), show1.getSeats().get("1-6"));
        Booking booking = bookingSystem.booktickets(user, show1, selectedSeats1);
        if(booking != null)
            bookingSystem.confirmBooking(booking.getId());

        User user1 = new User("U2", "John Doe", "john@example.com");
        List<Seat> selectedSeats2 = Arrays.asList(show1.getSeats().get("1-3"), show1.getSeats().get("1-7"));
        Booking booking1 = bookingSystem.booktickets(user, show2, selectedSeats2);
        if(booking1 != null)
            bookingSystem.confirmBooking(booking1.getId());
        Booking booking2 = bookingSystem.booktickets(user1, show2, selectedSeats2);
        if(booking2 != null)
            bookingSystem.confirmBooking(booking2.getId());
        // Cancel booking
        if(booking != null )
            bookingSystem.cancelBooking(booking.getId());


    }



    private static Map<String, Seat> createSeat(int row, int col){
        Map<String, Seat> seats = new HashMap<>();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                String seatId = i + "-" + j;
                SeatType seatType = i <= 2 ? SeatType.PREMIUM: SeatType.NORMAL;
                double price = (seatType == SeatType.NORMAL)? 150.0 : 200.0;
                Seat seat = new Seat(seatId, i, j, seatType, price, SeatStatus.AVAILABLE);
                seats.put(seatId, seat);
            }
        }
        return seats;
    }



}