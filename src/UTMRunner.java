import java.util.ArrayList;
import java.util.Scanner;

public class UTMRunner {
    private static final char STEP_MODE = '0';
    private static final char RUN_MODE = '1';
    private static boolean stepModeOn = false;


    public static void main(String[] args) {
        Scanner modeScanner = new Scanner(System.in);
        boolean quit = false;

        boolean modeSelected = false;

        System.out.println("""
                Select the mode to run the calculations. Possible mods are:
                0: Step-Mode
                1: Run-Mode""");
        while (!modeSelected) {
            char input = modeScanner.next().charAt(0);
            if (input == STEP_MODE) {
                stepModeOn = true;
                modeSelected = true;
            } else if (input == RUN_MODE) {
                stepModeOn = false;
                modeSelected = true;
            } else {
                System.out.println("Invalid input: please try again");
                System.out.println("""
                        Select the mode to run the calculations. Possible mods are:
                        0: Step-Mode
                        1: Run-Mode""");
            }
        }
        Scanner scanner = new Scanner(System.in);
        while (!quit) {
            System.out.println("\n" + "\n" + "Enter your TM-Code & calculation (Example can be found in the README):");
            String input = scanner.nextLine();

            if ("q".equals(input)) {
                quit = true;
            } else {
                UTM utm = new UTM();
                utm.generateTM(input, false);
                try {
                    utm.run(stepModeOn);
                } catch (InterruptedException e) {
                    System.out.println("Ooo no. Something blew up.");
                    System.out.println("Error: ");
                    e.printStackTrace();
                    quit = true;

                }
            }
        }


    }


}
