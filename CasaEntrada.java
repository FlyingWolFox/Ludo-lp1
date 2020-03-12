
public class CasaEntrada extends Casa {

    private String corDaEntrada;

    public CasaEntrada(String corDaZonaSegura) {
        super();
        corDaEntrada = corDaZonaSegura;
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira) {
        if (castelo.getCor() == corDaEntrada)
            return super.getCasaSegura();
        return super.getCasaSeguinte();
    }
}