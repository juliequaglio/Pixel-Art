package pixel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Gerencia a estrutura de dados bidimensional (matriz) de pixels e realiza a conversao
 * entre coordenadas fisicas da tela e coordenadas logicas da matriz.
 * 
 * <p>Implementa a interface {@link IDesenhavel} para renderizar cada pixel como uma
 * primitiva grafica retangular e fornece metodos para exportar e importar desenhos
 * no formato PNG.</p>
 * 
 * @author Julie Quaglio
 * Ana Paula Barros
 * Vitor Seiji
 * 
 * @version 3.2
 */
public class GridPixelArt implements IDesenhavel {

    /** Quantidade total de colunas da matriz de desenho. */
    private int colunas;

    /** Quantidade total de linhas da matriz de desenho. */
    private int linhas;

    /** Dimensao (largura e altura) em pixels de cada celula quadrada na tela. */
    private int tamanhoPixelTela;

    /** Matriz bidimensional que armazena todos os objetos {@link Pixel} do desenho. */
    private Pixel[][] matriz;

    /**
     * Constroi um novo grid de Pixel Art e inicializa todas as celulas na cor branca.
     * 
     * @param colunas          Numero de colunas na matriz.
     * @param linhas           Numero de linhas na matriz.
     * @param tamanhoPixelTela Tamanho em pixels de cada bloco/retangulo na tela.
     */
    public GridPixelArt(int colunas, int linhas, int tamanhoPixelTela) {
        this.colunas = colunas;
        this.linhas = linhas;
        this.tamanhoPixelTela = tamanhoPixelTela;
        this.matriz = new Pixel[linhas][colunas];
        limparGrid();
    }

    /**
     * Reinicia toda a matriz, preenchendo todos os pixels com a cor branca ({@link Color#WHITE}).
     */
    public void limparGrid() {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                matriz[l][c] = new Pixel(c, l, Color.WHITE);
            }
        }
    }

    /**
     * Converte as coordenadas fisicas de clique do mouse na tela para os indices logicos
     * da matriz e altera a cor do pixel correspondente.
     * 
     * @param mouseX Coordenada horizontal (X) em pixels capturada na tela.
     * @param mouseY Coordenada vertical (Y) em pixels capturada na tela.
     * @param cor    Nova {@link java.awt.Color} a ser aplicada ao pixel atingido.
     */
    public void pintarPixelPorCoordenadaTela(int mouseX, int mouseY, Color cor) {
        int coluna = mouseX / tamanhoPixelTela;
        int linha = mouseY / tamanhoPixelTela;

        if (coluna >= 0 && coluna < colunas && linha >= 0 && linha < linhas) {
            matriz[linha][coluna].setCor(cor);
        }
    }

    /**
     * Renderiza toda a matriz na tela desenhando cada pixel como um retangulo preenchido
     * ({@code fillRect}) acompanhado de seu contorno de grade ({@code drawRect}).
     * 
     * @param g Contexto grafico ({@link java.awt.Graphics}) onde o grid sera desenhado.
     */
    @Override
    public void desenhar(Graphics g) {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                Pixel p = matriz[l][c];

                // Desenha a primitiva grafica: retangulo preenchido com a cor do pixel
                g.setColor(p.getCor());
                g.fillRect(c * tamanhoPixelTela, l * tamanhoPixelTela, tamanhoPixelTela, tamanhoPixelTela);

                // Desenha a grade (contorno do retangulo) para orientacao visual do aluno
                g.setColor(new Color(220, 220, 220));
                g.drawRect(c * tamanhoPixelTela, l * tamanhoPixelTela, tamanhoPixelTela, tamanhoPixelTela);
            }
        }
    }

    /**
     * Converte o estado atual da matriz de pixels em uma imagem em memoria ({@link BufferedImage})
     * sem as linhas de grade, pronta para exportacao em formato PNG.
     * 
     * @return Objeto {@link java.awt.image.BufferedImage} contendo a arte finalizada.
     */
    public BufferedImage gerarImagem() {
        BufferedImage imagem = new BufferedImage(getLarguraTotalTela(), getAlturaTotalTela(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = imagem.getGraphics();

        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                g.setColor(matriz[l][c].getCor());
                g.fillRect(c * tamanhoPixelTela, l * tamanhoPixelTela, tamanhoPixelTela, tamanhoPixelTela);
            }
        }
        g.dispose();
        return imagem;
    }

    /**
     * Le uma imagem PNG salva anteriormente e reconstroi as cores da matriz de pixels,
     * permitindo ao usuario retomar e continuar editando um desenho existente.
     * 
     * @param imagem Objeto {@link java.awt.image.BufferedImage} lido a partir de um arquivo PNG.
     * @return {@code true} se as dimensoes da imagem forem compativeis com o grid e o carregamento
     *         tiver sucesso; {@code false} caso as dimensoes sejam incompativeis.
     */
    public boolean carregarDeImagem(BufferedImage imagem) {
        if (imagem.getWidth() != getLarguraTotalTela() || imagem.getHeight() != getAlturaTotalTela()) {
            return false;
        }

        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                int centroX = (c * tamanhoPixelTela) + (tamanhoPixelTela / 2);
                int centroY = (l * tamanhoPixelTela) + (tamanhoPixelTela / 2);
                int rgb = imagem.getRGB(centroX, centroY);
                matriz[l][c].setCor(new Color(rgb, true));
            }
        }
        return true;
    }

    /**
     * Calcula a largura total do grid em pixels na tela.
     * 
     * @return Largura total em pixels ({@code colunas * tamanhoPixelTela}).
     */
    public int getLarguraTotalTela() {
        return colunas * tamanhoPixelTela;
    }

    /**
     * Calcula a altura total do grid em pixels na tela.
     * 
     * @return Altura total em pixels ({@code linhas * tamanhoPixelTela}).
     */
    public int getAlturaTotalTela() {
        return linhas * tamanhoPixelTela;
    }

    /**
     * Altera o tamanho de renderizacao de cada bloco na tela.
     * 
     * @param tamanho Novo tamanho em pixels de cada celula.
     */
    public void setTamanhoPixelTela(int tamanho) {
        this.tamanhoPixelTela = tamanho;
    }
}