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

    public void getName(String name) {
        this.name = name;
    }

    public void getIntensity(Intensity intensity) {
        this.intensity = intensity;
    }

    public void printDetails(){
        System.out.println("\nYou selected: " + name);
        System.out.println("Intensity: " + intensity);
    }


}
