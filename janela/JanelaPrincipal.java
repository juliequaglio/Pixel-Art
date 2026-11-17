package janela;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;

import pixel.*;

/**
 * Janela principal ({@link javax.swing.JFrame}) da aplicacao, configurada para
 * iniciar automaticamente em modo tela cheia ({@code MAXIMIZED_BOTH}).
 * 
 * <p>Integra o cabecalho superior estilizado com instrucoes didaticas, a barra lateral
 * de cores ({@link PaletaCores}) e a area central de desenho ({@link PainelDesenho}).</p>
 * 
 * @author Julie Quaglio
 * Ana Paula Barros
 * Vitor Seiji
 * 
 * @version 3.2
 */
public class JanelaPrincipal extends JFrame {

    /**
     * Constroi e organiza todos os componentes visuais da janela principal da aplicacao.
     */
    public JanelaPrincipal() {
        setTitle("Pixel Art - Iniciacao a Logica Computacional");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuracao para tela cheia ao iniciar
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLayout(new BorderLayout());

        // 1. Cabecalho Superior com Fundo Escuro para Contrastar com o Rosa
        JPanel painelTopo = new JPanel(new BorderLayout(5, 5));
        painelTopo.setBackground(new Color(25, 28, 36));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel lblTitulo = new JLabel("CRIE SEU DESENHO EM PIXEL ART", SwingConstants.CENTER);
        lblTitulo.setFont(obterFonteRetro(28, Font.BOLD));
        lblTitulo.setForeground(new Color(255, 105, 180));

        String textoInstrucoes = "<html><center>"
                + "<font color='#F0F0F0'><b>COMO DESENHAR:</b> Escolha uma cor na barra lateral e clique (ou arraste) nos quadradinhos para formar seu desenho</font><br>"
                + "<font color='#FFC0CB'>Use a <i>Borracha</i> para apagar, <i>Limpar Tudo</i> para recomecar, <i>Salvar PNG</i> para guardar ou <i>Abrir PNG</i> para continuar um desenho.</font>"
                + "</center></html>";

        JLabel lblInstrucoes = new JLabel(textoInstrucoes, SwingConstants.CENTER);
        lblInstrucoes.setFont(new Font("SansSerif", Font.PLAIN, 13));

        painelTopo.add(lblTitulo, BorderLayout.NORTH);
        painelTopo.add(lblInstrucoes, BorderLayout.SOUTH);

        // 2. Grid de Desenho (Matriz 40x40 com blocos de 18px)
        GridPixelArt grid = new GridPixelArt(40, 40, 18);
        PainelDesenho painelDesenho = new PainelDesenho(grid);

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
     * Carrega a fonte retro {@code Pixelify Sans} caso esteja instalada no sistema
     * ou retorna a fonte {@code Monospaced} como alternativa segura.
     * 
     * @param tamanho Tamanho da fonte em pontos.
     * @param estilo  Estilo da fonte (ex: {@link Font#BOLD}, {@link Font#PLAIN}).
     * @return Instancia de {@link java.awt.Font} configurada.
     */
    private Font obterFonteRetro(int tamanho, int estilo) {
        Font f = new Font("Pixelify Sans", estilo, tamanho);
        if (f.getFamily().equals("Dialog")) {
            return new Font("Monospaced", estilo, tamanho);
        }
        return f;
    }
}