public class Transition {
    private final int actualState;
    private final int symbolsToRead;
    private final int nextState;
    private final int symbolsToWrite;


    private final int direction;

    public Transition(int actualState, int input, int nextState, int charToWrite, int direction) {
        this.actualState = actualState;
        this.symbolsToRead = input - 1;
        this.nextState = nextState;
        this.symbolsToWrite = charToWrite -1;
        this.direction = direction;
    }

    public String getDirection() {
        if (direction == 1) {
            return "L";
        }
        return "R";
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(q").append(actualState).append(",").append(symbolsToRead).append(")").append(" -> ")
                .append("(q").append(nextState).append(",").append(symbolsToWrite).append(",").append(getDirection()).append(")");
        return sb.toString();
    }
}
