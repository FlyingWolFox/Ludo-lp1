import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Implementação gráfica do Dado.
 * 
 * @author Alan Moraes / alan@ci.ufpb.br
 * @author Victor Koehler / koehlervictor@cc.ci.ufpb.br
 */
public class DadoGUI extends JButton {

    private final Principal janelaPrincipal;

    /**
     * Construtor gráfico para os Dados.
     * 
     * @param janelaPrincipal Janela principal.
     */
    public DadoGUI(Principal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        initComponents();

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        setIcon(janelaPrincipal.getIconePadrão("DADO_0"));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                janelaPrincipal.reagirDadoClicado((DadoGUI) evt.getSource());
            }
        });
    }

    /**
     * Define o valor de exibição dos dados.
     * 
     * @param valor Valor do dado
     */
    public void setValor(int valor) {
        if (valor >= 1 && valor <= 6) {
            setIcon(janelaPrincipal.getIconePadrão("DADO_" + valor));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(150, 150));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
