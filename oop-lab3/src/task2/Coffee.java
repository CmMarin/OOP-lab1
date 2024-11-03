package task2;

public class Coffee {
    private String name;
    private Intensity intensity;

    enum Intensity{
        LIGHT,
        NORMAL,
        STRONG
    }

    public Coffee(String name, Intensity intensity) {
        this.name = name;
        this.intensity = intensity;
    }

    public void printDetails(){
        System.out.println("\nSelected: " + name);
        System.out.println("Intensity: " + intensity);
    }

}
