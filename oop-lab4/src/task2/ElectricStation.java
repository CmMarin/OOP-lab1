package task2;

public class ElectricStation implements Refuable {

    private int count = 0;

    @Override
    public void refuel(String carId) {
        System.out.println("Charging car: " + carId);
        count++;
        System.out.println("Tesla Customer: " + count);
    }
}
