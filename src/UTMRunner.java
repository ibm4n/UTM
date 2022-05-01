import java.util.*;

public class UTMRunner {


    private static final char STEP_MODE = '0';
    private static final char RUN_MODE = '1';
    private static final String TM_CODE = "101010010001011010001000000000100010110010100101011001000100010001011000101000010010110001000100000000100010011000010100001010110000100010000010001011000001010000010101100000100010000001010011000000101000000101001100000010001000000010001001100000001010000000101001100000001001000100101100000000100100000000101001100000000100010000000000001000100110000000001000100000000010001011000000000101000000000010001011000000000010100000000001000101100000000001000100000000000100010110000000000001010000000000001010011000000000000100010100010111";
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
            System.out.println("Enter your TM-Code & calculation:");
            System.out.println(TM_CODE);

            String inputCalcOnly = scanner.nextLine();
            String input = TM_CODE + inputCalcOnly;

            if ("q".equals(input)) {
                quit = true;
            } else {
                handleInput(input);
            }
        }
    }

    private static void handleInput(String input) {
        UTM turingMachine = new UTM();

        try {
            turingMachine.run(input, stepModeOn, 250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Calculation result = %d\n", turingMachine.getResultFromBand());
        System.out.println("--------------------------------------------------------");
        System.out.println("\n" + "\n" + "\n" + "\n");
    }


}
