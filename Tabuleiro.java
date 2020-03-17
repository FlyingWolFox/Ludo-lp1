/**
 * Representa o tabuleiro do jogo.
 * 
 * @author Alan Moraes / alan@ci.ufpb.br
 * @author Victor Koehler / koehlervictor@cc.ci.ufpb.br
 */
import java.util.ArrayList;
public class Tabuleiro {

    // Armazena as casas de início das diferentes cores.
    private CasaComum casaInicioAmarelo;
    private CasaComum casaInicioAzul;
    private CasaComum casaInicioVerde;
    private CasaComum casaInicioVermelho;

    // Armazena as guaritas do tabuleiro.
    private Guarita guaritaAmarelo;
    private Guarita guaritaAzul;
    private Guarita guaritaVerde;
    private Guarita guaritaVermelho;

    private  ArrayList<Casa> casas_finais;

    /**
     * Construtor padrão de um tabuleiro.
     */
    public Tabuleiro() {

        // Casas de Inicio
        casaInicioAmarelo = new CasaComum("AMARELO");
        casaInicioAzul = new CasaComum("AZUL");
        casaInicioVerde = new CasaComum("VERDE");
        casaInicioVermelho = new CasaComum("VERMELHO");

        guaritaAmarelo = new Guarita("AMARELO", casaInicioAmarelo);
        guaritaAzul = new Guarita("AZUL", casaInicioAzul);
        guaritaVerde = new Guarita("VERDE", casaInicioVerde);
        guaritaVermelho = new Guarita("VERMELHO", casaInicioVermelho);

        casas_finais = new  ArrayList<Casa>();

        // Inicializamos um tabuleiro de Ludo

        // Casas comuns
        // Usaremos uma espécie de lista encadeada informal para guardar as casas.
        popularCasas(casaInicioAmarelo, casaInicioVerde);
        popularCasas(casaInicioVerde, casaInicioVermelho);
        popularCasas(casaInicioVermelho, casaInicioAzul);
        popularCasas(casaInicioAzul, casaInicioAmarelo);
    }

    /**
     * Método usado na construção do tabuleiro para popular as casas com for.
     * 
     * @param casa
     * @param ultimaCasa
     */
    private void popularCasas(Casa primeiraCasa, Casa ultimaCasa) {
        Casa casa = primeiraCasa;
        for (int i = 0; i < 12; i++) {
            Casa casaSeguinte = new CasaComum();
            casa.setCasaSeguinte(casaSeguinte);            

            if (i == 10) {
                casaSeguinte = new CasaEntrada(ultimaCasa.getCor());
                casa.setCasaSeguinte(casaSeguinte);
                casa = casaSeguinte;
                criarCasasZonaSegura(casa, ultimaCasa.getCor());
            }
            casa = casaSeguinte;
        }
        casa.setCasaSeguinte(ultimaCasa);
    }

    /**
     * Método usado na construção do tabuleiro para popular as casas seguras com
     * for.
     * 
     * @param casaEntradaZonaSegura
     * @param cor
     */
    private void criarCasasZonaSegura(Casa casaEntradaZonaSegura, String cor) {
        Casa casaZonaSegura = new CasaSegura(cor, null);
        casaEntradaZonaSegura.setCasaSegura(casaZonaSegura);
        for (int i = 0; i < 5; i++) {
            Casa casaNova = new CasaSegura(cor, casaZonaSegura);
            casaZonaSegura.setCasaSeguinte(casaNova);
            casaZonaSegura = casaNova;
        }
        casas_finais.add(casaZonaSegura);
    }

    /**
     * Obtém a guarita do jogador especificado. Consulte a classe Guarita para mais
     * detalhes.
     * 
     * @param cor Cor do jogador. Consulte a classe Cores.
     * @return Guarita do jogador
     */
    public Guarita getGuarita(String cor) {
        switch (cor) {
            case "AMARELO":
                return guaritaAmarelo;
            case "AZUL":
                return guaritaAzul;
            case "VERDE":
                return guaritaVerde;
            case "VERMELHO":
                return guaritaVermelho;
            default:
                return null;
        }
    }

    /**
     * Obtém a casa de início da cor especificada.
     * 
     * @param cor Cor do jogador. Consulte a classe Cores.
     * @return Casa de início.
     */
    public Casa getCasaInicio(String cor) {
        switch (cor) {
            case "AMARELO":
                return casaInicioAmarelo;
            case "AZUL":
                return casaInicioAzul;
            case "VERDE":
                return casaInicioVerde;
            case "VERMELHO":
                return casaInicioVermelho;
            default:
                return null;
        }
    }

    public ArrayList<Casa> getCasasFinais() {
        return casas_finais;
    }

    public boolean ehVitoria() {
        for (Casa casa : casas_finais) {
            if (casa.getPeca().getNivel() == 4)
                return true;
        }
        return false;
    }
}
