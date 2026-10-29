import javax.swing.SwingUtilities;

/**
 * Classe de entrada principal do sistema.
 * 
 * @author Julie Quaglio
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal();
            janela.setVisible(true);
        });
    }
}