import java.util.ArrayList;

public class Castelo {

    // Cor da peça
    private final String cor;

    // Casa na qual a peça se encontra
    private Casa casa;

    private ArrayList<Castelo> castelo;

    public Castelo(String cor) {
        this.cor = cor;
        this.casa = null;
        this.castelo = new ArrayList<Castelo>();
    }

    public void adicionarPeca(Castelo peca) {
        castelo.add(peca);
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
    public void moverPeca(Casa casaDestino) {
        if (casa != null) {
            casa.setCastelo(null);
        }
        casaDestino.setCastelo(this);
        casa = casaDestino;
    }

    public void mover(Casa casaDestino) {
        for (Castelo peca : castelo) {
            peca.moverPeca(casaDestino);
        }
        this.casa = casaDestino;
    }

    public int getQtDePecas() {
        return castelo.size();
    }
}