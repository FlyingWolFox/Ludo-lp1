
// Usado para a ArrayList de players
import java.util.ArrayList;
// Para poder reiniciar o jogo a partir do JMenuItem
import javax.swing.JMenuItem;
// Para exibir a caixa de diálogo de vitória
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
	private ArrayList<Player> players; // possui os jogadores da partida
	private TurnManager turnManager; // gerenciador de turnos

	private JMenuItem menuNovo; // segura o gatilho para reiniciar o jogo

	/**
	 * Construtor padrão do Jogo Ludo. Isto é, um jogo de Ludo convencional com dois
	 * dados. menuNovo é passado como argumento para o jogo ser reiniciável
	 */
	public Jogo(JMenuItem menuNovo) {
		this(2);
		this.menuNovo = menuNovo;
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
			this.dados[i] = new Dado();
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
		for (CasaGuarita casaGuarita : guaritaVermelha.getTodasAsCasas()) {
			Castelo novaPeca = new Castelo("VERMELHO");
			novaPeca.mover(casaGuarita);
		}

		for (CasaGuarita casaGuarita : guaritaVerde.getTodasAsCasas()) {
			Castelo novaPeca = new Castelo("VERDE");
			novaPeca.mover(casaGuarita);
		}

		for (CasaGuarita casaGuarita : guaritaAzul.getTodasAsCasas()) {
			Castelo novaPeca = new Castelo("AZUL");
			novaPeca.mover(casaGuarita);
		}

		for (CasaGuarita casaGuarita : guaritaAmarela.getTodasAsCasas()) {
			Castelo novaPeca = new Castelo("AMARELO");
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

		// TODA VEZ QUE O USURIO CLICAR NO DADO DESENHADO NA INTERFACE GRÁFICA,
		// ESTE MÉTODO SERÁ INVOCADO.

		// dados são rolados, há não ser que já foram rolados no atual turno
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

		// TODA VEZ QUE O USUÁRIO CLICAR EM UMA CASA DESENHADA NA INTERFACE GRÁFICA,
		// ESTE MÉTODO SERÁ INVOCADO.

		// Perguntamos à casa se ela possui uma peça e
		// Vemos se os dados foram rolados no turno atual
		// Se não possuir ou os dados não foram rolados, não há nada para se fazer.
		if (!casa.possuiPeca() || !dadosRolados) {
			return;
		}

		// Perguntamos à casa qual é a peça.
		Castelo castelo = casa.getPeca();
		// Pegamos o jogador da vez
		Player player = players.get(turnManager.getWhoIsNow());
		// verificamos se a peça perternce ao jogador da vez
		// se não for, a peça não se move e o jogo não continua
		if (player.isThisPlayer(castelo)) {

			if (estaTravado(player, dados)) {
				turnManager.next();
				dadosRolados = false;
				return;
			}

			// curupira controla se a peça
			// andará para frente ou para trás,
			// nas casas seguras
			boolean curupira;

			// Percorreremos N casas.
			Casa proximaCasa = casa.proximaCasa(castelo, curupira=false, dados);

			if(casa.equals(proximaCasa))
			{
				ArrayList<Castelo> castelos = players.get(turnManager.getWhoIsNow()).getCastelo();
				for (Castelo casteloJogador : castelos) {
					Casa casaCastelo = casteloJogador.getCasa();
					if(!casaCastelo.proximaCasa(castelo, curupira=false, dados).equals(casaCastelo))
						return;
				}
			}

			// o jogo não continua a execução enquanto uma peça
			// do jogador da vez não se mecheu (moved == true)
			for (boolean moved = false; !moved;) {

				// se a próxima casa não for nula, mova a peça até lá
				if (proximaCasa != null) {
					// caso a próxima caso não seja final
					// o movimento é normal
					if (!proximaCasa.ehCasaFinal()) {
						// Finalmente, a variável casaN contém a casa que a peça deve ser inserida.
						Castelo pecaProxima = proximaCasa.getPeca();
						// caso tenha uma peça na casa de destino:
						if (pecaProxima != null) {
							// se for de outro jogador, capture ela
							// e a mande pra guarita
							if (!player.isThisPlayer(pecaProxima)) {
								// pega a peça que está na casa de destino
								Guarita pecaRetorno = tabuleiro.getGuarita(pecaProxima.getCor());
								// e a move para a guarita
								for (Casa casaGuarita : pecaRetorno.getTodasAsCasas()) {
									if (!casaGuarita.possuiPeca()) {
										pecaProxima.mover(casaGuarita);
									}
								}
								castelo.mover(proximaCasa);
								moved = true; // faz sair do loop, o movimento foi feito
							}
							if (castelo.equals(pecaProxima)) {
								castelo.mover(proximaCasa);
								moved = true; // faz sair do loop, o movimento foi feito
							}
							// caso seja do mesmo jogador, nada acontece
							else
								return;
						}
						// caso não haja peça na casa de destino
						// a peça vai até lá
						else {
							castelo.mover(proximaCasa); // move a peça
							moved = true; // faz sair do loop, o movimento foi feito
						}
					}
					// caso a casa de destino seja a casa final
					// devemos mover a peça até lá
					// e aumentar o número de peças na casa
					if (proximaCasa.ehCasaFinal()) {
						int qtDePecas = proximaCasa.getQuantidadePecas();
						castelo.mover(proximaCasa);
						qtDePecas++;
						proximaCasa.setQuantidadePecas(qtDePecas);
						// caso haja 4 peças na casa final, o jogador venceu!
						if (qtDePecas == 4) {
							// mostra uma caixa de diálogo, mostrando quem venceu
							showMessageDialog(null, proximaCasa.getCor() + " venceu!");
							// ativa o gatilho do item do menu Arquivo, Novo, efetivamente reiniciando o
							// jogo
							menuNovo.doClick();
						}
						moved = true;
					}
				} else {
					// não há próxima casa,
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

							// verifica se há uma peça na casa inicial
							Castelo pecaProxima = proximaCasa.getPeca();
							if (pecaProxima != null) {
								// caso não seja do mesmo jogador que estiver saindo, deve-se haver a captura
								if (!player.isThisPlayer(pecaProxima)) {
									Guarita pecaRetorno = tabuleiro.getGuarita(pecaProxima.getCor());
									for (CasaGuarita casaGuarita : pecaRetorno.getTodasAsCasas()) {
										if (!casaGuarita.possuiPeca()) {
											pecaProxima.mover(casaGuarita);
										}
									}
									castelo.mover(proximaCasa);
									moved = true; // faz sair do loop, o movimento foi feito
								}
								// caso seja, nada acontece
								else
									return;
							}
							// caso não haja nenhuma peça, o movimento é normal e a peça sai da guarita
							else {
								castelo.mover(proximaCasa); // move a peça
								moved = true; // faz sair do loop, o movimento foi feito
							}
						}
						// verifica se o jogador pode mover outra peça. Caso positivo o jogo não anda,
						// caso negativo, ele perde a vez
						else {
							for (Castelo pecaDoPlayer : player.getCastelo()) {
								Casa casaDaPeca = pecaDoPlayer.getCasa();
								if (!casaDaPeca.pertenceGuarita() && !casaDaPeca.ehCasaFinal())
									return;
							}
							moved = true;
						}
					}

					// caso a peça esteja na casa finala, não há nada a se fazer
					if (casa.ehCasaFinal()) {
						return;
					}
				}
			}

			// TODO: capture verification

			// TODO: win verfication

			if (proximaCasa.ehCasaFinal()) {
				if (castelo.getNivel() == 4) {
					// mostra uma caixa de diálogo, mostrando quem venceu
					showMessageDialog(null, proximaCasa.getCor() + " venceu!");
					// ativa o gatilho do item do menu Arquivo, Novo, efetivamente reiniciando o
					// jogo
					menuNovo.doClick();
				}
			}

			// permite que os dados sejam rolados de novo
			// já que, como a peça já se mecheu, o próximo turno vai começar
			dadosRolados = false;
			// caso o jogador tire uma dupla nos dados, ele pode jogar de novo
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

	public String getJogadorAnterior() {
		Player player = players.get(turnManager.getWhoWasBefore());
		return player.getColor();
	}

	public boolean estaTravado(Player jogador, Dado[] dados) {
		int somaDados = dados[0].getValor() + dados[1].getValor();
		for (Castelo peca : jogador.getCastelo()) {
			Casa proximaCasa = peca.getCasa();
			if (!peca.getCasa().ehCasaFinal() && !peca.getCasa().pertenceGuarita()) {
				boolean curupira = false;
				for (int i = 0; i < somaDados && proximaCasa != null; i++) {
					// caso a peça esteja na entrada da casa segura
					// ele entra lá
					if (proximaCasa.ehEntradaZonaSegura() && (proximaCasa.getCasaSegura().getCor() == peca.getCor()))
						proximaCasa = proximaCasa.getCasaSegura();
					// caso a peça já esteja na casa final
					// isso vai acionar curupira e faz a peça
					// andar para trás
					else if (proximaCasa.ehCasaFinal()) {
						// caso a peça já esteja na casa final
						// não há nada há fazer
						if (i == 0) {
							proximaCasa = null;
							break;
						}
						curupira = true;
						proximaCasa = proximaCasa.getCasaAnterior();

					}
					// faz a peça retroceder até a primeira casa segura
					// caso a peça "passou direto" pela casa final
					else if (curupira && proximaCasa.getCasaAnterior() != null)
						proximaCasa = proximaCasa.getCasaAnterior();
					// a peça atingiu a primeira casa segura,
					// curupira é desacionar curupira, para
					// a peça voltar a se mover para frente
					else if (curupira && proximaCasa.getCasaAnterior() == null) {
						proximaCasa = proximaCasa.getCasaSeguinte();
						curupira = false;
					}
					// em nenhum caso especial, a peça se move normalmente
					else
						proximaCasa = proximaCasa.getCasaSeguinte();

				}
				if (proximaCasa.possuiPeca() && !proximaCasa.ehCasaFinal()) {
					if (!jogador.isThisPlayer(proximaCasa.getPeca()) && !peca.equals(proximaCasa.getPeca())) {
						return false;
					}
					if (peca.equals(proximaCasa.getPeca()))
						return false;
				} else
					return false;
			}
			if (peca.getCasa().pertenceGuarita()) {
				if (dados[0].getValor() == dados[1].getValor()) {
					// verifica se há uma peça na casa inicial
					proximaCasa = tabuleiro.getCasaInicio(jogador.getColor());
					Castelo pecaProxima = proximaCasa.getPeca();
					if (pecaProxima != null) {
						if (!jogador.isThisPlayer(pecaProxima)) {
							return false;
						}
					} else
						return false;
				}
			}
		}
		return true;
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
