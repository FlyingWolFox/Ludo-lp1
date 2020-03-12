
public class CasaGuarita extends Casa {

    public CasaGuarita() {
        super();
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira)
    {
        return super.getGuarita().getCasaInicio();
    }
}