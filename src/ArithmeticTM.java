import java.util.Map;

/**
 * Specific implementation of {@link UniversalTuringMachine} with the addition to
 * read repeating symbols to the right of reader as a number, by counting them
 * (Generally used to read the result of an arithmetic calculation)
 */
public class ArithmeticTM extends UniversalTuringMachine {

    /**
     * Initializes new Turing Machine with given transitions
     *
     * @param transitionSymbolMapper    {@link Map} with key: {@link Integer} amount of '0' and value: {@link Character}
     * @param transitionDirectionMapper {@link Map} with key: {@link Integer} amount of '0' and value: {@link Character}
     * @param transitionString          {@link String} of '0' and '1' with '1' between symbols and '11' between transitions
     * @param allowedCharacters
     * @throws IllegalArgumentException if there is another symbol other than '1' or '0'
     */
    public ArithmeticTM(Map<Integer, Character> transitionSymbolMapper, Map<Integer, Band.BandDirections> transitionDirectionMapper, String transitionString, char[] allowedCharacters) {
        super(transitionSymbolMapper, transitionDirectionMapper, transitionString, allowedCharacters);
    }

    /**
     * Reads from the element on the reader and counts all elements on the right as long as they are equivalent,
     * if not equivalent, it stops and returns the result
     * @return count of symbols under reader to the right
     */
    public int readResult() {
        int result = 0;
        char symbol = band.getValue();
        boolean stop = false;

        if(Band.EMPTY_ELEMENT == symbol) {
            stop = true;
        } else {
            result = 1;
        }

        while(!stop) {
            band.move(Band.BandDirections.R);
            if(symbol == band.getValue()) {
                result++;
            } else {
                stop = true;
            }
        }

        return result;
    }
}
