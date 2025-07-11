package gates;

import parkinglot.ParkingLot;
import parkinglot.ParkingSpot;
import payment.PaymentService;
import vehicles.Vehicle;

public class ExitGate {
    private ParkingLot parkingLot;
    private PaymentService paymentService;


    public ExitGate(ParkingLot parkingLot, PaymentService paymentService) {
        this.parkingLot = parkingLot;
        this.paymentService = paymentService;
    }

    public void processExit(int spotNumber, int hourStayed){
        ParkingSpot spot = parkingLot.getSpotByNumber(spotNumber);
        if(spot == null || !spot.isOccupied()){
            System.out.println("Invalid or vacant spot");
            return;
        }
        Vehicle vehicle = spot.getVehicle();
        if(vehicle == null){
            System.out.println("No vehicle found on the spot");
            return;
        }
        // calculate the parking fee
        double fee = vehicle.calculateFee(hourStayed);
        paymentService.processPayment(fee);
        parkingLot.vacateSpot(spot, vehicle);
        System.out.println("Spot vacated successfully with spotNumber as " + spotNumber + " and vehicle " + vehicle.getLicensePlate());
    }
}
