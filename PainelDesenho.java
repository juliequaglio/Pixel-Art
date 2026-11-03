import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Componente visual Swing onde o desenho ocorre.
 * Captura eventos do mouse e salva a arte em PNG.
 * 
 * @author Julie Quaglio
 * @version 3.0
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
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                grid.pintarPixelPorCoordenadaTela(e.getX(), e.getY(), corAtual);
                repaint();
            }
        });

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

    /**
     * Abre uma caixa de dialogo para guardar a imagem PNG.
     */
    public void salvarDesenho() {
        JFileChooser seletorArquivo = new JFileChooser();
        seletorArquivo.setDialogTitle("Salvar Desenho em Pixel Art");
        seletorArquivo.setFileFilter(new FileNameExtensionFilter("Imagem PNG (*.png)", "png"));

        int escolha = seletorArquivo.showSaveDialog(this);
        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivoParaSalvar = seletorArquivo.getSelectedFile();
            
            if (!arquivoParaSalvar.getAbsolutePath().toLowerCase().endsWith(".png")) {
                arquivoParaSalvar = new File(arquivoParaSalvar.getAbsolutePath() + ".png");
            }

            try {
                BufferedImage img = grid.gerarImagem();
                ImageIO.write(img, "png", arquivoParaSalvar);
                JOptionPane.showMessageDialog(this, "Desenho salvo com sucesso!", "Parabens!", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        grid.desenhar(g);
    }
}