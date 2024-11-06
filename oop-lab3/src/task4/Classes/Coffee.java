package task4.Classes;

class Coffee {
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
        System.out.println("\nYou selected: " + name);
        System.out.println("Intensity: " + intensity);
    }

    public void makeCoffee(){
        System.out.println("\nMaking: " + name);
        System.out.println("Intensity set to: " + intensity);
    }


}
