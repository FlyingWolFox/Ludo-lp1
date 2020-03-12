import java.util.*;

/**
 * Representação do Jogador.
 * 
 * @author Lucas Isaac / lucaspissaia@cc.ci.ufpb.br
 */
public class Player {

    private int number; // número da vez do jogador, 0 - 3
    private ArrayList<Castelo> castelo; // peças que correspondem ao jogador
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
        this.castelo = new ArrayList<Castelo>();
        for (Casa casa : casaDaGuarita) {
            this.castelo.add(casa.getPeca());
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

    public ArrayList<Castelo> getCastelo(){
        return castelo;
    }

    /**
     * Verifica se a peça passada pertence ao jogador
     * 
     * @param castelo castelo a ser verificada
     * @return true se a peça for do jogador, false caso contrário
     */
    public boolean isThisPlayer(Castelo castelo) {
        return this.castelo.contains(castelo);
    }
}