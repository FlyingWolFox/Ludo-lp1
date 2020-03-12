
public class CasaEntrada extends Casa {

    private String corDaEntrada;

    public CasaEntrada(String cor, Casa anterior) {
        super(cor, anterior);
        corDaEntrada = super.getCasaSegura().getCor();
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira)
    {
        if(castelo.getCor() == corDaEntrada)
            return super.getCasaSegura();
        return super.getCasaSeguinte();
    }
}