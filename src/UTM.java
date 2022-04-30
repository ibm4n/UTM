import java.util.ArrayList;
import java.util.Arrays;

public class UTM {

    private ArrayList<Transition> transitions = new ArrayList<>();

    private Band band;
    private final String TM_WORD_SEPARATOR = "111";
    private final String TRANSITION_SEPARATOR = "11";
    private final String SYMBOL_SEPARATOR = "1";


    /*private Transition generateTransition(String transitions) {
        String[] symbols = transitions.split(SYMBOL_SEPARATOR);

        return new Transition(symbols[0].length(), symbols[1].length(), symbols[2].length(), symbols[3].length(), symbols[4].length());

    }


    public void generateTM(String s) {

        String inputStringWithoutLeadingOne = s.substring(1, s.length());

        String word = inputStringWithoutLeadingOne.split(TM_WORD_SEPARATOR)[1];
        String tmCode = inputStringWithoutLeadingOne.split(TM_WORD_SEPARATOR)[0];

        String[] transitionsAsStrings = tmCode.split(TRANSITION_SEPARATOR);


        for (String transition : transitionsAsStrings) {
            transitions.add(generateTransition(transition));
        }

        for (Transition transition : transitions) {

            System.out.println(transition.toString());
        }
        Character[] wordsAsCharArray = word.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        band = new Band(Arrays.stream(wordsAsCharArray).toList());
        runTransitions(word);

    }

    private void runTransitions(String word) {


    }*/
}