
package Main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JTextArea;

/**
 *
 * @author radames
 */
class GUIListarProduto extends JDialog {
    Container cp;
    JTextArea taTexto = new JTextArea();
   
    
    public GUIListarProduto(Controle controle, Point coordenadas, Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Relatório de produto");
        cp = getContentPane();
        cp.setLayout(new GridLayout(1,1));
        
        cp.add(taTexto);
        taTexto.setText("");
        List<Produto> lp = controle.listar();
        for (Produto produto : lp) {
            taTexto.append(produto.toString()+System.lineSeparator());
        }     
        setModal(true);
        
        setSize(dimensao);
        setLocation((int)coordenadas.getX(),(int)coordenadas.getY()+dimensao.height);
        
//        setLocationRelativeTo(null);
        setVisible(true);    
        
    }
    
}
