package task2;

public class PumpkinSpiceLatte extends Cappucino{
    private int mgOfPumpkinSpice;


    public PumpkinSpiceLatte(int mlOfMilk, int mgOfPumpkinSpice) {
        super(mlOfMilk);
        this.mgOfPumpkinSpice = mgOfPumpkinSpice;
        getName("Pumpkin Spice Latte") ;
        getIntensity(Intensity.LIGHT);
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("With " + mgOfPumpkinSpice + " mg of pumpkin spice");

    }
}
