import java.util.*;

public class UTMRunner {

    private static char[] allowedCharacters = { '0', '1', Band.EMPTY_ELEMENT };
    private static Map<Integer, Character> symbolMapper = new HashMap<>() {{
        put(1, '0');
        put(2, '1');
        put(3, '_');
    }};
    private static Map<Integer, Band.BandDirections> directionMapper = new HashMap<>() {{
        put(1, Band.BandDirections.R);
        put(2, Band.BandDirections.L);
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while(!quit) {
            String input = scanner.nextLine();
            if("q".equals(input)) {
                quit = true;
            } else {
                fun(input);
            }
        }
    }

    private static void fun(String input) {
        ArithmeticTM turingMachine = new ArithmeticTM(symbolMapper,
                directionMapper,
                UTMCodes.MULTIPLICATION_CODE,
                allowedCharacters);

        String convertedInput = convertNumberInputToTMInput(input);
        try {
            turingMachine.run(convertedInput, true, 250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Calculation result = %d\n", turingMachine.readResult());
    }

    private static String convertNumberInputToTMInput(String input) {
        StringBuilder convertedInput = new StringBuilder();
        String multiplicationRegex = "\\d\\s?\\*\\s?\\d";
        if(input.matches(multiplicationRegex)) {
            String[] numbers = input.split("\\*");
            int a = Integer.parseInt(numbers[0].trim());
            int b = Integer.parseInt(numbers[1].trim());

            convertedInput.append("0".repeat(Math.max(0, a)));
            convertedInput.append("_");
            convertedInput.append("0".repeat(Math.max(0, b)));
        }

        return convertedInput.toString();
    }
}
