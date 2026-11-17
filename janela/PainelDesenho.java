package janela;

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

import pixel.GridPixelArt;

/**
 * Componente visual Swing ({@link javax.swing.JPanel}) onde a area de desenho e exibida.
 * 
 * <p>Gerencia a interacao do usuario por meio de eventos de clique e arrasto do mouse,
 * alem de disponibilizar caixas de dialogo para salvar e abrir arquivos PNG.</p>
 * 
 * @author Julie Quaglio
 * Ana Paula Barros
 * Vitor Seiji
 * 
 * @version 3.2
 */
public class PainelDesenho extends JPanel {

    /** Referencia ao grid logico que armazena e renderiza os pixels. */
    private GridPixelArt grid;

    /** Cor atualmente selecionada pelo usuario para pintura. */
    private Color corAtual;

    /**
     * Constroi o painel de desenho vinculando-o a uma instancia de {@link GridPixelArt}
     * e configurando os ouvintes de eventos do mouse.
     * 
     * @param grid Instancia de {@link pixel.GridPixelArt} que gerencia a matriz.
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

    /**
     * Define a cor ativa utilizada pelo pincel ao clicar ou arrastar sobre o quadro.
     * 
     * @param cor Nova {@link java.awt.Color} selecionada.
     */
    public void setCorAtual(Color cor) {
        this.corAtual = cor;
    }

    /**
     * Retorna a cor atualmente selecionada para pintura.
     * 
     * @return Objeto {@link java.awt.Color} correspondente a cor ativa.
     */
    public Color getCorAtual() {
        return corAtual;
    }

    /**
     * Limpa toda a area de desenho restaurando os pixels para branco e atualizando a tela.
     */
    public void limpar() {
        grid.limparGrid();
        repaint();
    }

    /**
     * Abre uma janela de selecao de arquivos ({@link JFileChooser}) para que o usuario
     * salve o desenho atual como uma imagem PNG no computador.
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

    /**
     * Abre uma janela de selecao de arquivos ({@link JFileChooser}) para resgatar
     * um desenho PNG salvo anteriormente e carrega-lo na matriz para continuar editando.
     */
    public void abrirDesenho() {
        JFileChooser seletorArquivo = new JFileChooser();
        seletorArquivo.setDialogTitle("Abrir Desenho em Pixel Art");
        seletorArquivo.setFileFilter(new FileNameExtensionFilter("Imagem PNG (*.png)", "png"));

        int escolha = seletorArquivo.showOpenDialog(this);
        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivoParaAbrir = seletorArquivo.getSelectedFile();

            try {
                BufferedImage img = ImageIO.read(arquivoParaAbrir);
                if (img == null) {
                    JOptionPane.showMessageDialog(this, "Arquivo de imagem invalido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean carregado = grid.carregarDeImagem(img);
                if (carregado) {
                    repaint();
                    JOptionPane.showMessageDialog(this, "Desenho carregado! Pode continuar pintando.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Esta imagem nao possui o tamanho padrao do quadro (" + grid.getLarguraTotalTela() + "x" + grid.getAlturaTotalTela() + " px).",
                        "Tamanho Incompativel", JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Sobrescreve o metodo de pintura do Swing para renderizar o grid de pixel art.
     * 
     * @param g Contexto grafico ({@link java.awt.Graphics}) do componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        grid.desenhar(g);
    }
}