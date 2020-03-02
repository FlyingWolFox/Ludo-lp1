
/**
 * Gerenciador de turnos do jogo
 * 
 * @author Lucas Isaac
 */
public class TurnManager {
    private int whoIsNow; // de quem é o turno

    /**
     * Construtor inicializa com jogador nº0 como primeiro
     */
    public TurnManager() {
        whoIsNow = 0;
    }

    /**
     * Quem que joga agora
     * 
     * @return numero do jogador que joga no turno
     */
    public int getWhoIsNow() {
        return whoIsNow;
    }

    /**
     * Passa o turno para o próximo jogador
     */
    public void next() {
        // funciona mais ou menos assim:
        // 0 -> 1 -> 2 -> 3 -> 0 ....
        if (whoIsNow + 1 < 4)
            whoIsNow++;
        else
            whoIsNow = 0;
    }
}