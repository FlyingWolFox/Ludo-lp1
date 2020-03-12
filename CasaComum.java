
public class CasaComum extends Casa {

    public CasaComum() {
        super();
    }

    public CasaComum(String cor) {
        super(cor);
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira, Dado[] dados) {
        if (castelo.getNivel() == 1)
            return super.getCasaSeguinte().proximaCasa(castelo, curupira, dados[0].getValor() + dados[1].getValor());
        return super.getCasaSeguinte().proximaCasa(castelo, curupira,
                Math.min(dados[0].getValor(), dados[1].getValor()));
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira, int casasAAndar) {
        if (casasAAndar > 0)
            return super.getCasaSeguinte().proximaCasa(castelo, curupira, casasAAndar - 1);
        return this;
    }
}