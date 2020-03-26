
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author radames
 */
class GUI extends JFrame{
    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnSul = new JPanel();
    JPanel pnOeste = new JPanel();
    JPanel pnLeste = new JPanel();
    JPanel pnCentro = new JPanel();
    JLabel lbNorte = new JLabel("label no painel norte");
    
    public GUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Exemplo de Border Layout");
        
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        cp.add(pnNorte,BorderLayout.NORTH);
        cp.add(pnSul,BorderLayout.SOUTH);
        cp.add(pnOeste,BorderLayout.WEST);
        cp.add(pnLeste,BorderLayout.EAST);
        cp.add(pnCentro, BorderLayout.CENTER);
        
        pnNorte.setBackground(Color.cyan);
        pnSul.setBackground(Color.green);
        pnOeste.setBackground(Color.PINK);
        pnLeste.setBackground(Color.YELLOW);
        pnCentro.setBackground(Color.white);
        
        pnNorte.add(lbNorte);
        
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);        
    }  
}
