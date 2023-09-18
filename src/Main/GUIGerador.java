package Main;

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
        String caminhoProjetoDestino = "/home/radames/NetBeansProjects/AAABBB";

        lbProjetoDestino.setText(caminhoProjetoDestino);

        //  GerarEntidade gerarEntidade = new GerarEntidade(atributo,nomeClasse);
        //  GerarControle gerarControle = new GerarControle(atributo,nomeClasse,caminhoDoProjetoDestino);
        
        
        
        
        setTitle("Gerador de CRUD dos WebGartner");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new GridLayout(6, 1));

        cp.add(lbProjetoDestino);
        cp.add(btGerarEstrutura);

        
        
        
        btGerarEstrutura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = lbProjetoDestino.getText();
                List<String> listaString = new ArrayList<>();
                listaString.clear();
                listaString.add("/src/" + "Entidades");
                listaString.add("/src/" + "Controles");
                listaString.add("/src/" + "GUIs");
                listaString.add("/src/" + "tools");
                listaString.add("/src/" + "icones");
                listaString.add("/src/" + "Main");

                for (String pacote : listaString) {
                    File pac = new File(src + pacote);
                    if (!pac.exists()) {
                        new File(src + pacote).mkdir();//cria as pastas
                    }
                }

                //copiar pacote de icones
                File listaIcones = new File("src/icones");

                if (listaIcones.exists()) {
                    File[] arqs = listaIcones.listFiles();
                    CopiarArquivos copiarArquivos = new CopiarArquivos();
                    for (int i = 0; i < arqs.length; i++) {
                      //  System.out.print(" A origem " + arqs[i].getAbsolutePath() + "  ---  ");
                      //  System.out.println("destino " + caminhoProjetoDestino + "/src" + "/icones/" + arqs[i].getName());
                        copiarArquivos.copiar(arqs[i].getAbsolutePath(),
                                caminhoProjetoDestino + "/src" + "/icones/" + arqs[i].getName());
                    }
                }

                //copiar pacote de ferramentas
                File listaFerramentas = new File("src/tools");
                if (listaFerramentas.exists()) {
                    File[] arqs = listaFerramentas.listFiles();
                    CopiarArquivos copiarArquivos = new CopiarArquivos();
                    for (int i = 0; i < arqs.length; i++) {
                        copiarArquivos.copiar(arqs[i].getAbsolutePath(),
                                caminhoProjetoDestino + "/src" + "/tools/" + arqs[i].getName());
                    }

                }

            }
        });

        setSize(800, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
