
package Main;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author radames
 */
class GUI extends JFrame{
    Container cp;
    public GUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Título da janela");
        
        cp = getContentPane();
        cp.setLayout(new GridLayout(3,2));
        
        
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    
    
}
