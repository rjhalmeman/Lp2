
package GUI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author radames
 */
public class MenuPrincipal extends JFrame {
    private Container cp;
    private JButton btUnidadeDeMedida = new JButton("Unidade de Medida");
    private JButton btProduto = new JButton("Produto");
    public MenuPrincipal() {
        cp= getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Sistema de Estoque do Salin - V00");
        
        cp.setLayout(new GridLayout(2,1));
        
        cp.add(btUnidadeDeMedida);
        cp.add(btProduto);
        
        
        btUnidadeDeMedida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIUnidadeDeMedida guiUnidadeDeMedida = new GUIUnidadeDeMedida();
            }
        });
        btProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIProduto guiProduto = new GUIProduto();
            }
        });
        
        setSize(350,200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    
}
