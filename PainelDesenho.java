import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Componente visual Swing onde o desenho ocorre.
 * Captura eventos do mouse e interage com o GridPixelArt.
 * 
 * @author Julie Quaglio
 * @version 1.0
 */
public class PainelDesenho extends JPanel {
    private GridPixelArt grid;
    private Color corAtual;

    /**
     * Construtor do Painel de Desenho.
     * 
     * @param grid Instancia do GridPixelArt
     */
    public PainelDesenho(GridPixelArt grid) {
        this.grid = grid;
        this.corAtual = Color.BLACK;

        setPreferredSize(new Dimension(grid.getLarguraTotalTela(), grid.getAlturaTotalTela()));

        // Evento de clique do mouse para pintar um unico bloco
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                grid.pintarPixelPorCoordenadaTela(e.getX(), e.getY(), corAtual);
                repaint();
            }
        });

        // Evento de arrastar o mouse para pintar continuamente
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                grid.pintarPixelPorCoordenadaTela(e.getX(), e.getY(), corAtual);
                repaint();
            }
        });
    }

    public void setCorAtual(Color cor) {
        this.corAtual = cor;
    }

    public Color getCorAtual() {
        return corAtual;
    }

    public void limpar() {
        grid.limparGrid();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        grid.desenhar(g);
    }
}