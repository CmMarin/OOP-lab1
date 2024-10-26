package task1;

public class Display {

    public int width;
    public int height;
    public float ppi;
    public String model;

    public Display(int width, int height, String model, float ppi) {
        this.width = width;
        this.height = height;
        this.ppi = ppi;
        this.model = model;
    }

    public String toString() {
        return width + "x" + height + " " + ppi + " " + model;
    }

    public void compareSize(Display other) {
        int originArea = this.width * this.height;
        int otherArea = other.width * other.height;

        if (originArea > otherArea) {
            System.out.println(this.model + " has a larger screen area of " + originArea + " pixels compared to " + other.model + " with an area of " + otherArea + " pixels.");
        } else if (originArea < otherArea) {
            System.out.println(other.model + " has a larger screen area of " + otherArea + " pixels compared to " + this.model + " with an area of " + originArea + " pixels.");
        } else {
            System.out.println(this.model + " and " + other.model + " have equal screen sizes at " + originArea + " pixels.");
        }
    }

    public void compareSharpness(Display other) {
        if (this.ppi > other.ppi) {
            System.out.println(this.model + " features a higher pixel density of " + this.ppi + " PPI, resulting in sharper and clearer images compared to " + other.model + " which has " + other.ppi + " PPI.");
        } else if (this.ppi < other.ppi) {
            System.out.println(other.model + " offers superior sharpness with a pixel density of " + other.ppi + " PPI, enhancing detail and clarity over " + this.model + " which has a PPI of " + this.ppi + ".");
        } else {
            System.out.println(this.model + " and " + other.model + " share the same pixel density of " + this.ppi + " PPI.");
        }
    }

    public static void compareWithMonitor(Display[] displays) {
        Display bestDisplay = displays[0];
        double highestAverage = calculateAverage(bestDisplay);

        for (Display display : displays) {
            double currentAverage = calculateAverage(display);
            if (currentAverage > highestAverage) {
                bestDisplay = display;
                highestAverage = currentAverage;
            }
        }

        System.out.println("The display that offers the best combination of screen area and pixel density is: " + bestDisplay.model +
                " with an area of " + (bestDisplay.width * bestDisplay.height) + " pixels and a pixel density of " + bestDisplay.ppi + " PPI.");
    }

    private static double calculateAverage(Display display) {
        int area = display.width * display.height;
        return (area + display.ppi) / 2.0;
    }
}
