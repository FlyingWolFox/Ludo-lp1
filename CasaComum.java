
public class CasaComum extends Casa {

    public CasaComum() {
        super();
    }

    public CasaComum(String cor) {
        super(cor);
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira) {
        return super.getCasaSeguinte();
    }
}