
public class CasaGuarita extends Casa {

    public CasaGuarita(Guarita guarita) {
        super(guarita);
    }

    public Casa proximaCasa(Castelo castelo, boolean curupira) {
        return super.getGuarita().getCasaInicio();
    }
}