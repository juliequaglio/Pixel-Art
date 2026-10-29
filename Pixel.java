import java.awt.Color;

/**
 * Representa um pixel individual no plano de pixel art.
 * Armazena suas coordenadas logicas (coluna x e linha y) e sua cor RGB.
 * 
 * @author 
 * Ana Paula Barros de Jesus
 * Julie Quaglio
 * 
 * @version 1.0
 */
public class Pixel {
    private int x;
    private int y;
    private Color cor;

    /**
     * Construtor da classe Pixel.
     * 
     * @param x Posicao da coluna no grid (sistema de coordenadas logico)
     * @param y Posicao da linha no grid (sistema de coordenadas logico)
     * @param cor Cor RGB associada a este pixel
     */
    public Pixel(int x, int y, Color cor) {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}