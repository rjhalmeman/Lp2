package Main;

import Geradores.GeradorDeEstruturas;
import Geradores.GerarEntidade;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GUIGerador extends JFrame {

    Container cp;
    JButton btGerarClasseDeEntidade = new JButton("Entidade");
    JButton btGerarEstrutura = new JButton("Estrutura");
    JButton btAbrirDescricaoAtributos = new JButton("Atributos");
    JLabel lbProjetoDestino = new JLabel("destino");
    StringTools st = new StringTools();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    String nomeClasse = "";
    //usar um JFileChooser para escolher essa pasta
    String caminhoProjetoDestino = "/home/radames/NetBeansProjects/AAABBB";
    List<String> atributo = new ArrayList<>();

    public GUIGerador() {

        List<String> ultimo = manipulaArquivo.abrirArquivo("src/ultimoArquivoUsado.txt");

        nomeClasse = manipulaArquivo.getNomeDoArquivo(ultimo.get(0));
        if (nomeClasse == null) {
            nomeClasse = "";
        }
        nomeClasse = nomeClasse.substring(0, nomeClasse.length() - 4); //tira o .txt
        nomeClasse = st.plMaiusc(nomeClasse); //Garantindo que a primeira letra da classe é Maiúscula

        System.out.println("ultimo " + ultimo.get(0)); //mostra a primeira linha do arquivo
        System.out.println("nomeClasse " + nomeClasse);

        atributo = manipulaArquivo.abrirArquivo(ultimo.get(0)); //abre o ultimo arquivo

        //   vai buscar os atributos em um arquivo texto 

//     atributo.add("String;cpf;10");
//     atributo.add("String;nome;50");
//     atributo.add("Date;dataNascimento;10");


        if (atributo == null) {
            //file chooser direto
        }

//        System.exit(0);
        lbProjetoDestino.setText(caminhoProjetoDestino);

        //GerarControle gerarControle = new GerarControle(atributo,nomeClasse,caminhoDoProjetoDestino);
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

                new GerarEntidade(atributo, nomeClasse, caminhoProjetoDestino);
            }
        });

        setSize(800, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
