import java.util.ArrayList;
import java.util.List;
import task1.Display;

public class Assistant {
    private String assistantName;
    private List<Display> assignedDisplays;

    // Constructor
    public Assistant(String assistantName) {
        this.assistantName = assistantName;
        this.assignedDisplays = new ArrayList<>();
    }

    // Method to add a Display to the list
    public void assignDisplay(Display d) {
        assignedDisplays.add(d);
    }

    public List<Display> getAssignedDisplays() {
        return assignedDisplays;
    }

    // call functions from display class from task 1 to compare each monitor
    public void assist() {
        System.out.println("\nStarting comparison of displays...");
// for loop to compare
        for (int i = 0; i < assignedDisplays.size(); i++) {
            Display current = assignedDisplays.get(i);
            Display next = assignedDisplays.get((i + 1) % assignedDisplays.size()); // go back to compare to first display

            System.out.println("Comparing " + current.model + " and " + next.model + ":");
            current.compareSize(next);
            current.compareSharpness(next);
            System.out.println();
        }
    }

    // buy display and remove from list
    public Display buyDisplay(String modelName) {
        for (Display d : assignedDisplays) {
            if (d.model.equalsIgnoreCase(modelName)) {
                assignedDisplays.remove(d);
                return d;
            }
        }
        return null;
    }
}
