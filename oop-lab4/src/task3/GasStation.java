package task3;

public class GasStation implements Refuable{
    @Override
    public void refuel(String carId) {
        System.out.println("Refueling car with ID: " + carId);
    }
}
