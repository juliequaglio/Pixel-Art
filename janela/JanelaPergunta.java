package janela;

import javax.swing.*;

public class JanelaPergunta {

    public static int[] DefinirAlturaELargura() {
        // Chama a função e guarda os dois resultados no array "valores"
        int[] valores = pedirDoisNumeros("Altura (1 a 45):", "Largura (1 a 90):");

        // Verifica se o usuário não cancelou a janela
        if (valores != null) {
            int numero1 = valores[0];
            int numero2 = valores[1];

        } else {
            JOptionPane.showMessageDialog(null, "O usuário cancelou a operação.");
        }
        
        return valores;
    }

    /**
     * Abre uma janela com duas perguntas e retorna um array int[2] com valores entre 1 e 100.
     * Retorna null se o usuário cancelar.
     */
    public static int[] pedirDoisNumeros(String pergunta1, String pergunta2) {
        JTextField campo1 = new JTextField(10);
        JTextField campo2 = new JTextField(10);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel(pergunta1));
        painel.add(campo1);
        painel.add(Box.createVerticalStrut(10));
        painel.add(new JLabel(pergunta2));
        painel.add(campo2);

        while (true) {
            int opcao = JOptionPane.showConfirmDialog(null, painel, "Entrada de Dados", JOptionPane.OK_CANCEL_OPTION);

            // Se clicar em Cancelar ou fechar no X, a função retorna null
            if (opcao != JOptionPane.OK_OPTION) {
                return null;
            }

            try {
                int num1 = Integer.parseInt(campo1.getText().trim());
                int num2 = Integer.parseInt(campo2.getText().trim());

                // Valida se ambos estão entre os intervalos definidos
                if (num1 >= 1 && num1 <= 45 && num2 >= 1 && num2 <= 90) {
                    // Retorna os dois valores juntos em um array
                    return new int[]{num1, num2};
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: Os dois valores devem estar entre o intervalo definido.", "Fora do limite", JOptionPane.WARNING_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Digite apenas números inteiros válidos.", "Formato inválido", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
