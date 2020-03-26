package Main;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author radames
 */
class GUI extends JFrame {

    Container cp;

    JPanel pnCima = new JPanel();
    JPanel pnMeio = new JPanel();
    JPanel pnBaixo = new JPanel();

    JLabel lbIdade = new JLabel("Idade");
    JTextField tfIdade = new JTextField(10);
    JButton btCalcular = new JButton("Calcular");
    JLabel lbResposta = new JLabel("Categoria");
    JTextField tfCategoria = new JTextField(20);

    public GUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Exercício 4 lista 11");

        cp = getContentPane();
        cp.setLayout(new GridLayout(3, 1));

        cp.add(pnCima);
        cp.add(pnMeio);
        cp.add(pnBaixo);

        pnCima.setBackground(Color.lightGray);
        pnMeio.setBackground(Color.CYAN);
        pnBaixo.setBackground(Color.yellow);

        pnCima.add(lbIdade);
        pnCima.add(tfIdade);
        pnMeio.add(btCalcular);
        pnBaixo.add(lbResposta);
        pnBaixo.add(tfCategoria);

        btCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idade = 0;
                try {
                    idade = Integer.valueOf(tfIdade.getText());
                    if (idade < 5) {
                        tfCategoria.setText("erro - idade < 5");
                    } else if (idade > 120) {
                        tfCategoria.setText("Velho demais...");
                    } else {
                        Processamento processamento = new Processamento(idade);
                        tfCategoria.setText(processamento.getCategoria());
                    }
                } catch (Exception erro) {
                    tfCategoria.setText("Erro na entrada de dados");
                }

            }
        });

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
