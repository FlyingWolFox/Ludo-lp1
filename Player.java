import java.util.*;

/**
 * Representação do Jogador.
 * 
 * @author Lucas Isaac / lucaspissaia@cc.ci.ufpb.br
 */
public class Player {

    private int number; // número da vez do jogador, 0 - 3
    private ArrayList<Castelo> castelos; // peças que correspondem ao jogador
    private Guarita guarita;
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
        this.guarita = casaDaGuarita[0].getGuarita();
        this.castelos = new ArrayList<Castelo>();
        for (Casa casa : casaDaGuarita) {
            this.castelos.add(casa.getPeca());
            casa.getPeca().setPlayer(this);
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

    public ArrayList<Castelo> getCastelo() {
        return castelos;
    }

    /**
     * Verifica se a peça passada pertence ao jogador
     * 
     * @param castelo castelo a ser verificada
     * @return true se a peça for do jogador, false caso contrário
     */
    public boolean isThisPlayer(Castelo castelo) {
        return this.castelos.contains(castelo);
    }

    public Casa retornarGuaritaLivre() {
        for (Casa casa : guarita.getTodasAsCasas()) {
            if (!casa.possuiPeca())
                return casa;
        }

        // shall never be reached
        return null;
    }

    public void removerCastelo(Castelo castelo) {
        castelos.remove(castelo);
    }

    public void adicionarCastelo(Castelo castelo) {
        castelos.add(castelo);
    }
}
