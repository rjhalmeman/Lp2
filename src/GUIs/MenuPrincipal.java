package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author radames
 */
public class MenuPrincipal extends JFrame {

    Container cp;
    JPanel pn = new JPanel();
    JButton bt1 = new JButton("Cor dos Olhos");
    JButton bt2 = new JButton("Pessoa");
    JButton bt3 = new JButton("Buscar Imagens");
    JTextField tf= new JTextField(20);

    public MenuPrincipal() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("MENU PRINCIPAL");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pn.add(bt1);
        pn.add(bt2);
//        pn.add(bt3);
//        pn.add(tf);
        cp.add(pn, BorderLayout.CENTER);

        pn.setBackground(Color.LIGHT_GRAY);
        pn.setPreferredSize(new Dimension(100, 60));
        bt1.setPreferredSize(new Dimension(240, 80));
        bt2.setPreferredSize(new Dimension(240, 80));
        bt3.setPreferredSize(new Dimension(240, 80));
        bt1.setBackground(new Color(200, 29, 37));
        bt2.setBackground(new Color(200, 29, 37));
        bt3.setBackground(new Color(11, 57, 84));
        bt1.setForeground(Color.WHITE);
        bt2.setForeground(Color.WHITE);
        bt3.setForeground(Color.WHITE);
        bt1.setFont(new Font("Sans-Serif ", Font.PLAIN, 26));
        bt2.setFont(new Font("Sans-Serif", Font.PLAIN, 26));
        bt3.setFont(new Font("Sans-Serif", Font.PLAIN, 26));
        bt1.setBorder(BorderFactory.createLineBorder(Color.black));
        bt2.setBorder(BorderFactory.createLineBorder(Color.black));
        bt3.setBorder(BorderFactory.createLineBorder(Color.black));
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CorDosOlhosGUI corDosOlhosGUI = new CorDosOlhosGUI();
            }
        });

        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PessoaGUI pessoaGUI = new PessoaGUI();
            }
        });
//        bt3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                final String prefixBusca = "http://www.google.com.br/search?q=";
//                String pesquisa = tf.getText();
//                String uri = prefixBusca + pesquisa;
//                try {
//                    Desktop.getDesktop().browse(new URI(uri));
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Erro Ao Buscar, Tente Outra Pesquisa");
//
//                }
//            }
//        });

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
