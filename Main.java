import javax.swing.SwingUtilities;
import janela.*;
/**
 * Classe de entrada principal do sistema.
 * 
 * @author Julie Quaglio
 * @author Ana Paula Barros de Jesus
 * @author Vitor Seiji Colombo Nishida
 * @version 3.0
 */
public class Main {
    public static void main(String[] args) {
        JanelaPergunta Jp = new JanelaPergunta();
        
        int valores[] = Jp.DefinirAlturaELargura();
        
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal(valores[1], valores[0]);
            janela.setVisible(true);
        });
    }
}