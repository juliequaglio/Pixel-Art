import java.awt.Color;
import java.awt.Graphics;

/**
 * Gerencia a matriz de pixels e realiza os calculos do sistema de coordenadas,
 * convertendo posicoes em pixels de tela para coordenadas de matriz.
 * Cada pixel e renderizado como um retangulo (primitiva grafica).
 * 
 * @author Julie Quaglio
 * @version 1.0
 */
public class GridPixelArt implements IDesenhavel {
    private int colunas;
    private int linhas;
    private int tamanhoPixelTela;
    private Pixel[][] matriz;

    /**
     * Construtor do Grid de Pixel Art.
     * 
     * @param colunas Numero de colunas na matriz
     * @param linhas Numero de linhas na matriz
     * @param tamanhoPixelTela Tamanho em pixels de cada bloco/retangulo na tela
     */
    public GridPixelArt(int colunas, int linhas, int tamanhoPixelTela) {
        this.colunas = colunas;
        this.linhas = linhas;
        this.tamanhoPixelTela = tamanhoPixelTela;
        this.matriz = new Pixel[linhas][colunas];
        limparGrid();
    }

    /**
     * Inicializa ou limpa o grid com a cor branca.
     */
    public void limparGrid() {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                matriz[l][c] = new Pixel(c, l, Color.WHITE);
            }
        }
    }

    /**
     * Pinta um pixel especifico na matriz logica a partir de coordenadas de tela (x, y do mouse).
     * 
     * @param mouseX Coordenada X na tela (pixels)
     * @param mouseY Coordenada Y na tela (pixels)
     * @param cor Cor a ser aplicada ao pixel
     */
    public void pintarPixelPorCoordenadaTela(int mouseX, int mouseY, Color cor) {
        int coluna = mouseX / tamanhoPixelTela;
        int linha = mouseY / tamanhoPixelTela;

        if (coluna >= 0 && coluna < colunas && linha >= 0 && linha < linhas) {
            matriz[linha][coluna].setCor(cor);
        }
    }

    /**
     * Renderiza o grid e cada retangulo (primitiva grafica) correspondente ao pixel na tela.
     * 
     * @param g Contexto grafico AWT
     */
    @Override
    public void desenhar(Graphics g) {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                Pixel p = matriz[l][c];
                
                // Desenha a primitiva grafica: Retangulo preenchido com a cor do pixel
                g.setColor(p.getCor());
                g.fillRect(c * tamanhoPixelTela, l * tamanhoPixelTela, tamanhoPixelTela, tamanhoPixelTela);
                
                // Desenha a grade (contorno do retangulo) para facilitar o alinhamento visual
                g.setColor(new Color(220, 220, 220));
                g.drawRect(c * tamanhoPixelTela, l * tamanhoPixelTela, tamanhoPixelTela, tamanhoPixelTela);
            }
        }
    }

    public int getLarguraTotalTela() {
        return colunas * tamanhoPixelTela;
    }

    public int getAlturaTotalTela() {
        return linhas * tamanhoPixelTela;
    }
}