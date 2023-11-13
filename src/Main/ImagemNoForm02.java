
package Main;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author radames
 */

public class ImagemNoForm02 extends JFrame {  
  
    private DesktopPaneFundo desk;  
    private JScrollPane scroll;  
    private JInternalFrame jan;  
  
    public ImagemNoForm02() {  
        super();  
          
        desk = new DesktopPaneFundo();  
        scroll = new JScrollPane(desk);  
        getContentPane().add(scroll,BorderLayout.CENTER);  
          
        jan = new JInternalFrame();  
        jan.setSize(300,300);  
        jan.setTitle("Janela 01");       
        jan.setVisible(true);  
        jan.setLocation(10, 10);  
        desk.add(jan);       
        
        jan = new JInternalFrame();  
        jan.setSize(300,300);  
        jan.setTitle("Janela 2");       
        jan.setVisible(true);  
        jan.setLocation(20, 20);  
        desk.add(jan);                  
        setSize(500,500);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setVisible(true);  
        setLocationRelativeTo(null);  
    }  
  
      
    public static void main(String[] args) {  
        new ImagemNoForm02();  
    }  
}  
  
  
  
class DesktopPaneFundo extends JDesktopPane  
{  
  Image img;  
  public DesktopPaneFundo()  
  {  
    try  
    {  
     String   caminho = "lua.png";
      img = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(caminho), caminho));  
    }  
    catch(Exception e){}//do nothing  
  }  
  public void paintComponent(Graphics g)  
  {  
    super.paintComponent(g);  
    if(img != null) g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);  
    else g.drawString("Image not found", 50,50);  
  }  
}  
