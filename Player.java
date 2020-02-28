import java.util.*;

/**
 * Representação do Jogador.
 * 
 * @author Lucas Isaac / lucaspissaia@cc.ci.ufpb.br
 */
public class Player {

    private int number; // número da vez do jogador, 0 - 3
    private ArrayList<Peca> pecas; // peças que correspondem ao jogador
    private String color; // cor da guarita

    /**
     * Construtor.
     * 
     * @param number        número do jogador
     * @param casaDaGuarita casas da guarita do Jogador
     * @param color         cor da guarita do jogador
     */
    public Player(int number, Casa[] casaDaGuarita, String color) {
        this.number = number;
        this.color = color;
        this.pecas = new ArrayList<Peca>();
        for (Casa casa : casaDaGuarita) {
            this.pecas.add(casa.getPeca());
        }
    }

    /**
     * Número do jogador
     * 
     * @return número do jogador
     */
    public int getNumber() {
        return number;
    }

    /**
     * Cor do jogador
     * 
     * @return cor do jogador
     */
    public String getColor() {
        return color;
    }

    /**
     * Verifica se a peça passada pertence ao jogador
     * 
     * @param peca peça a ser verificada
     * @return true se a peça for do jogador, false caso contrário
     */
    public boolean isThisPlayer(Peca peca) {
        return pecas.contains(peca);
    }
}