package task4.Classes;

class PumpkinSpiceLatte extends Cappucino {
    private int mgOfPumpkinSpice;


    public PumpkinSpiceLatte(int mlOfMilk, int mgOfPumpkinSpice, Intensity intensity) {
        super("Pumpkin Spice Latte", intensity ,mlOfMilk);
        this.mgOfPumpkinSpice = mgOfPumpkinSpice;
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("With " + mgOfPumpkinSpice + " mg of pumpkin spice");

    }

    public static PumpkinSpiceLatte makePumpkinSpice(int mgOfPumpkinSpice, int mlOfMilk, Intensity intensity) {
        PumpkinSpiceLatte coffee = new PumpkinSpiceLatte(mlOfMilk, mgOfPumpkinSpice, intensity);
        coffee.makeCoffee();
        System.out.println("Adding: " + mgOfPumpkinSpice + " mg of pumpkin spice");
        System.out.println("Adding " + mlOfMilk + " ml of milk");
        return coffee;
    }
}
