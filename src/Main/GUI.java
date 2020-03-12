package Main;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GUI extends JFrame{
    Container cp;
    JPanel pnA = new JPanel();
    JPanel pnB = new JPanel();
    JPanel pnC = new JPanel();
    JPanel pnD = new JPanel();
    
    
    public GUI() {
        cp= getContentPane();
        cp.setLayout(new GridLayout(4,1));
        
        cp.add(pnA);
        pnA.setBackground(Color.red);
        cp.add(pnB);
        pnB.setBackground(Color.blue);
        
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);//centraliza
        setVisible(true);
    }
}