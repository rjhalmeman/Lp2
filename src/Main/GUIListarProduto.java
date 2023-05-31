package Main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author radames
 */
class GUIListarProduto extends JDialog {
    
    Container cp;
    JTextArea taTexto = new JTextArea();
    JScrollPane jScrollPane = new JScrollPane(taTexto);
    
    public GUIListarProduto(ControleProduto controle, Point coordenadas, Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        //  setTitle("Relatório de produto");
        taTexto.setEditable(false);
        setUndecorated(true);//sem barra de título
        cp = getContentPane();
        cp.setLayout(new GridLayout(1, 1));
        
        cp.add(jScrollPane);
        taTexto.setText("");
        List<Produto> lp = controle.listar();
        for (Produto produto : lp) {
            taTexto.append(produto.toString() + System.lineSeparator());
        }
        
        taTexto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                dispose();//ao clicar no textArea, fecha o relatório
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
        
        setSize(dimensao);
        setLocation((int) coordenadas.getX(), (int) coordenadas.getY() + 30);

//        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
}
