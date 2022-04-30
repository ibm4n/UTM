import java.util.Arrays;

/**
 * "Legacy" version of TM band:
 * Only references the element under the reader.
 * Each element has a reference to element beforehand and element afterwards.
 * Can move one-step at the time left or right.
 * Has at least 31 elements and always 15 before element under the reader and 15 after (initialized as empty '_').
 * When moving, a new element will be created at the end of the moved direction (left/right), to ensure 15 elements
 * before or after reader.
 */
public class Band {

    /**
     * Default value for empty elements (special char)
     */
    public static final char EMPTY_ELEMENT = '_';

    /**
     * Default amount of elements before or after reader
     */
    private static final int DEFAULT_EMPTY_AMOUNT_SURROUNDING = 15;

    private final Character[] allowedCharacters;

    private BandElement elementOnReader;

    /**
     * Initializes new band with 31 empty elements and reader on the element in the middle (16th element)
     * @param allowedCharacters accepted characters for band
     */
    public Band(char[] allowedCharacters) {
        this.allowedCharacters = new String(allowedCharacters).chars()
                .mapToObj(c -> (char) c)
                .toArray(Character[]::new);

        initializeEmptyElements();
    }

    /**
     * Sets a value on the element under the reader
     * @param value : {@link Character}
     * @throws IllegalArgumentException when the given value is not allowed (defined in constructor)
     */
    public void setValue(char value) throws IllegalArgumentException {
        if(Arrays.stream(allowedCharacters).anyMatch(c -> value == c)) {
            elementOnReader.setValue(value);
        } else {
            throw new IllegalArgumentException(String.format("Character: %c, is not allowed for this machine", value));
        }
    }

    /**
     * Gets the value of element under reader
     * @return {@link Character} value
     */
    public char getValue() {
        return elementOnReader.getValue();
    }

    /**
     * Moves the reader to the next element in given direction
     * and adds an empty element in the same direction to ensure 15 elements surrounding reader
     * @param direction {@link BandDirections}
     */
    public void move(BandDirections direction) {
        switch (direction) {
            case R -> {
                elementOnReader = elementOnReader.getRight();
                addEmptyAtEnd(direction);
            }
            case L -> {
                elementOnReader = elementOnReader.getLeft();
                addEmptyAtEnd(direction);
            }
        }
    }

    /**
     * Creates new empty {@link BandElement} under reader
     * and 15 empty left
     * and 15 empty right
     */
    private void initializeEmptyElements() {
        // Middle element
        elementOnReader = new BandElement();
        // 15 before
        addEmptyFor(DEFAULT_EMPTY_AMOUNT_SURROUNDING, BandDirections.L);
        // 15 after
        addEmptyFor(DEFAULT_EMPTY_AMOUNT_SURROUNDING, BandDirections.R);
    }

    /**
     * Adds new empty {@link BandElement}s in a certain direction
     * @param amount of elements to create
     * @param direction direction to create
     */
    private void addEmptyFor(int amount, BandDirections direction) {
        BandElement current = elementOnReader;
        for(int i = 0; i < amount; i++) {
            BandElement next = null;
            switch (direction) {
                case R -> {
                    current.setRightElement(new BandElement());
                    next = current.getRight();
                    next.setLeftElement(current);
                }
                case L -> {
                    current.setLeftElement(new BandElement());
                    next = current.getLeft();
                    next.setRightElement(current);
                }
            }
            current = next;
        }
    }

    /**
     * Adds an empty element at the end of given direction if there are less than 15 elements after reader (count = 14)
     * @param direction to iterate until the end
     */
    private void addEmptyAtEnd(BandDirections direction) {
        BandElement current = elementOnReader;
        switch (direction) {
            case R -> {
                int count = 0;
                while(current.getRight() != null) {
                    current = current.getRight();
                    count++;
                }
                if(count == 14) {
                    BandElement next = new BandElement();
                    next.setLeftElement(current);
                    current.setRightElement(next);
                }
            }
            case L -> {
                int count = 0;
                while(current.getLeft() != null) {
                    current = current.getLeft();
                    count++;
                }
                if(count == 14) {
                    BandElement next = new BandElement();
                    next.setRightElement(current);
                    current.setLeftElement(next);
                }
            }
        }
    }

    /**
     * Gets the last possible element in given direction
     * @param direction {@link BandDirections} to go in
     * @return last {@link BandElement}
     */
    private BandElement getFurthestElement(BandDirections direction) {
        BandElement current = elementOnReader;
        switch (direction) {
            case R -> {
                while(current.getRight() != null) {
                    current = current.getRight();
                }
            }
            case L -> {
                while(current.getLeft() != null) {
                    current = current.getLeft();
                }
            }
        }

        return current;
    }

    /**
     * Creates two lines: one with a pointer on current element under the reader and one with the band values
     * @return Pointer for current element under reader and elements in band
     */
    @Override
    public String toString() {
        StringBuilder positionBuilder = new StringBuilder("  ");
        StringBuilder bandBuilder = new StringBuilder("| ");

        BandElement current = getFurthestElement(BandDirections.L);
        while(current != null) {
            // Reached element on reader
            if(elementOnReader.hashCode() == current.hashCode()) {
                positionBuilder.append("↓   ");
            } else {
                positionBuilder.append("    ");
            }

            bandBuilder.append(current.getValue()).append(" | ");
            current = current.getRight();
        }

        return positionBuilder + "\n" + bandBuilder;
    }


    /**
     * Directions that {@link Band}'s reader can move in
     * <ul>
     *     <li>RIGHT: R</li>
     *     <li>LEFT: L</li>
     * </ul>
     */
    public enum BandDirections {
        R,
        L
    }

}
