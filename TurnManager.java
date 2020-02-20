
public class TurnManager {
    private int whoIsNow;

    public TurnManager() {
        whoIsNow = 1;
    }

    public int getWhoIsNow() {
        return whoIsNow;
    }

    public void next() {
        if (whoIsNow + 1 > 4)
            whoIsNow++;
        else
            whoIsNow = 1;
    }
}