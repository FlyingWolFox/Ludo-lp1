
public class CasaSegura extends Casa {

    public CasaSegura(String cor, Casa anterior) {
        super(cor, anterior);
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira)
    {
        if(curupira)
        {
            if(super.getCasaAnterior() != null)
                return super.getCasaAnterior();
            else
            {
                curupira = false;
                return super.getCasaSeguinte();
            }
        }
        if(super.ehCasaFinal())
        {
            curupira = true;
            return super.getCasaAnterior();
        }
        return super.getCasaSeguinte();
    }
}