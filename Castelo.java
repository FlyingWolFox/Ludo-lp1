import java.util.ArrayList;

public class Castelo extends Peca {

    ArrayList<Peca> pecas;

    public Castelo(Peca peca1, Peca peca2, String cor) {
        super(cor);
        this.pecas = new ArrayList<Peca>();
        pecas.add(peca1);
        pecas.add(peca1);
    }

    public void adicionarPeca(Peca peca)
    {
        pecas.add(peca);
    }

    public void mover(Casa casaDestino)
    {
        for (Peca peca : pecas)
        {
            peca.mover(casaDestino);
        }
        super.mover(casaDestino);
    }
}