import java.util.*;

public class Player {

    private int number;
    private ArrayList<Peca> pecas;
    private String color;

    public Player(int number, Casa[] casaDaGuarita, String color) {
        this.number = number;
        this.color = color;
        this.pecas = new ArrayList<Peca>();
        for (Casa casa : casaDaGuarita) {
            this.pecas.add(casa.getPeca());
        }
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public boolean isThisPlayer(Peca peca) {
        return pecas.contains(peca);
    }
}