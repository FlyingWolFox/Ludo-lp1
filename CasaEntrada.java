
public class CasaEntrada extends Casa {

    private String corDaEntrada;

    public CasaEntrada(String corDaZonaSegura) {
        super();
        corDaEntrada = corDaZonaSegura;
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira, Dado[] dados) {
        if (castelo.getCor() == corDaEntrada)
            return super.getCasaSegura();
        return super.getCasaSeguinte();
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira, int casasAAndar)
    {
        
    }
}