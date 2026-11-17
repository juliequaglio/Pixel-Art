package janela;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;

/**
 * Barra lateral interativa contendo a paleta de selecao de cores e os botoes
 * de ferramentas (Borracha, Limpar Tudo, Salvar PNG e Abrir PNG).
 * 
 * <p>Projetada com botoes amplos e cores contrastantes para facilitar a interacao
 * intuitiva de criancas e estudantes.</p>
 * 
 * @author Julie Quaglio\
 * Ana Paula Barros
 * Vitor Seiji
 * 
 * @version 3.2
 */
public class PaletaCores extends JPanel {

    /** Referencia ao painel de desenho para atualizar a cor ativa e acionar comandos. */
    private PainelDesenho painelDesenho;

    /** Conjunto pre-definido de 20 cores disponiveis na paleta. */
    private final Color[] CORES = {
        Color.BLACK, Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.WHITE,
        new Color(139, 69, 19), new Color(178, 34, 34), Color.RED, Color.ORANGE, Color.YELLOW,
        new Color(255, 215, 0), Color.GREEN, new Color(34, 139, 34), Color.CYAN, new Color(30, 144, 255),
        Color.BLUE, new Color(75, 0, 130), Color.MAGENTA, Color.PINK, new Color(255, 182, 193)
    };

    /**
     * Constroi a barra lateral de cores e botoes de acao vinculados ao {@link PainelDesenho}.
     * 
     * @param painelDesenho Instancia de {@link PainelDesenho} controlada pela paleta.
     */
    public PaletaCores(PainelDesenho painelDesenho) {
        this.painelDesenho = painelDesenho;
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(210, 215, 225)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Subpainel de Cores em Grid
        JPanel painelCoresGrid = new JPanel(new GridLayout(10, 2, 6, 6));
        painelCoresGrid.setOpaque(false);

        for (Color cor : CORES) {
            JButton btnCor = new JButton();
            btnCor.setBackground(cor);
            btnCor.setOpaque(true);
            btnCor.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
            btnCor.setPreferredSize(new Dimension(36, 36));
            btnCor.setFocusPainted(false);

            btnCor.addActionListener(e -> painelDesenho.setCorAtual(cor));
            painelCoresGrid.add(btnCor);
        }

        // Subpainel de Acoes
        JPanel painelAcoes = new JPanel(new GridLayout(4, 1, 8, 8));
        painelAcoes.setOpaque(false);

        JButton btnBorracha = criarBotaoEstilizado("Borracha", new Color(230, 230, 240), Color.BLACK);
        btnBorracha.addActionListener(e -> painelDesenho.setCorAtual(Color.WHITE));

        JButton btnLimpar = criarBotaoEstilizado("Limpar Tudo", new Color(255, 220, 220), new Color(150, 0, 0));
        btnLimpar.addActionListener(e -> painelDesenho.limpar());

        JButton btnSalvar = criarBotaoEstilizado("Salvar PNG", new Color(220, 245, 220), new Color(0, 120, 0));
        btnSalvar.addActionListener(e -> painelDesenho.salvarDesenho());

        JButton btnAbrir = criarBotaoEstilizado("Abrir PNG", new Color(220, 235, 255), new Color(0, 70, 160));
        btnAbrir.addActionListener(e -> painelDesenho.abrirDesenho());

        painelAcoes.add(btnBorracha);
        painelAcoes.add(btnLimpar);
        painelAcoes.add(btnSalvar);
        painelAcoes.add(btnAbrir);

        JLabel lblTituloPaleta = new JLabel("CORES", SwingConstants.CENTER);
        lblTituloPaleta.setFont(new Font("Pixelify Sans", Font.BOLD, 14));
        lblTituloPaleta.setForeground(new Color(60, 60, 90));

        add(lblTituloPaleta, BorderLayout.NORTH);
        add(painelCoresGrid, BorderLayout.CENTER);
        add(painelAcoes, BorderLayout.SOUTH);
    }

    /**
     * Metodo auxiliar para criar botoes estilizados com cores e bordas personalizadas.
     * 
     * @param texto Rotulo textual exibido no botao.
     * @param bg    Cor de fundo ({@link java.awt.Color}) do botao.
     * @param fg    Cor do texto ({@link java.awt.Color}) do botao.
     * @return Instancia configurada de {@link javax.swing.JButton}.
     */
    private JButton criarBotaoEstilizado(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(fg.brighter(), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        return btn;
    }
}