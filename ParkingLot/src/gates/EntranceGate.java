package gates;

import parkinglot.ParkingLot;
import parkinglot.ParkingSpot;
import vehicles.Vehicle;
import vehicles.VehicleFactory;

import java.util.Scanner;

public class EntranceGate {
    private ParkingLot parkingLot;
    public EntranceGate(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }
    public void processEntrance(){
        Scanner scanner = new Scanner((System.in));
        System.out.println("Enter the vehicle license no.");
        String licenseNo = scanner.next();
        System.out.println("Enter the vehicle type: Car or Bike");
        String vehicleType = scanner.next();
        Vehicle vehicle = VehicleFactory.createVehicle(vehicleType, licenseNo);
        if(vehicle == null){
            System.out.println("Invalid vehicle type, Only Car and Bike are allowed.");
            return;
        }

        ParkingSpot spot = parkingLot.parkVehicle(vehicle);
        if(spot != null){
            System.out.println("Vehicle parked successfully in spot: " + spot.getSpotNumber());
        }else{
            System.out.println("No available spots for vehicle type ");
        }
    }
}
