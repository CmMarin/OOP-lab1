package task3;

public class Cappucino extends Coffee {
    private static int mlOfMilk;

    public Cappucino(String name,Intensity intensity ,int mlOfMilk) {
        super(name, intensity);
        this.mlOfMilk = mlOfMilk;
    }

    public Cappucino(int mlOfMilk, Intensity intensity) {
        super("Cappucino", intensity);
        this.mlOfMilk = mlOfMilk;
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("Added " + mlOfMilk  + " ml of milk ");

    }

    public static Cappucino makeCappucino(int mlOfMilk, Intensity intensity) {
        Cappucino coffee = new Cappucino(mlOfMilk, intensity);
        coffee.makeCoffee();
        System.out.println("Adding " + mlOfMilk + " ml of milk");
        return coffee;
    }

}
