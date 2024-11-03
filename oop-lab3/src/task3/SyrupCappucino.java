package task3;

public class SyrupCappucino extends Cappucino {
    private SyrupType syrupType;
    enum SyrupType{
        MACADAMIA,
        VANILLA,
        COCONUT,
        CARAMEL,
        CHOCOLATE,
        POPCORN,
    }


    public SyrupCappucino(SyrupType syrupType, int mlOfMilk, Intensity intensity) {
        super("Syrup Cappucino", intensity,mlOfMilk);
        this.syrupType = syrupType;
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("With " + syrupType );

    }

    public static SyrupCappucino makeSyrupCappucino(SyrupType syrupType, int mlOfMilk, Intensity intensity) {
        SyrupCappucino coffee = new SyrupCappucino(syrupType, mlOfMilk, intensity);
        coffee.makeCoffee();
        System.out.println("Adding:" + syrupType + " syrup");
        System.out.println("Adding " + mlOfMilk + " ml of milk");
        return coffee;
    }
}
