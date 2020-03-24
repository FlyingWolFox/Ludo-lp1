
public class Castelo {

    // Cor da peça
    private final String cor;

    // Casa na qual a peça se encontra
    private Casa casa;

    private Player player;
    private int nivel;

    public Castelo(String cor) {
        this.cor = cor;
        this.casa = null;
        this.nivel = 1;
    }

    public Castelo(String cor, Player player) {
        this.cor = cor;
        this.casa = null;
        this.nivel = 1;
        this.player = player;
        player.adicionarCastelo(this);
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

        if (casaDestino.possuiPeca() && !this.equals(casaDestino.getPeca())) {
            Castelo casteloCasa = casaDestino.getPeca();
            if (!player.isThisPlayer(casteloCasa)) {
                casteloCasa.captured();
            }

            if (player.isThisPlayer(casteloCasa)) {
                player.removerCastelo(casteloCasa);
                nivel += casteloCasa.getNivel();
            }

        }

        casaDestino.setCastelo(this);
        casa = casaDestino;
    }

    public int getNivel() {
        return nivel;
    }

    public Castelo levelUP() {
        nivel++;
        return this;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void captured() {
        for (int i = 1; i < nivel; i++) {
            new Castelo(cor, player).mover(player.retornarGuaritaLivre());
        }
        this.nivel = 1;
        this.mover(player.retornarGuaritaLivre());
    }
}
