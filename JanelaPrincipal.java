import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;

/**
 * Janela principal configurada para EXECUTAR EM TELA CHEIA.
 * Organiza o topo estilizado em tons de rosa e contraste escuro, 
 * paleta lateral e matriz centralizada.
 * 
 * @author Julie Quaglio
 * @version 3.1
 */
public class JanelaPrincipal extends JFrame {

    /**
     * Construtor da JanelaPrincipal.
     */
    public JanelaPrincipal() {
        setTitle("Pixel Art - Iniciacao a Logica Computacional");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // CONFIGURACAO PARA TELA CHEIA AO COMPILAR E EXECUTAR
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false); 
        setLayout(new BorderLayout());

        // 1. Cabecalho Superior com Fundo Escuro para Contrastar com o Rosa
        JPanel painelTopo = new JPanel(new BorderLayout(5, 5));
        painelTopo.setBackground(new Color(25, 28, 36)); // Fundo escuro em alto contraste
        painelTopo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel lblTitulo = new JLabel("CRIE SEU DESENHO EM PIXEL ART", SwingConstants.CENTER);
        // Fonte retro em estilo Pixel Art e cor Rosa (Hot Pink)
        lblTitulo.setFont(obterFonteRetro(28, Font.BOLD));
        lblTitulo.setForeground(new Color(255, 105, 180)); 

        String textoInstrucoes = "<html><center>"
                + "<font color='#F0F0F0'><b>COMO DESENHAR:</b> Escolha uma cor na barra lateral e clique (ou arraste) nos quadradinhos para formar seu desenho</font><br>"
                + "<font color='#FFC0CB'>Use a <i>Borracha</i> para apagar, <i>Limpar Tudo</i> para limpar a tela e <i>Salvar PNG</i> para guardar sua arte.</font>"
                + "</center></html>";

        JLabel lblInstrucoes = new JLabel(textoInstrucoes, SwingConstants.CENTER);
        lblInstrucoes.setFont(new Font("SansSerif", Font.PLAIN, 13));

        painelTopo.add(lblTitulo, BorderLayout.NORTH);
        painelTopo.add(lblInstrucoes, BorderLayout.SOUTH);

        // 2. Grid de Desenho (Matriz 40x40 ajustada para a tela)
        GridPixelArt grid = new GridPixelArt(40, 40, 18);
        PainelDesenho painelDesenho = new PainelDesenho(grid);

        // Subpainel central
        JPanel painelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelCentral.setBackground(new Color(230, 235, 242));
        painelCentral.add(painelDesenho);

        // 3. Paleta de Cores Lateral
        PaletaCores paletaCores = new PaletaCores(painelDesenho);

        // Adiciona os componentes a Janela Principal
        add(painelTopo, BorderLayout.NORTH);
        add(paletaCores, BorderLayout.WEST);
        add(painelCentral, BorderLayout.CENTER);
    }

    /**
     * Retorna a fonte retro em estilo Pixel Art com fallback seguro.
     */
    private Font obterFonteRetro(int tamanho, int estilo) {
        Font f = new Font("Pixelify Sans", estilo, tamanho);
        if (f.getFamily().equals("Dialog")) {
            return new Font("Monospaced", estilo, tamanho);
        }
        return f;
    }
}