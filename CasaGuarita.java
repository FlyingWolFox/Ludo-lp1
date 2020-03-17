
public class CasaGuarita extends Casa {

    public CasaGuarita(Guarita guarita) {
        super(guarita);
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira, Dado[] dados) {
        if(dados[0].getValor() == dados[1].getValor())
            return super.getGuarita().getCasaInicio();
        return this;
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira, int casasAAndar)
    {
        // shall never be reached
        return this;
    }
}