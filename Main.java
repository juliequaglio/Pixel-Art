import javax.swing.SwingUtilities;
import janela.*;

/**
 * Classe principal de inicializacao do sistema de Pixel Art.
 * 
 * <p>Responsavel por iniciar a interface grafica de forma segura na
 * Event Dispatch Thread (EDT) do Java Swing.</p>
 * 
 * @author Julie Quaglio
 * Ana Paula Barros
 * Vitor Seiji
 * 
 * @version 3.2
 */
public class Main {

    /**
     * Ponto de entrada da aplicacao Java.
     * 
     * @param args Argumentos de linha de comando (nao utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal();
            janela.setVisible(true);
        });
    }
}