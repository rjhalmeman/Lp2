
package MyUtil;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author radames
 */
public class ImagemAjustada {

    ImageIcon icone;

    public ImageIcon getImagemAjustada(int largura, int altura, String caminhoDaImagem) {
        //para ajustar o tamanho de uma imagem
        ImageIcon icon = null;

        try {
            Image image = new ImageIcon(caminhoDaImagem).getImage();
            Image scaledImage = image.getScaledInstance(largura, altura, Image.SCALE_FAST);
            icon = new ImageIcon(scaledImage);
        } catch (Exception e) {
            icon = null;
        }
        return icon;
    }

}
