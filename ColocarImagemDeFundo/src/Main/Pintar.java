package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author radames
 */
public class Pintar extends JFrame {

    JLabel rotulo;
    Container cp;

    public Pintar() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = ge.getScreenDevices();
        int largura = devices[0].getDisplayMode().getWidth();
        int altura = devices[0].getDisplayMode().getHeight();

        cp = getContentPane();
        cp.setLayout(new BorderLayout());

        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/icones/comilao.jpg"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(largura, altura, Image.SCALE_FAST));

            rotulo = new JLabel();
            rotulo.setIcon(icone);
        } catch (Exception e) {
            System.out.println("erro ao carregar a imagem");
        }

        // setMaximizedBounds(getMaximizedBounds());
        setSize(largura, altura);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        // rotulo.setFont((new Font("Arial", Font.BOLD, 86)));

        cp.add(rotulo);
    }

    public static void main(String[] args) {
        new Pintar();
    }

}
