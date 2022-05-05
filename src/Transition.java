public class Transition {
    private final int actualState;
    private final char symbolToRead;
    private final int nextState;
    private final char symbolToWrite;
    private final int direction;

    public Transition(int actualState, int input, int nextState, int charToWrite, int direction) {
        this.actualState = actualState;


        switch (input) {
            case 1 -> this.symbolToRead = '0';
            case 2 -> this.symbolToRead = '1';
            case 4 -> this.symbolToRead = 'X';
            case 5 -> this.symbolToRead = 'Y';
            default -> this.symbolToRead = '_';
        }
        switch (charToWrite) {
            case 1 -> this.symbolToWrite = '0';
            case 2 -> this.symbolToWrite = '1';
            case 4 -> this.symbolToWrite = 'X';
            case 5 -> this.symbolToWrite = 'Y';
            default -> this.symbolToWrite = '_';
        }

        this.nextState = nextState;
        this.direction = direction;
    }

    public Band.Direction getDirection() {
        if (direction == 2) {
            return Band.Direction.R;
        }
        return Band.Direction.L;
    }

    public int getTransitionId() {
        return actualState;
    }

    public int getNextState() {
        return nextState;
    }

    public Character getSymbolToWrite() {
        return symbolToWrite;
    }

    public Character getSymbolToRead() {
        return symbolToRead;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(q").append(actualState).append(",").append(symbolToRead).append(")").append(" -> ")
                .append("(q").append(nextState).append(",").append(symbolToWrite).append(",").append(getDirection()).append(")");
        return sb.toString();
    }
}
