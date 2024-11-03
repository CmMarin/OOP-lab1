package task2;

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
        super("Syrup Cappucino", mlOfMilk, intensity);
        this.syrupType = syrupType;
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("Syrup " + syrupType );

    }

}
