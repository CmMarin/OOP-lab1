import javax.swing.*;
import task1.Display;

public class Main {
    public static void main(String[] args) {
        String assistantName = JOptionPane.showInputDialog("Enter the assistant's name:");
        Assistant assistant = new Assistant(assistantName);

        JOptionPane.showMessageDialog(null, "Hello! I am " + assistantName +
                ". I will help you compare display options to find the best one for you.\n" +
                "You'll enter details for each display, and I will compare them sequentially by size and sharpness.\n" +
                "After each round of comparisons, you’ll have the option to buy one of the displays.\n" +
                "Let’s get started!");

        // display nr prompt
        int numberOfDisplays = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of displays:"));

        // user data input about displays
        for (int i = 0; i < numberOfDisplays; i++) {
            int width = Integer.parseInt(JOptionPane.showInputDialog("Enter width for Display " + (i + 1) + ":"));
            int height = Integer.parseInt(JOptionPane.showInputDialog("Enter height for Display " + (i + 1) + ":"));
            String model = JOptionPane.showInputDialog("Enter model name for Display " + (i + 1) + ":");
            float ppi = Float.parseFloat(JOptionPane.showInputDialog("Enter PPI for Display " + (i + 1) + ":"));

            Display display = new Display(width, height, model, ppi);
            assistant.assignDisplay(display);
        }

        boolean continueComparing = true;
        while (continueComparing && assistant.getAssignedDisplays().size() > 1) {
            assistant.assist();

            // show display list
            StringBuilder displayModels = new StringBuilder("Current Displays:\n");
            for (Display d : assistant.getAssignedDisplays()) {
                displayModels.append(d.model).append("\n");
            }

            // buy display prompt
            String modelToBuy = JOptionPane.showInputDialog(displayModels + "Enter the model name of the display you want to buy (or type 'no' to skip):");
            if (!modelToBuy.equalsIgnoreCase("no")) {
                Display boughtDisplay = assistant.buyDisplay(modelToBuy);
                if (boughtDisplay != null) {
                    StringBuilder remainingModels = new StringBuilder("You bought the display: " + boughtDisplay.model + "\nRemaining Displays:\n");
                    for (Display d : assistant.getAssignedDisplays()) {
                        remainingModels.append(d.model).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, remainingModels.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Display model not found.");
                }
            }

            // continue comparing prompt
            Object[] options = {"Yes", "No", "Add Display to Comparison"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Do you want to continue comparing the remaining displays?",
                    "Continue Comparison",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == JOptionPane.YES_OPTION) {
                continueComparing = true;
            } else if (choice == JOptionPane.NO_OPTION) {
                continueComparing = false;
            } else if (choice == 2) { // "Add Display to Comparison"
                int width = Integer.parseInt(JOptionPane.showInputDialog("Enter width for the new display:"));
                int height = Integer.parseInt(JOptionPane.showInputDialog("Enter height for the new display:"));
                String model = JOptionPane.showInputDialog("Enter model name for the new display:");
                float ppi = Float.parseFloat(JOptionPane.showInputDialog("Enter PPI for the new display:"));

                Display newDisplay = new Display(width, height, model, ppi);
                assistant.assignDisplay(newDisplay);
                JOptionPane.showMessageDialog(null, "New display " + model + " has been added to the comparison list.");
            }
        }

        JOptionPane.showMessageDialog(null, "Thank you for using " + assistantName + "'s assistance. Have a great day!");
    }
}
