import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Implementa as mecânicas e regras do jogo Ludo.
 *
 * @author Alan Moraes / alan@ci.ufpb.br
 * @author Victor Koehler / koehlervictor@cc.ci.ufpb.br
 * @author Lucas Isaac / lucaspissaia@cc.ci.ufpb.br
 * @author Luciano Pereira / lucianofilho@cc.ci.ufpb.br
 */
public class Jogo {

	// Tabuleiro do jogo
	private final Tabuleiro tabuleiro;

	// Dados do jogo.
	private final Dado[] dados;

	private boolean dadosRolados = false; // guardam se os dados já foram rolados no turno
	private boolean win = false; // guarda se o jogo foi ganho
	private ArrayList<Player> players; // possui os jogadores da partida
	private TurnManager turnManager; // gerenciador de turnos

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

		// Inicialização do Jogo

		// Ininicialização das guaritas
		Guarita guaritaVermelha;
		Guarita guaritaVerde;
		Guarita guaritaAzul;
		Guarita guaritaAmarela;

		// atribui as guaritas do tabulheiro às suas respectivas guaritas
		guaritaVermelha = tabuleiro.getGuarita("VERMELHO");
		guaritaVerde = tabuleiro.getGuarita("VERDE");
		guaritaAzul = tabuleiro.getGuarita("AZUL");
		guaritaAmarela = tabuleiro.getGuarita("AMARELO");

		// Colocando as peças nas suas respectivas guaritas
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

		// Cria-se os jogadores
		Player playerVermelho = new Player(0, guaritaVermelha.getTodasAsCasas(), "VERMELHO");
		Player playerAzul = new Player(1, guaritaAzul.getTodasAsCasas(), "AZUL");
		Player playerAmarelo = new Player(2, guaritaAmarela.getTodasAsCasas(), "AMARELO");
		Player playerVerde = new Player(3, guaritaVerde.getTodasAsCasas(), "VERDE");

		// adiciona os jogadores a um ArrayList para melhor controle
		players = new ArrayList<Player>();
		players.add(playerVermelho);
		players.add(playerAzul);
		players.add(playerAmarelo);
		players.add(playerVerde);

		// cria-se o gerenciador de turnos
		turnManager = new TurnManager();
	}

	/**
	 * Método invocado pelo usuário através da interface gráfica ou da linha de
	 * comando para jogar os dados. Aqui deve-se jogar os dados e fazer todas as
	 * verificações necessárias.
	 */
	public void rolarDados() {

		// TODA VEZ QUE O USUÁRIO CLICAR NO DADO DESENHADO NA INTERFACE GRÁFICA,
		// ESTE MÉTODO SERÁ INVOCADO.

		// dados são rolados, há não ser que já foram rolados no atual turno
		if (!dadosRolados && !win) {
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

		// TODA VEZ QUE O USUÁRIO CLICAR EM UMA CASA DESENHADA NA INTERFACE GRÁFICA,
		// ESTE MÉTODO SERÁ INVOCADO.

		// Perguntamos à casa se ela possui uma peça e
		// Vemos se os dados foram rolados no turno atual
		// Se não possuir ou os dados não foram rolados, não há nada para se fazer.
		if (!casa.possuiPeca() || !dadosRolados || win) {
			return;
		}

		// Perguntamos à casa qual é a peça.
		Peca peca = casa.getPeca();
		// Pegamos o jogador da vez
		Player player = players.get(turnManager.getWhoIsNow());
		// verificamos se a peça perternce ao jogador da vez
		// se não for, a peça não se move e o jogo não continua
		if (player.isThisPlayer(peca)) {

			// Percorremos cada dado, somando o valor nele à variável somaDados.
			int somaDados = 0;
			for (Dado dado : dados) {
				somaDados += dado.getValor();
			}

			// Percorreremos N casas.
			Casa proximaCasa = casa;
			boolean curupira = false;
			for (int i = 0; i < somaDados && proximaCasa != null; i++) {
				if (proximaCasa.ehEntradaZonaSegura() && (proximaCasa.getCasaSegura().getCor() == peca.getCor()))
					proximaCasa = proximaCasa.getCasaSegura();
				else if (proximaCasa.ehCasaFinal()) {
					if (i == 0) {
						proximaCasa = null;
						break;
					}
					curupira = true;
					proximaCasa = proximaCasa.getCasaAnterior();
				} else if (curupira && proximaCasa.getCasaAnterior() != null)
					proximaCasa = proximaCasa.getCasaAnterior();
				else if (curupira && proximaCasa.getCasaAnterior() == null) {
					proximaCasa = proximaCasa.getCasaSeguinte();
					curupira = false;
				} else
					proximaCasa = proximaCasa.getCasaSeguinte();

			}

			// o jogo não continua a execução enquanto uma peça
			// do jogador da vez não se mecheu (moved == true)
			for (boolean moved = false; !moved;) {

				// se a próxima casa não for nula, mova a peça até lá
				if (proximaCasa != null) {
					if (!proximaCasa.ehCasaFinal()) {
						// Finalmente, a variável casaN contém a casa que a peça deve ser inserida.
						Peca pecaProxima = proximaCasa.getPeca();
						if (pecaProxima != null) {
							if (!player.isThisPlayer(pecaProxima)) {
								Guarita pecaRetorno = tabuleiro.getGuarita(pecaProxima.getCor());
								for (Casa casaGuarita : pecaRetorno.getTodasAsCasas()) {
									if (!casaGuarita.possuiPeca()) {
										pecaProxima.mover(casaGuarita);
									}
								}
								peca.mover(proximaCasa);
								moved = true; // faz sair do loop, o movimento foi feito
							} else
								return;
						} else {
							peca.mover(proximaCasa); // move a peça
							moved = true; // faz sair do loop, o movimento foi feito
						}
					}
					if (proximaCasa.ehCasaFinal()) {
						int qtDePecas = proximaCasa.getQuantidadePecas();
						peca.mover(proximaCasa);
						qtDePecas++;
						proximaCasa.setQuantidadePecas(qtDePecas);
						if (qtDePecas == 4) {
							showMessageDialog(null, proximaCasa.getCor() + " venceu!");
							win = true;
							Principal principal = new Principal();
						}
						moved = true;

					}
				} else {
					// // NÃO HÁ PRÓXIMA CASA!
					// // FIM DO JOGO? A PEÇA ESTÁ NA GUARITA?
					// // Descomente a próxima linha para ser notificado quando isso acontecer:
					// System.err.println("Não há próxima casa!");

					// a próxima casa só é nula se:
					// 1. A peça está na guarita
					// 2. A peça está na casa final
					// No caso 1, a peça só sai da guarita se os dados tiverem valores iguais
					// a peça vai pra casa inicial da sua cor, que é obtida pelo objeto tabulheiro
					// No caso 2, não há o que fazer, a peça não deve se mecher e o jogo deve
					// permanecer parado

					if (casa.pertenceGuarita()) { // verifica se a peça está na guarita
						// verifica se os dados são iguais, para tirar da guarita
						if (dados[0].getValor() == dados[1].getValor()) {
							// move a peça para a casa inicial

							// ---NOTA: a peça não deve sair se a casa de início estiver ocupada----
							proximaCasa = tabuleiro.getCasaInicio(player.getColor());

							Peca pecaProxima = proximaCasa.getPeca();
							if (pecaProxima != null) {
								if (!player.isThisPlayer(pecaProxima)) {
									Guarita pecaRetorno = tabuleiro.getGuarita(pecaProxima.getCor());
									for (Casa casaGuarita : pecaRetorno.getTodasAsCasas()) {
										if (!casaGuarita.possuiPeca()) {
											pecaProxima.mover(casaGuarita);
										}
									}
									peca.mover(proximaCasa);
									moved = true; // faz sair do loop, o movimento foi feito
								} else
									return;
							} else {
								peca.mover(proximaCasa); // move a peça
								moved = true; // faz sair do loop, o movimento foi feito
							}
						} else {
							for (Peca pecaDoPlayer : player.getPecas()) {
								Casa casaDaPeça = pecaDoPlayer.getCasa();
								if (!casaDaPeça.pertenceGuarita() && !casaDaPeça.ehCasaFinal())
									return;
							}
							moved = true;
						}
					}

					if (casa.ehCasaFinal()) {
						return;
					}
					// // Descomente as duas próximas linhas para verificar se a peça está na
					// guarita:
					// if (casa.pertenceGuarita()) {
					// System.out.println("A peça está na guarita");
					// moved = true;
				}
			}
			// permite que os dados sejam rolados de novo
			// já que, como a peça já se mecheu, o próximo turno vai começar
			dadosRolados = false;
			if (dados[0].getValor() != dados[1].getValor())
				turnManager.next(); // finaliza o turno e começa o próximo
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
