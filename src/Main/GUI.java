
package Main;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author radames
 */
public class GUI extends JFrame {  

    Container cp;
    
    JLabel lbCpf = new JLabel("CPF");
    JTextField tfCpf = new JTextField(20);
    JLabel lbNome = new JLabel("Nome");
    JTextField tfNome = new JTextField(50);
    JLabel lbSalario = new JLabel("Salário");
    JTextField tfSalario = new JTextField(20);
    JButton btAdicionar = new JButton("Adicionar");
    JButton btListar = new JButton("Listar");
    
    
    Controle controle = new Controle();
    Trabalhador trabalhador = new Trabalhador();
    
    public GUI() {
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setSize(400,300);
        setTitle("CRUD Trabalhador");
        setLocationRelativeTo(null);//centro do monitor
        
        cp = getContentPane();
        cp.setLayout(new GridLayout(4,2));
        cp.add(lbCpf);
        cp.add(tfCpf);
        cp.add(lbNome);
        cp.add(tfNome);
        cp.add(lbSalario);
        cp.add(tfSalario);
        cp.add(btAdicionar);
        cp.add(btListar);
     
      
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trabalhador = new Trabalhador();
                trabalhador.setCpf(tfCpf.getText());
                trabalhador.setNome(tfNome.getText());
                trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                controle.adicionar(trabalhador);
            }
        });
        
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              controle.listar();
            }
        });
        
        setVisible(true);
                
    }
    
    
    
}
