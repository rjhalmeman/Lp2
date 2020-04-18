package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
 * @author radames
 */
public class GUI extends JFrame {

    private final Container cp;
    CardLayout cardLayout = new CardLayout();
    private final JPanel pnCard = new JPanel(cardLayout);

    private final JPanel pnAzul = new JPanel();
    private final JPanel pnVerde = new JPanel();
    private final JPanel pnAmarelo = new JPanel();

    private final JLabel jLabel = new JLabel("um label");
    private final JTextField jTextField = new JTextField(30);
    private final JButton jButton = new JButton("OK");
    private final JButton btVerde = new JButton("Painel Verde");
    private final JButton btAmarelo = new JButton("Painel Amarelo");

    public GUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //muda a cor dos painéis para que possamos vê-los
        pnCard.setBackground(Color.white);
        pnCard.add(pnVerde, "verde");
        pnCard.add(pnAmarelo, "amarelo");
        pnVerde.setBackground(Color.green);
        pnAmarelo.setBackground(Color.yellow);

        pnAzul.setBackground(Color.blue);

        cp = getContentPane(); //captura o painel de conteúdos
        cp.setLayout(new GridLayout(2, 1));
        cp.add(pnCard);
        cp.add(pnAzul);
        pnAzul.add(btVerde);
        pnAzul.add(btAmarelo);

        pnAmarelo.add(jLabel);
//        pnCard.add(jTextField);
//        pnCard.add(jButton);

        setTitle("GUI Básica - guib");
        setSize(800, 600);

        btVerde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnCard, "verde");
            }
        });

        btAmarelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnCard, "amarelo");
            }
        });

        setLocationRelativeTo(null);//posiciona no meio da tela
        setVisible(true);//faz a janela ficar visível

    }
}
