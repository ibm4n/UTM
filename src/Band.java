import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Band {

    private List<Character> bandContent = new ArrayList<>();
    private int readerPosition;
    public static final char EMPTY_ELEMENT = '_';
    private static final int DEFAULT_EMPTY_AMOUNT_SURROUNDING = 15;
    private static final int DEFAULT_BAND_ENTRY_LENGTH = 4;

    public Band(List<Character> bandContent) {
        this.bandContent = bandContent;
        addEmptys();
        this.readerPosition = DEFAULT_EMPTY_AMOUNT_SURROUNDING + 1;
    }

    public void move(Direction dir) {
        switch (dir) {
            case L -> moveLeft();
            case R -> moveRight();
        }
    }

    public int countRemainingSymbols() {
        int count = 0;
        for (char symbol : bandContent) {
            if (symbol != EMPTY_ELEMENT)
                count++;
        }
        return count;
    }

    public enum Direction {
        L, R
    }

    private void moveRight() {
        readerPosition += 1;
        List<Character> rightSide = bandContent.subList(readerPosition, bandContent.size());
        if (rightSide.size() <= 15) {
            addEmptyRight();
        }
    }

    private void moveLeft() {
        List<Character> leftSize = bandContent.subList(0, readerPosition);
        if (leftSize.size() <= 15) {
            addEmptyLeft();
        } else {
            readerPosition -= 1;
        }
    }

    private void addEmptyLeft() {
        this.bandContent.add(0, EMPTY_ELEMENT);
    }

    private void addEmptyRight() {
        this.bandContent.add(EMPTY_ELEMENT);
    }

    public Character getSymbolAtCurrentPosition() {
        return bandContent.get(readerPosition);
    }


    public void replaceSymbolAtCurrentPosition(char newSymbol) {
        bandContent.set(readerPosition, newSymbol);
    }


    public void addEmptys() {
        List<Character> emptys = new ArrayList<>();
        for (int i = 0; i <= DEFAULT_EMPTY_AMOUNT_SURROUNDING; i++) {
            emptys.add(EMPTY_ELEMENT);
        }
        List<Character> newList = Stream.of(emptys, bandContent, emptys)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        this.bandContent = newList;
    }


    public String toString() {

        StringBuilder bandBuilder = new StringBuilder("| ");
        StringBuilder readerBuilder = new StringBuilder("=");
        StringBuilder borderBuilder = new StringBuilder("=");

        for (Character symbol : bandContent) {
            bandBuilder.append(symbol).append(" | ");

            borderBuilder.append("====");
            readerBuilder.append("====");

        }
        readerBuilder.setCharAt(readerPosition * DEFAULT_BAND_ENTRY_LENGTH + 2, '▲');
        borderBuilder.setCharAt(readerPosition * DEFAULT_BAND_ENTRY_LENGTH + 2, '▼');

        return borderBuilder + "\n" + bandBuilder + "\n" + readerBuilder + "\n";

    }

}
