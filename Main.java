import javax.swing.SwingUtilities;

/**
 * Classe de entrada principal do sistema.
 * 
 * @author Julie Quaglio
<<<<<<< HEAD
 * @version 3.0
=======
 * @version 1.0
>>>>>>> main
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal();
            janela.setVisible(true);
        });
    }
}