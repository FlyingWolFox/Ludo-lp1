import java.util.*;

/**
 * Implementa as mecânicas e regras do jogo Ludo.
 *
 * @author Alan Moraes / alan@ci.ufpb.br
 * @author Victor Koehler / koehlervictor@cc.ci.ufpb.br
 */
public class Jogo {

    // Tabuleiro do jogo
    private final Tabuleiro tabuleiro;

    // Dados do jogo.
    private final Dado[] dados;

    private boolean dadosRolados = false;
    private ArrayList<Player> players;
    private TurnManager turnManager;

    /**
     * Construtor padrão do Jogo Ludo. Isto é, um jogo de Ludo convencional com dois
     * dados.
     */
    public Jogo() {
        this(2);
    }

    /**
     * Construtor do Jogo Ludo para inserção de um número arbitrário de dados.
     * 
     * @param numeroDados Número de dados do jogo.
     */
    public Jogo(int numeroDados) {
        this.tabuleiro = new Tabuleiro();
        this.dados = new Dado[numeroDados];

        for (int i = 0; i < this.dados.length; i++) {
            // remover parâmetro do construtor para dado não batizado
            this.dados[i] = new Dado(i);
        }

        inicializaJogo();
    }

    /**
     * Construtor do Jogo Ludo para inserção de dados arbitrários. Útil para inserir
     * dados "batizados" e fazer testes.
     * 
     * @param dados Dados
     */
    public Jogo(Dado[] dados) {
        this.tabuleiro = new Tabuleiro();
        this.dados = dados;
        assert dados.length > 0; // TO BE REMOVED

        inicializaJogo();
    }

    private void inicializaJogo() {

        // AQUI SUGERE-SE QUE SE INSIRA A INICIALIZAÇÃO DO JOGO
        // ISTO É, A INSERÇÃO DAS PEÇAS NO TABULEIRO E AS DEFINIÇÕES DOS CAMPOS
        // SE NECESSÁRIO, MODIFIQUE A ASSINATURA DO MÉTODO

        Guarita guaritaVermelha;
        Guarita guaritaVerde;
        Guarita guaritaAzul;
        Guarita guaritaAmarela;

        guaritaVermelha = tabuleiro.getGuarita("VERMELHO");
        guaritaVerde = tabuleiro.getGuarita("VERDE");
        guaritaAzul = tabuleiro.getGuarita("AZUL");
        guaritaAmarela = tabuleiro.getGuarita("AMARELO");

        // Vamos inicializar a guarita verde colocando as 4 peças do jogador verde nela.
        //
        // Guarita = espaço onde fica as peças fora do jogo;

        for (Casa casaGuarita : guaritaVermelha.getTodasAsCasas()) {
            Peca novaPeca = new Peca("VERMELHO");
            novaPeca.mover(casaGuarita);
        }

        for (Casa casaGuarita : guaritaVerde.getTodasAsCasas()) {
            Peca novaPeca = new Peca("VERDE");
            novaPeca.mover(casaGuarita);
        }

        for (Casa casaGuarita : guaritaAzul.getTodasAsCasas()) {
            Peca novaPeca = new Peca("AZUL");
            novaPeca.mover(casaGuarita);
        }

        for (Casa casaGuarita : guaritaAmarela.getTodasAsCasas()) {
            Peca novaPeca = new Peca("AMARELO");
            novaPeca.mover(casaGuarita);
        }

        Player playerVerde = new Player(1, guaritaVerde.getTodasAsCasas(), "VERDE");
        Player playerVermelho = new Player(2, guaritaVermelha.getTodasAsCasas(), "VERMELHO");
        Player playerAzul = new Player(3, guaritaAzul.getTodasAsCasas(), "AZUL");
        Player playerAmarelo = new Player(4, guaritaAmarela.getTodasAsCasas(), "AMARELO");

        players.add(playerVerde);
        players.add(playerVermelho);
        players.add(playerAzul);
        players.add(playerAmarelo);

        turnManager = new TurnManager();

        //
        // TRECHO DE EXEMPLO
        //

        // Obtemos uma das peças verdes que inicializamos logo acima para usa-la como
        // exemplo.
        // Movemos ela para a casa de inicio do jogador verde.
        Casa casaGuarita;
        Casa casaInicio;
        Peca peca;

        guarita = tabuleiro.getGuarita("VERDE");
        casaGuarita = guarita.getCasa(0);
        peca = casaGuarita.getPeca();
        casaInicio = tabuleiro.getCasaInicio("VERDE");
        peca.mover(casaInicio);

        // Apenas como um exemplo adicional, colocamos uma peça azul no tabuleiro.
        peca = new Peca("AZUL");
        casaInicio = tabuleiro.getCasaInicio("AZUL");
        peca.mover(casaInicio);

        //
        // TRECHO DE EXEMPLO
        //
    }

    /**
     * Método invocado pelo usuário através da interface gráfica ou da linha de
     * comando para jogar os dados. Aqui deve-se jogar os dados e fazer todas as
     * verificações necessárias.
     */
    public void rolarDados() {

        // AQUI SE IMPLEMENTARÁ AS REGRAS DO JOGO.
        // TODA VEZ QUE O USUÁRIO CLICAR NO DADO DESENHADO NA INTERFACE GRÁFICA,
        // ESTE MÉTODO SERÁ INVOCADO.

        //
        // TRECHO DE EXEMPLO
        //

        // Aqui percorremos cada dado para lançá-lo individualmente.

        if (!dadosRolados) {
            for (Dado dado : dados) {
                dado.rolar();
            }
            dadosRolados = true;
        }
    }

    /**
     * Método invocado pelo usuário através da interface gráfica ou da linha de
     * comando quando escolhida uma peça. Aqui deve-se mover a peça e fazer todas as
     * verificações necessárias.
     * 
     * @param casa Casa escolhida pelo usuário/jogador.
     */
    public void escolherCasa(Casa casa) {

        // AQUI SE IMPLEMENTARÁ AS REGRAS DO JOGO.
        // TODA VEZ QUE O USUÁRIO CLICAR EM UMA CASA DESENHADA NA INTERFACE GRÁFICA,
        // ESTE MÉTODO SERÁ INVOCADO.

        //
        // TRECHO DE EXEMPLO
        //

        // Perguntamos à casa se ela possui uma peça.
        // Se não possuir, não há nada para se fazer.
        if (!casa.possuiPeca() || dadosRolados) {
            return;
        }

        // Perguntamos à casa qual é a peça.
        Peca peca = casa.getPeca();
        Player player = players.get(turnManager.getWhoIsNow());
        if (player.isThisPlayer(peca)) {

            // Percorremos cada dado, somando o valor nele à variável somaDados.
            int somaDados = 0;
            for (Dado dado : dados) {
                somaDados += dado.getValor();
            }

            // Percorreremos N casas.
            Casa proximaCasa = casa;
            for (int i = 0; i < somaDados && proximaCasa != null; i++) {
                proximaCasa = proximaCasa.getCasaSeguinte();
            }

            for (boolean moved = false; !moved;) {

                if (proximaCasa != null) {
                    // Finalmente, a variável casaN contém a casa que a peça deve ser inserida.
                    peca.mover(proximaCasa);
                    dadosRolados = false;
                    moved = true;
                } else {
                    // // NÃO HÁ PRÓXIMA CASA!
                    // // FIM DO JOGO? A PEÇA ESTÁ NA GUARITA?
                    // // Descomente a próxima linha para ser notificado quando isso acontecer:
                    System.err.println("Não há próxima casa!");

                    // // Descomente as duas próximas linhas para verificar se a peça está na
                    // guarita:
                    if (casa.pertenceGuarita())
                        System.out.println("A peça está na guarita");
                }
            }

            turnManager.next();
        }
    }

    /**
     * Retorna o jogador que deve jogar os dados ou escolher uma peça.
     * 
     * @return Cor do jogador.
     */
    public String getJogadorDaVez() {
        Player player = players.get(turnManager.getWhoIsNow());
        return player.getColor();
    }

    /**
     * O tabuleiro deste jogo.
     * 
     * @return O tabuleiro deste jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     * Retorna o i-ésimo dado deste jogo entre 0 (inclusivo) e N (exclusivo).
     * Consulte getQuantidadeDeDados() para verificar o valor de N (isto é, a
     * quantidade de dados presentes).
     * 
     * @param i Indice do dado.
     * @return O i-ésimo dado deste jogo.
     */
    public Dado getDado(int i) {
        return dados[i];
    }

    /**
     * Obtém a quantidade de dados sendo usados neste jogo.
     * 
     * @return Quantidade de dados.
     */
    public int getQuantidadeDados() {
        return dados.length;
    }
}
