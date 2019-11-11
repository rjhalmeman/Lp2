package Main;

// @author Radames
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame {

    JPanel painelAviso = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel labelAviso = new JLabel();
    JButton buttonAutoSelecione = new JButton("Selecione no comboBox o [dois]");
    JButton buttonCapturar = new JButton("Capture o valor que está selecionado no comboBox");
    JButton buttonEnviarParaLista = new JButton("EnviarParaLista");
    JComboBox comboBox = new JComboBox();
    Container cp;

    public GUI() {
        setTitle("ComboBox");
        setSize(300, 200);//tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memória a classe
        setLayout(new GridLayout(5, 1));//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        cp = getContentPane();//container principal, para adicionar nele os outros componentes

        comboBox.addItem("um");
        comboBox.addItem("dois");
        comboBox.addItem("três");
        comboBox.addItem("quatro");

        cp.add(comboBox);
        cp.add(buttonAutoSelecione);
        cp.add(buttonCapturar);
        cp.add(buttonEnviarParaLista);
        painelAviso.add(labelAviso);
        cp.add(painelAviso);
        labelAviso.setOpaque(true);

        setLocationRelativeTo(null); // posiciona no centro da tela principal
        setVisible(true);//faz a janela ficar visível

        buttonEnviarParaLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filme filme = new Filme();
                filme.setId(1);
                filme.setNome((String)comboBox.getSelectedItem());
                
                System.out.println("Filme =>"+filme.toString());
                
                
                
                
            }
        });
        
        
        buttonAutoSelecione.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // comboBox.setSelectedIndex(2);
                comboBox.setSelectedItem("dois");   //3-curitiba          
            }
        });
        buttonCapturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(cp, (String) ("Valor que está selecionado no comboBox [" + comboBox.getSelectedItem() + "]"));
            }
        });

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    labelAviso.setText("mudou para o item [" + (String) item + "]");

                    labelAviso.setBackground(Color.yellow);
                }
            }
        });

        comboBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                labelAviso.setBackground(Color.GREEN);
            }
        });

    }
}
