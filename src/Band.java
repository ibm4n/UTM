import java.util.ArrayList;
import java.util.List;

public class Band {

    private List<Character> bandContent = new ArrayList<>();
    private int readerPosition;


    public Band(List<Character> bandContent) {
        this.bandContent = bandContent;
        this.readerPosition = 0;
    }


}
