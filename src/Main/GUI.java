package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author radames
 */
public class GUI extends JFrame {

    private final Container cp;
    private final JPanel pnNorte = new JPanel();
    private final JPanel pnCentro = new JPanel();
    private final JPanel pnSul = new JPanel();

    private final JLabel lbData = new JLabel("Data");
    
    
    //para usar o DateTextField tem que adicionar ao projeto a classe DateTextField.java (basta copiar essa classe de outro projeto)
    DateTextField tfDataInicial = new DateTextField();
    
    private final JButton btVai = new JButton("=>");
    private final JTextField tfDataFinal = new JTextField(10);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public GUI() {
        sdf.setLenient(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //muda a cor dos painéis para que possamos vê-los
        pnNorte.setBackground(Color.green);
        pnCentro.setBackground(Color.CYAN);
        pnSul.setBackground(Color.blue);

        cp = getContentPane(); //captura o painel de conteúdos
        cp.setLayout(new BorderLayout());
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.add(lbData);
        pnNorte.add(tfDataInicial);
        pnNorte.add(btVai);
        pnNorte.add(tfDataFinal);

        setTitle("Exemplo com DATE");
        setSize(500, 200);

        btVai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date data = new Date();//pega a data e hora do sistema

                try {
                    data = sdf.parse(tfDataInicial.getText());
                    SimpleDateFormat sdfEUA = new SimpleDateFormat("yyyy-MM-dd");
                    tfDataFinal.setText(sdfEUA.format(data));
                } catch (ParseException ex) {
                    System.out.println("Erro na data");
                }

            }
        });

        setLocationRelativeTo(null);//posiciona no meio da tela
        setVisible(true);//faz a janela ficar visível

    }
}
