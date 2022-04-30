package legacy;

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
public class LegacyBand {

    /**
     * Default value for empty elements (special char)
     */
    public static final char EMPTY_ELEMENT = '_';

    /**
     * Default amount of elements before or after reader
     */
    private static final int DEFAULT_EMPTY_AMOUNT_SURROUNDING = 15;

    private final Character[] allowedCharacters;

    private LegacyBandElement elementOnReader;

    /**
     * Initializes new band with 31 empty elements and reader on the element in the middle (16th element)
     * @param allowedCharacters accepted characters for band
     */
    public LegacyBand(char[] allowedCharacters) {
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
            throw new IllegalArgumentException(String.format("Character not allowed %c", value));
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
     * Creates new empty {@link LegacyBandElement} under reader
     * and 15 empty left
     * and 15 empty right
     */
    private void initializeEmptyElements() {
        // Middle element
        elementOnReader = new LegacyBandElement();
        // 15 before
        addEmptyFor(DEFAULT_EMPTY_AMOUNT_SURROUNDING, BandDirections.L);
        // 15 after
        addEmptyFor(DEFAULT_EMPTY_AMOUNT_SURROUNDING, BandDirections.R);
    }

    /**
     * Adds new empty {@link LegacyBandElement}s in a certain direction
     * @param amount of elements to create
     * @param direction direction to create
     */
    private void addEmptyFor(int amount, BandDirections direction) {
        LegacyBandElement current = elementOnReader;
        for(int i = 0; i < amount; i++) {
            switch (direction) {
                case R -> {
                    current.setRightElement(new LegacyBandElement());
                    current = current.getRight();
                }
                case L -> {
                    current.setLeftElement(new LegacyBandElement());
                    current = current.getLeft();
                }
            }
        }
    }

    /**
     * Adds an empty element at the end of given direction
     * @param direction to iterate until the end
     */
    private void addEmptyAtEnd(BandDirections direction) {
        switch (direction) {
            case R -> getFurthestElement(BandDirections.R).setRightElement(new LegacyBandElement());
            case L -> getFurthestElement(BandDirections.L).setLeftElement(new LegacyBandElement());
        }
    }

    /**
     * Gets the last possible element in given direction
     * @param direction {@link BandDirections} to go in
     * @return last {@link LegacyBandElement}
     */
    private LegacyBandElement getFurthestElement(BandDirections direction) {
        LegacyBandElement current = elementOnReader;
        switch (direction) {
            case R -> {
                while(current.getRight() != null) { current = elementOnReader.getRight(); }
            }
            case L -> {
                while(current.getLeft() != null) { current = elementOnReader.getLeft(); }
            }
        }

        return current;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        StringBuilder positionBuilder = new StringBuilder(" ");
        StringBuilder bandBuilder = new StringBuilder("|");

        LegacyBandElement current = getFurthestElement(BandDirections.L);
        while(current != null) {
            // Reached element on reader
            if(elementOnReader.hashCode() == current.hashCode()) {
                positionBuilder.append("↓ ");
            } else {
                positionBuilder.append("  ");
            }

            bandBuilder.append(current.getValue()).append("|");
            current = current.getRight();
        }
        bandBuilder.append("|");

        return positionBuilder + "\n" + bandBuilder;
    }


    /**
     * Directions that {@link LegacyBand}'s reader can move in
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
