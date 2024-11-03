package task2;

public class Americano extends Coffee {
    private int mlOfWater;

    public Americano(int mlOfWater) {
        super("Americano", Intensity.STRONG);
        this.mlOfWater = mlOfWater;
    }



    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("Added " + mlOfWater  + " ml of water " );

    }
}
