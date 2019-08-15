package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
    private final JPanel pnNorte = new JPanel();
    private final JPanel pnCentro = new JPanel();
    private final JPanel pnSul = new JPanel();
    
    private final JLabel jLabel = new JLabel("um label");
    private final JTextField jTextField = new JTextField(30); 
    private final JButton jButton = new JButton("OK");
    

    public GUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //muda a cor dos painéis para que possamos vê-los
        pnNorte.setBackground(Color.green);
        pnCentro.setBackground(Color.yellow);
        pnSul.setBackground(Color.blue);

        cp = getContentPane(); //captura o painel de conteúdos
        cp.setLayout(new BorderLayout());
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        
        pnNorte.add(jLabel);
        pnNorte.add(jTextField);
        pnNorte.add(jButton);
        

        setTitle("GUI Básica - guib");
        setSize(800, 600);

        
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField.setText("você clicou o botão");
            }
        });
        
        
        setLocationRelativeTo(null);//posiciona no meio da tela
        setVisible(true);//faz a janela ficar visível

    }
}
