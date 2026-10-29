import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;

/**
 * Componente que exibe opcoes de cores e acoes (como limpar tela)
 * para facilitar o uso por criancas e alunos.
 * 
 * @author Julie Quaglio
 * @version 1.0
 */
public class PaletaCores extends JPanel {
    private PainelDesenho painelDesenho;

    private final Color[] CORES = {
        Color.BLACK, Color.GRAY, Color.WHITE, Color.RED,
        Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN,
        Color.BLUE, Color.MAGENTA, new Color(128, 0, 128), new Color(139, 69, 19)
    };

    /**
     * Construtor da Paleta de Cores.
     * 
     * @param painelDesenho Referencia ao PainelDesenho para atualizar a cor selecionada
     */
    public PaletaCores(PainelDesenho painelDesenho) {
        this.painelDesenho = painelDesenho;
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Cria um botao para cada cor da paleta
        for (Color cor : CORES) {
            JButton btnCor = new JButton();
            btnCor.setBackground(cor);
            btnCor.setOpaque(true);
            btnCor.setBorderPainted(true);
            btnCor.setPreferredSize(new Dimension(30, 30));

            btnCor.addActionListener(e -> painelDesenho.setCorAtual(cor));
            add(btnCor);
        }

        // Botao especial de borracha (cor branca)
        JButton btnBorracha = new JButton("Borracha");
        btnBorracha.addActionListener(e -> painelDesenho.setCorAtual(Color.WHITE));
        add(btnBorracha);

        // Botao de limpar todo o desenho
        JButton btnLimpar = new JButton("Limpar Tudo");
        btnLimpar.addActionListener(e -> painelDesenho.limpar());
        add(btnLimpar);
    }
}