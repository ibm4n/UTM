package legacy;

/**
 * Element of {@link LegacyBand}:
 * Possesses reference to elements right and left.
 * Default value is '_' -> defining empty element
 */
public class LegacyBandElement {

    private char value;

    private LegacyBandElement left;
    private LegacyBandElement right;

    /**
     * Create new empty element (value = '_') for {@link LegacyBand}
     */
    public LegacyBandElement() {
        this.value = LegacyBand.EMPTY_ELEMENT;
    }


    public char getValue() {
        return this.value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public LegacyBandElement getRight() {
        return this.right;
    }

    public void setRightElement(LegacyBandElement element) {
        this.right = element;
    }

    public LegacyBandElement getLeft() {
        return this.left;
    }

    public void setLeftElement(LegacyBandElement element) {
        this.left = element;
    }
}
