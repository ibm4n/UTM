/**
 * Element of {@link Band}:
 * Possesses reference to elements right and left.
 * Default value is '_' -> defining empty element
 */
public class BandElement {

    private char value;

    private BandElement left;
    private BandElement right;

    /**
     * Create new empty element (value = '_') for {@link Band}
     */
    public BandElement() {
        this.value = Band.EMPTY_ELEMENT;
    }


    public char getValue() {
        return this.value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public BandElement getRight() {
        return this.right;
    }

    public void setRightElement(BandElement element) {
        this.right = element;
    }

    public BandElement getLeft() {
        return this.left;
    }

    public void setLeftElement(BandElement element) {
        this.left = element;
    }
}
