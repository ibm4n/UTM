public class Transition {
    private final int actualState;
    private final char symbolsToRead;
    private final int nextState;
    private final char symbolsToWrite;


    private final int direction;

    public Transition(int actualState, int input, int nextState, int charToWrite, int direction) {
        this.actualState = actualState;


        switch (input) {
            case 1 -> this.symbolsToRead = '0';
            case 2 -> this.symbolsToRead = '1';
            default -> this.symbolsToRead = '_';
        }
        switch (charToWrite) {
            case 1 -> this.symbolsToWrite = '0';
            case 2 -> this.symbolsToWrite = '1';
            default -> this.symbolsToWrite = '_';
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
        return symbolsToWrite;
    }

    public Character getSymbolToRead() {
        return symbolsToRead;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(q").append(actualState).append(",").append(symbolsToRead).append(")").append(" -> ")
                .append("(q").append(nextState).append(",").append(symbolsToWrite).append(",").append(getDirection()).append(")");
        return sb.toString();
    }
}
