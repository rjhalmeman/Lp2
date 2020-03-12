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

class GUI extends JFrame{
    Container cp;
    JPanel pnA = new JPanel();
    JPanel pnB = new JPanel();
    JPanel pnC = new JPanel();
    JPanel pnD = new JPanel();
    JLabel lbTitulo = new JLabel("Algum título");
    JLabel lbNotas = new JLabel("Notas");
    JTextField tfNota1 = new JTextField(10);
    JTextField tfNota2 = new JTextField(10);
    JTextField tfNota3 = new JTextField(10);
    JTextField tfNota4 = new JTextField(10);
    JButton btCalcular = new JButton("Calcular");
    JLabel lbMedia = new JLabel("Média");
    JTextField tfMedia = new JTextField(10);
    
    public GUI() {
        cp= getContentPane();
        cp.setLayout(new GridLayout(4,1));
        
        cp.add(pnA);
        pnA.setBackground(Color.red);
        pnA.add(lbTitulo);
        
        cp.add(pnB);
        pnB.setBackground(Color.cyan);
        
        pnB.add(lbNotas);
        pnB.add(tfNota1);
        pnB.add(tfNota2);
        pnB.add(tfNota3);
        pnB.add(tfNota4);
        
        cp.add(pnC);
        pnC.setBackground(Color.yellow);
        pnC.add(btCalcular);
        cp.add(pnD);
        pnD.setBackground(Color.pink);
        pnD.add(lbMedia);
        pnD.add(tfMedia);
        
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700,300);
        setLocationRelativeTo(null);//centraliza
        setVisible(true);
        
        btCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double n1 = Double.valueOf(tfNota1.getText());
                double n2 = Double.valueOf(tfNota2.getText());
                double n3 = Double.valueOf(tfNota3.getText());
                double n4 = Double.valueOf(tfNota4.getText());
                
                double m = (n1+n2+n3+n4)/4;
                tfMedia.setText(String.valueOf(m));
            }
        });
        
    }
}