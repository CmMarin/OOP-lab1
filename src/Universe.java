import java.util.ArrayList;
import java.util.Arrays;


public class Universe {

    private ArrayList<Aliens> aliens;

    public Universe() {
        aliens = new ArrayList<>();
    }


    public void addAlien(Aliens alien) {
        aliens.add(alien);
    }

    public void displayAliens() {
        for (Aliens alien : aliens) {
            System.out.println("Alien name:" + alien.getName());
            System.out.println("Age: " + alien.getAge());
            System.out.println("Planet Origin: " + alien.getPlanet());
            System.out.println("Humanoid: " + alien.getisHumanoid());
//            System.out.println("Traits: " + Arrays.toString(alien.getTraits()));
            System.out.println();
        }
    }
}
