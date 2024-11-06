package task2;

public class PumpkinSpiceLatte extends Cappucino{
    private int mgOfPumpkinSpice;


    public PumpkinSpiceLatte(int mlOfMilk, int mgOfPumpkinSpice, Intensity intensity) {
        super("Pumpkin Spice Latte", mlOfMilk, intensity);
        this.mgOfPumpkinSpice = mgOfPumpkinSpice;
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("Pumpkin Spice: " + mgOfPumpkinSpice + " mg of pumpkin spice");

    }

}
