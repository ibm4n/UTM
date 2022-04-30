import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Turing machine without using lists for band.
 * <br>
 * Takes a String in single-number-system with '0' as value and '1' as separator for tuple value and '11' for transitions
 * this String defines the transitions of the Turing Machine.
 * <br>
 * Requires:
 * {@link java.util.Map} providing mapping of amount of '0' to relevant {@link Character}:
 * For example: '00' (2) -> 1 and '0' (1) -> 0.
 * {@link Map} providing mapping of amount of '0' to relevant {@link Band.BandDirections}:
 * For example: '00' (2) -> L and '0' (1) -> R.
 * State number is derived as {@link Integer} count of '0' without mapping.
 * <br>
 * Also requires {@link Character}[] of allowed characters in band
 * <br>
 * Machine can take string of symbols as "input" which will be added to the band right of element on reader.
 * <br>
 * Calculations are performed based on given transition string and state of band is displayed after each calculation.
 */
public class UniversalTuringMachine {

    private static final String TRANSITION_SEPARATOR = "11";
    private static final String SYMBOL_SEPARATOR = "1";
    /**
     * Regex pattern that allows one or more '0'
     */
    private static final String SYMBOL_REGEX = "0+";

    private final Character[] allowedCharacters;
    private final Transition[] transitions;
    protected final Band band;

    private int currentState = 0;

    /**
     * Initializes new Turing Machine with given transitions
     * @param transitionSymbolMapper {@link Map} with key: {@link Integer} amount of '0' and value: {@link Character}
     * @param transitionDirectionMapper {@link Map} with key: {@link Integer} amount of '0' and value: {@link Character}
     * @param transitionString {@link String} of '0' and '1' with '1' between symbols and '11' between transitions
     * @throws IllegalArgumentException if there is another symbol other than '1' or '0'
     */
    public UniversalTuringMachine(Map<Integer, Character> transitionSymbolMapper,
                                  Map<Integer, Band.BandDirections> transitionDirectionMapper,
                                  String transitionString, char[] allowedCharacters) {

        this.allowedCharacters = new String(allowedCharacters).chars()
                .mapToObj(c -> (char) c)
                .toArray(Character[]::new);
        this.band = new Band(allowedCharacters);
        this.transitions = parseTransitions(transitionSymbolMapper, transitionDirectionMapper, transitionString);
    }

    /**
     * Runs the turing machine for specified input.
     * <br>
     * Follows current list of steps:
     * <ol>
     *     <li>Writes input onto band from the reader to the right</li>
     *     <li>Follows transitions defined for machine</li>
     *     <li>Writes the current state to the band to the console after each transition</li>
     * </ol>
     * @param input {@link String} input to perform calculations on
     * @param doStepByStep {@link Boolean} defines if the machine should display transition and band after calculation
     * @param timeToWait {@link Integer} time in MS to wait in-between calculations
     * @throws IllegalArgumentException when the input contains a character that is not in the allowed array for this machine
     * @throws InterruptedException when there is a problem whilst sleeping before next calculation
     */
    public void run(String input, boolean doStepByStep, int timeToWait) throws IllegalArgumentException, InterruptedException{
        addInputOntoBand(input);
        System.out.println(band);
        System.out.println("Initial State");

        Transition nextTransition = getNextTransition();
        while(nextTransition != null) {
            currentState = nextTransition.getEndState();
            band.setValue(nextTransition.getSymbolToWrite());
            band.move(nextTransition.getDirection());

            if(doStepByStep) {
                System.out.println(band);
                System.out.println(nextTransition);
                Thread.sleep(timeToWait);
            }

            nextTransition = getNextTransition();
        }

        System.out.println(band);
        System.out.println("Result");
    }

    /**
     * Gets the next transition defined for this machine based on the current state of the machine and the value on the reader
     * @return the next possible {@link Transition} or null if there are no more possible transitions
     */
    private Transition getNextTransition() {
        Transition result = null;

        char valueOnReader = band.getValue();
        for(Transition transition : transitions) {
            if(currentState == transition.getStartingState() && valueOnReader == transition.getSymbolToRead()) {
                result = transition;
            }
        }

        return result;
    }

    /**
     * Starts adding symbols onto input string on reader and moves right for each element.
     * At the end moves the reader back left onto the first symbol
     * @param input {@link String} input to write onto band
     * @throws IllegalArgumentException when the input contains a character that is not in the allowed array for this machine
     */
    private void addInputOntoBand(String input) throws IllegalArgumentException {
        char[] inputArray = input.toCharArray();
        for(char symbol : inputArray) {
            if(Arrays.stream(allowedCharacters).noneMatch(c -> symbol == c)) {
                throw new IllegalArgumentException(String.format("Character: %c, is not allowed for this machine", symbol));
            }

            band.setValue(symbol);
            band.move(Band.BandDirections.R);
        }

        // go back to first symbol
        for(int i = 0; i < inputArray.length; i++) {
            band.move(Band.BandDirections.L);
        }
    }

    /**
     * Parses a transition {@link String} into a {@link Transition} array
     * @param transitionSymbolMapper {@link Map} of amount of '0' and corresponding symbol ({@link Character})
     * @param transitionDirectionMapper {@link Map} of amount of '0' and corresponding symbol ({@link Band.BandDirections})
     * @param transitionString {@link String} to parse
     * @return array of {@link Transition}
     * @throws IllegalArgumentException when there is another number other than '0' and '1' in transition string
     */
    private Transition[] parseTransitions(Map<Integer, Character> transitionSymbolMapper,
                                          Map<Integer, Band.BandDirections> transitionDirectionMapper,
                                          String transitionString) throws IllegalArgumentException {

        String[] transitionSections = removeEmpty(transitionString.split(TRANSITION_SEPARATOR));
        Transition[] transitions = new Transition[transitionSections.length];

        for(int i = 0; i < transitionSections.length; i++) {
            String section = transitionSections[i];
            String[] parameters = removeEmpty(section.split(SYMBOL_SEPARATOR));

            if(!Arrays.stream(parameters).allMatch(param -> param.matches(UniversalTuringMachine.SYMBOL_REGEX))) {
                throw new IllegalArgumentException("Only '1' and '0' are allowed");
            }

            int startingStateNumber = parameters[0].length() - 1; // -1 -> starting state is 0
            char symbolToRead = transitionSymbolMapper.get(parameters[1].length());
            int endStateNumber = parameters[2].length() - 1; // -1 -> starting state is 0
            char symbolToWrite = transitionSymbolMapper.get(parameters[3].length());
            Band.BandDirections direction = transitionDirectionMapper.get(parameters[4].length());

            transitions[i] = new Transition(startingStateNumber, symbolToRead, endStateNumber, symbolToWrite, direction);
        }

        return transitions;
    }

    /**
     * Removes empty {@link String} from array
     * @param array array to clean-up
     * @return array without empty values
     */
    private String[] removeEmpty(String[] array) {
        List<String> filledValues = new ArrayList<>();

        for(String value : array) {
            if(!value.isEmpty()) {
                filledValues.add(value);
            }
        }

        return filledValues.toArray(new String[0]);
    }

    /**
     * Returns TM definition with all transitions
     * For example: "(q0, 0) -> (q3, 1, R)"
     * @return List of transitions
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Transition transition : transitions) {
            builder.append(transition).append("\n");
        }
        
        return builder.toString();
    }

    /**
     * Displays band and indicator on current element under reader
     * @return {@link String} representation of {@link Band}
     */
    public String getBandState() {
        return band.toString();
    }
}
