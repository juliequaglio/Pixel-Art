package pixel;

import java.awt.Graphics;

/**
 * Interface que define o contrato para todos os elementos graficos que podem
 * ser renderizados na tela utilizando a biblioteca grafica do Java AWT/Swing.
 * 
 * <p>Garante o principio de polimorfismo na renderizacao dos componentes visuais
 * da aplicacao de Pixel Art.</p>
 * 
 * @author Julie Quaglio
 * Ana Paula Barros
 * Vitor Seiji
 * 
 * @version 3.2
 */
public interface IDesenhavel {

    /**
     * Desenha o elemento grafico na tela utilizando o contexto grafico fornecido.
     * 
     * @param g Contexto grafico ({@link java.awt.Graphics}) responsavel pela renderizacao.
     */
    void desenhar(Graphics g);
}