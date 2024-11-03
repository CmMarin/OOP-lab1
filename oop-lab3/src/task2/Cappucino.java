package task2;

public class Cappucino extends Coffee {
    private int mlOfMilk;

    public Cappucino(String name,int mlOfMilk, Intensity intensity) {
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
        System.out.println("Milk quantity " + mlOfMilk  + " ml of milk ");

    }

}
