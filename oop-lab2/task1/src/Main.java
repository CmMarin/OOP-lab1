

public class Main {
    public static void main(String[] args) {

        Display display1 = new Display(1920, 1080, "Acer xsxW24", 401);
        Display display2 = new Display(2560, 1440, "Samsung oddysey", 515);
        Display display3 = new Display(1280, 720, "AOC 56sdCCx", 326);
        Display[] displays = {display1, display2, display3};
        System.out.println();
        display1.compareSize(display2);
        display1.compareSize(display3);
        display2.compareSize(display3);
        display2.compareSharpness(display3);
        display3.compareSharpness(display1);
        display1.compareSharpness(display2);
        Display.compareWithMonitor(displays);
    }
}
