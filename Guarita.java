import java.util.Arrays;

/**
 *
 * Implementa a guarita, um espaço reservado as peças fora do jogo.
 * 
 * @author Alan Moraes / alan@ci.ufpb.br
 * @author Victor Koehler / koehlervictor@cc.ci.ufpb.br
 */
public class Guarita {
    private final CasaGuarita[] casas;
    private final String cor;
    private final CasaComum casaInicio;

    /**
     * Inicializa a guarita vazia.
     * 
     * @param cor Cor
     */
    public Guarita(String cor, CasaComum casaInicio) {
        this.cor = cor;
        this.casaInicio = casaInicio;
        this.casas = new CasaGuarita[] { new CasaGuarita(this), new CasaGuarita(this), new CasaGuarita(this),
                new CasaGuarita(this) };
    }

    /**
     * Obtém a casa associada a esta guarita de acordo com o índice especificado.
     * 
     * @param indice Índice da casa, entre 0 (inclusivo) e 4 (exclusivo).
     * @return Casa
     */
    public Casa getCasa(int indice) {
        return casas[indice];
    }

    /**
     * Obtém o número de peças presentes nas casas da guarita.
     * 
     * @return Número de peças
     */
    public int getNumeroDePecas() {
        int s = 0;
        for (Casa casa : casas) {
            if (casa.possuiPeca()) {
                s++;
            }
        }
        return s;
    }

    /**
     * Obtém uma cópia da lista de casas desta guarita. Útil para iterar sobre as
     * casas usando o for(each).
     * 
     * @return Array contendo as casas.
     */
    public CasaGuarita[] getTodasAsCasas() {
        return Arrays.copyOf(casas, casas.length);
    }

    /**
     * @return A cor da guarita
     */
    public String getCor() {
        return cor;
    }

    public Casa getCasaInicio() {
        return casaInicio;
    }
}
