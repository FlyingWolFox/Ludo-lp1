public class CasaComum extends Casa {

	private int movimentos;

	public CasaComum() {
		super();
	}

	public CasaComum(String cor) {
		super(cor);
	}

	public Casa proximaCasa(Castelo castelo, boolean curupira, Dado[] dados) {
		if (castelo.getNivel() == 1)
			movimentos = dados[0].getValor() + dados[1].getValor();
		else
			movimentos = Math.min(dados[0].getValor(), dados[1].getValor());

		return super.getCasaSeguinte().proximaCasa(castelo, curupira, movimentos - 1);
	}

	public Casa proximaCasa(Castelo castelo, boolean curupira, int casasAAndar) {

		if (this.possuiPeca()) {
			if (this.getPeca().getNivel() > castelo.getNivel() && this.getPeca().getCor() != castelo.getCor())
				return super.getCasaAnterior();
		}

		if (casasAAndar > 0)
			return super.getCasaSeguinte().proximaCasa(castelo, curupira, casasAAndar - 1);

		return this;

	}
}
