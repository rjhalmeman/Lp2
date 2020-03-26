package Main;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author radames
 */
class GUI extends JFrame {

    Container cp;
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();

    public GUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Exemplo GridLayout");

        cp = getContentPane();
        cp.setLayout(new GridLayout(3, 2));//3 linhas e 2 colunas

        cp.add(p1);
        cp.add(p2);
        cp.add(p3);
        cp.add(p4);
        cp.add(p5);
        cp.add(p6);

        p1.setBackground(Color.blue);
        p2.setBackground(Color.yellow);
        p3.setBackground(Color.green);
        p4.setBackground(Color.pink);
        p5.setBackground(Color.red);
        p6.setBackground(Color.black);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
