public class Aliens {
    private String name;
    private String planet;
    private boolean isHumanoid;
    private int age;
//    private char[] traits;


    //constructor

    public Aliens(String name, String planet, boolean isHumanoid, int age) {
        this.name = name;
        this.planet = planet;
        this.isHumanoid = isHumanoid;
//        this.traits = traits;
        this.age = age;
    }

    //methods
    public String getName(){
        return name;
    }

    public String getPlanet(){
        return planet;
    }

    public boolean getisHumanoid(){
        return isHumanoid;
    }

    public int getAge(){
        return age;
    }

//    public char[] getTraits(){
//        return traits;
//    }

}
