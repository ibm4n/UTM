import java.util.ArrayList;
import java.util.Arrays;

public class UTM {

    private static final int START_TRANSITION_NUMBER = 1;
    private static final int END_TRANSITION_NUMBER = 100;

    private ArrayList<Transition> transitions = new ArrayList<>();

    private Band band;
    private final String TM_WORD_SEPARATOR = "111";
    private final String TRANSITION_SEPARATOR = "11";
    private final String SYMBOL_SEPARATOR = "1";

    private String word = "";

    private Transition generateTransition(String transitions) {
        String[] symbols = transitions.split(SYMBOL_SEPARATOR);

        return new Transition(symbols[0].length(), symbols[1].length(), symbols[2].length(), symbols[3].length(), symbols[4].length());

    }


    public void generateTM(String s, boolean removeLeadingOne) {

        String input = "";
        if (removeLeadingOne) {
            input = s.substring(1, s.length());
        } else {
            input = s;
        }

        String word = input.split(TM_WORD_SEPARATOR)[1];
        String tmCode = input.split(TM_WORD_SEPARATOR)[0];

        String[] transitionsAsStrings = tmCode.split(TRANSITION_SEPARATOR);


        for (String transition : transitionsAsStrings) {
            transitions.add(generateTransition(transition));
        }
        print("\n" + "-----------------------------------Transitions-----------------------------------------");
        for (Transition transition : transitions) {

            print(transition.toString());
        }
        print("----------------------------------------------------------------------------" + "\n");
        Character[] wordsAsCharArray = word.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        band = new Band(Arrays.stream(wordsAsCharArray).toList());
        this.word = word;

    }

    public void run(boolean stepModeOn) throws InterruptedException {

        boolean hasEnded = false;
        int countOfSteps = 0;
        Transition startTransition = getNextTransition(START_TRANSITION_NUMBER);

        if (startTransition == null) {
            print("Something with the Start Transition is fucked. Please check. Specified start transition is: q" + START_TRANSITION_NUMBER);
            throw new InterruptedException();
        }


        Transition currentTransition = startTransition;
        print("Starting State:");
        print(band.toString());


        while (!hasEnded) {
            countOfSteps++;
            var symbolToWrite = currentTransition.getSymbolToWrite();


            band.replaceSymbolAtCurrentPosition(symbolToWrite);
            band.move(currentTransition.getDirection());

            if (stepModeOn) {
                print(currentTransition.toString());
                print("Symbol at head: " + band.getSymbolAtCurrentPosition());
                print(band.toString());
                Thread.sleep(300);
            }

            currentTransition = getNextTransition(currentTransition.getNextState());


            if (currentTransition == null) {
                hasEnded = true;
            }
        }
        print("END-State:");
        print(band.toString());
        print("Berechnungsschritte: " + countOfSteps);
        print("Result: " + readResultFromBand());


    }

    private String readResultFromBand() {
        return String.valueOf(band.countRemainingSymbols());
    }


    private Transition getNextTransition(int transitionNumber) {
        return transitions.stream()
                .filter(x -> transitionNumber == x.getTransitionId() && band.getSymbolAtCurrentPosition() == x.getSymbolToRead())
                .findAny()
                .orElse(null);

    }


    private void print(String message) {

        System.out.println(message);
    }
}