package task2;

public class Americano extends Coffee {
    private int mlOfWater;

    public Americano(int mlOfWater, Intensity intensity) {
        super("Americano", intensity);
        this.mlOfWater = mlOfWater;
    }



    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("Water quantity " + mlOfWater  + " ml of water " );

    }


}
