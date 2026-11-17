package pixel;

import java.awt.Color;

/**
 * Classe de modelo que representa um pixel individual dentro da matriz de desenho.
 * 
 * <p>Armazena as coordenadas logicas da celula no plano cartesiano (coluna {@code x}
 * e linha {@code y}) e a sua respectiva cor no modelo RGB.</p>
 * 
 * @author Julie Quaglio
 * Ana Paula Barros
 * Vitor Seiji
 * 
 * @version 3.2
 */
public class Pixel {

    /** Coordenada logica horizontal (coluna) do pixel na matriz. */
    private int x;

    /** Coordenada logica vertical (linha) do pixel na matriz. */
    private int y;

    /** Cor atual atribuida a este pixel no modelo RGB. */
    private Color cor;

    /**
     * Constroi uma nova instancia de {@code Pixel} com coordenadas e cor especificadas.
     * 
     * @param x   Posicao da coluna no grid (indice horizontal).
     * @param y   Posicao da linha no grid (indice vertical).
     * @param cor Cor RGB inicial associada a este pixel.
     */
    public Pixel(int x, int y, Color cor) {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }

    /**
     * Obtem a coordenada horizontal (coluna) do pixel.
     * 
     * @return O indice da coluna {@code x}.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtem a coordenada vertical (linha) do pixel.
     * 
     * @return O indice da linha {@code y}.
     */
    public int getY() {
        return y;
    }

    /**
     * Obtem a cor atual do pixel.
     * 
     * @return Objeto {@link java.awt.Color} representando a cor RGB do pixel.
     */
    public Color getCor() {
        return cor;
    }

    /**
     * Define ou altera a cor do pixel.
     * 
     * @param cor Nova {@link java.awt.Color} a ser aplicada ao pixel.
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }
}