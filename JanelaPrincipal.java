import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Janela principal da aplicacao que agrupa o painel de desenho e a paleta de cores.
 * 
 * @author Julie Quaglio
 * @version 1.0
 */
public class JanelaPrincipal extends JFrame {

    /**
     * Construtor da JanelaPrincipal.
     */
    public JanelaPrincipal() {
        setTitle("Pixel Art - Iniciacao a Logica Computacional");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Configuracao do grid: 20x20 blocos de 25 pixels cada
        GridPixelArt grid = new GridPixelArt(20, 20, 25);
        PainelDesenho painelDesenho = new PainelDesenho(grid);
        PaletaCores paletaCores = new PaletaCores(painelDesenho);

        add(painelDesenho, BorderLayout.CENTER);
        add(paletaCores, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }
}