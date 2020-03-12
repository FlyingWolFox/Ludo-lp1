import java.util.ArrayList;

public class Castelo {

    // Cor da peça
    private final String cor;

    // Casa na qual a peça se encontra
    private Casa casa;

    private int nivel;

    public Castelo(String cor) {
        this.cor = cor;
        this.casa = null;
        this.nivel = 1;
    }

    public void adicionarPeca() {
        nivel++;
    }

    /**
     * Cor da peça.
     * 
     * @return Cor da peça.
     */
    public String getCor() {
        return cor;
    }

    /**
     * Casa na qual a peça se encontra.
     * 
     * @return Casa na qual a peça se encontra.
     */
    public Casa getCasa() {
        return casa;
    }

    /**
     * Retira a peça da casa atual e coloca-a na casa de destino.
     * 
     * @param casaDestino
     */
    public void mover(Casa casaDestino) {
        if (casa != null) {
            casa.setCastelo(null);
        }
        casaDestino.setCastelo(this);
        casa = casaDestino;
    }

    public int getNivel() {
        return nivel;
    }
}