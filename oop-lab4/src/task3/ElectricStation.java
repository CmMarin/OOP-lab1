package task3;

public class ElectricStation implements Refuable{
    @Override
    public void refuel(String carId) {
        System.out.println("Charging car: " + carId);
    }
}
