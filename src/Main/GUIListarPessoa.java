package Main;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JTextArea;

/**
 *
 * @author radames
 */
class GUIListarPessoa extends JDialog {

    Container cp;
    JTextArea taTexto = new JTextArea();
    

    public GUIListarPessoa(Controle controle, Point xy, int altura, int largura) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Listagem de Pessoa");
         setModal(true);
        cp = getContentPane();
        cp.setLayout(new GridLayout(1, 1));
        
        cp.add(taTexto);
        taTexto.setText("");
        List<Pessoa> lp = controle.listar();
        for (Pessoa pessoa : lp) {
            taTexto.append(pessoa.toString()+"\n");
        }
        
        setLocation((int)xy.getX(), (int)xy.getY()+altura);
        setSize(largura, 100);
        setVisible(true);
       
    }
    
}
