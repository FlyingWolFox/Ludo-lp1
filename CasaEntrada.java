
public class CasaEntrada extends Casa {

    private String corDaEntrada;

    public CasaEntrada() {
        super();
        corDaEntrada = super.getCasaSegura().getCor();
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira) {
        if (castelo.getCor() == corDaEntrada)
            return super.getCasaSegura();
        return super.getCasaSeguinte();
    }
}