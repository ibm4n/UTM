import legacy.LegacyBand;

/**
 * Transition of Turing Machine
 * <br>
 * Possesses:
 * <ul>
 *     <li>starting state "q({@link Integer})"</li>
 *     <li>symbol to read {@link Character}</li>
 *     <li>end state "q({@link Integer})"</li>
 *     <li>symbol to write {@link Character}</li>
 *     <li>direction to move {@link LegacyBand.BandDirections}</li>
 * </ul>
 * <br>
 * Can display transition as e.g. "(q1, 0, R) -> (q2, 1)"
 */
public class Transition {

    private final int startingState;
    private final char symbolToRead;
    private final int endState;
    private final char symbolToWrite;
    private final LegacyBand.BandDirections direction;

    public Transition(int startingState, char symbolToRead, int endState, char symbolToWrite, LegacyBand.BandDirections direction) {
        this.startingState = startingState;
        this.symbolToRead = symbolToRead;
        this.endState = endState;
        this.symbolToWrite = symbolToWrite;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format("(q%d, %c) -> (q%d, %c, %s)", startingState, symbolToRead, endState, symbolToWrite, direction);
    }
}
