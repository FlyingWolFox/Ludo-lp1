


public class CasaSegura extends Casa {
    private int movimentos;
    
    public CasaSegura(String cor, Casa anterior) {
        super(cor, anterior);
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira, Dado[] dados) {
        if(castelo.getNivel() == 1)
            movimentos = dados[0].getValor() + dados[1].getValor();
        else
            movimentos = Math.min(dados[0].getValor(), dados[1].getValor());
        
        if (curupira) {
            if (super.getCasaAnterior() != null)
                return super.getCasaAnterior().proximaCasa(castelo, curupira, movimentos - 1);
            else {
                curupira = false;
                return super.getCasaSeguinte().proximaCasa(castelo, curupira, movimentos - 1);
            }
        }

        if (super.ehCasaFinal()) {
            return this;
        }

        return super.getCasaSeguinte().proximaCasa(castelo, curupira, movimentos - 1);
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira, int casasAAndar)
    {
        if (casasAAndar > 0)
        {
            if (curupira) {
                if (super.getCasaAnterior() != null)
                    return super.getCasaAnterior().proximaCasa(castelo, curupira, casasAAndar - 1);
                else {
                    curupira = false;
                    return super.getCasaSeguinte().proximaCasa(castelo, curupira, casasAAndar - 1);
                }
            }
    
            if (super.ehCasaFinal()) {
                curupira = true;
                return super.getCasaAnterior().proximaCasa(castelo, curupira, casasAAndar - 1);
            }
        }

        return this;
    }
}