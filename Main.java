import javax.swing.SwingUtilities;
import janela.*;
/**
 * Classe de entrada principal do sistema.
 * 
 * @author Julie Quaglio
 * @version 3.0
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal();
            janela.setVisible(true);
        });
    }
}