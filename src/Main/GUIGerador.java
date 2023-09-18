package Main;

import Geradores.GeradorDeEstruturas;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tools.CopiarArquivos;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GUIGerador extends JFrame {

    Container cp;
    JButton btGerarEstrutura = new JButton("Estrutura");
    JButton btGerarClasseDeEntidade = new JButton("Entidade");
    JLabel lbProjetoDestino = new JLabel("destino");

    public GUIGerador() {

        StringTools st = new StringTools();
        List<String> atributo = new ArrayList<>();

        atributo.add("String;cpf;20");
        atributo.add("String;nome;30");
        atributo.add("Date;dataNascimento;10");
        atributo.add("double;peso;5");
        atributo.add("double;altura;5");

        String nomeClasse = "Pessoa";
        
        
        //usar um JFileChooser para escolher essa pasta
        String caminhoProjetoDestino = "/home/radames/NetBeansProjects/AAABBB";

        lbProjetoDestino.setText(caminhoProjetoDestino);
        
        //  GerarControle gerarControle = new GerarControle(atributo,nomeClasse,caminhoDoProjetoDestino);
        
        
        setTitle("Gerador de CRUD dos WebGartner");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new GridLayout(6, 1));

        cp.add(lbProjetoDestino);
        cp.add(btGerarEstrutura);
        cp.add(btGerarClasseDeEntidade);
        

        btGerarEstrutura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeradorDeEstruturas geradorDeEstruturas = new GeradorDeEstruturas(caminhoProjetoDestino);
            }
        });
        
        btGerarClasseDeEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerarEntidade gerarEntidade = new GerarEntidade(atributo,nomeClasse,caminhoProjetoDestino);
            }
        });
        
        setSize(800, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
