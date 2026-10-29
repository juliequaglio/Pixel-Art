import java.awt.Graphics;

/**
 * Interface que define o contrato para objetos que podem ser desenhados
 * na tela usando a biblioteca grafica do Java.
 * 
 * @author Julie Quaglio
 * @version 1.0
 */
public interface IDesenhavel {
    /**
     * Desenha o elemento na tela utilizando o contexto grafico fornecido.
     * 
     * @param g Contexto grafico Java AWT
     */
    void desenhar(Graphics g);
}