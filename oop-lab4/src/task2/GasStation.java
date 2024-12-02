package task2;

public class GasStation implements Refuable{
    private int count = 0;


    @Override
    public void refuel(String carId) {
        System.out.println("Refueling Gas car: " + carId);
        count++;
        System.out.println("Gas Customer: " + count);

    }
}
