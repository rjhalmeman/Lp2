package Main;

import Geradores.GeradorDeEstruturas;
import Geradores.GerarClasseMain;
import Geradores.GerarControle;
import Geradores.GerarEntidade;
import Geradores.GerarGUI;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import tools.CentroDoMonitorMaior;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GUIGerador extends JFrame {

    Container cp;
    JButton btGerarClasseDeEntidade = new JButton("Entidade");
    JButton btGerarClasseDeControle = new JButton("Controle");
    JButton btGerarEstrutura = new JButton("Estrutura");
    JButton btGerarGUI = new JButton("GUI");
    JButton btGerarClasseMain = new JButton("classe Main");
    JPanel pnDestino = new JPanel();
    JLabel lbDestino = new JLabel("Destino");
    JLabel lbProjetoDestino = new JLabel("destino");
    JPanel pnOrigem = new JPanel();
    JLabel lbOrigem = new JLabel("Origem e atributos");
    JButton btAbrirDescricaoAtributos = new JButton("Atributos");
//    JButton btOrigem = new JButton("Origem");
    StringTools st = new StringTools();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    String nomeClasse = "";
    //usar um JFileChooser para escolher essa pasta
    String caminhoProjetoDestino = "";
    String caminhoDoTxtComAtributos = "";
    JFileChooser caixaDeDialogo = new JFileChooser();
    List<String> atributo = new ArrayList<>();

    public GUIGerador() {

        List<String> ultimo = manipulaArquivo.abrirArquivo("src/ultimoArquivoUsado.txt");

        nomeClasse = manipulaArquivo.getNomeDoArquivo(ultimo.get(0));
        if (nomeClasse == null) {
            nomeClasse = "";
        }
        nomeClasse = nomeClasse.substring(0, nomeClasse.length() - 4); //tira o .txt
        nomeClasse = st.plMaiusc(nomeClasse); //Garantindo que a primeira letra da classe é Maiúscula

        //    System.out.println("ultimo " + ultimo.get(0)); //mostra a primeira linha do arquivo
        //    System.out.println("nomeClasse " + nomeClasse);
        //   vai buscar os atributos em um arquivo texto
        caminhoDoTxtComAtributos = ultimo.get(0);
        
        btAbrirDescricaoAtributos.setText(caminhoDoTxtComAtributos);

        atributo = manipulaArquivo.abrirArquivo(caminhoDoTxtComAtributos); //abre o ultimo arquivo

        caminhoProjetoDestino = ultimo.get(1);//nome do projeto de destino
        if (atributo == null) {
            //file chooser direto
        }

//        System.exit(0);
        btAbrirDescricaoAtributos.setText(caminhoDoTxtComAtributos);
        lbProjetoDestino.setText(caminhoProjetoDestino);

        lbProjetoDestino.setFont(new Font("Arial", Font.BOLD, 14)); // Define a fonte
        lbProjetoDestino.setForeground(Color.BLACK); // Define a cor do texto
        lbProjetoDestino.setHorizontalAlignment(SwingConstants.CENTER); // Alinhamento de texto no centro

        // Adicione uma borda 3D ao JLabel
        lbProjetoDestino.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        // Adicione um efeito de sombreamento
        lbProjetoDestino.setOpaque(true);
        lbProjetoDestino.setBackground(new Color(120, 120, 120)); // Cor de fundo

        //GerarControle gerarControle = new GerarControle(atributo,nomeClasse,caminhoDoProjetoDestino);
        setTitle("Gerador de CRUD dos WebGartner");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new GridLayout(7, 1));

        cp.add(pnOrigem);
        pnOrigem.add(lbOrigem);
        pnOrigem.add(btAbrirDescricaoAtributos);

        cp.add(pnDestino);
        pnDestino.add(lbDestino);
        pnDestino.add(lbProjetoDestino);

        pnOrigem.setBackground(Color.yellow);
        pnDestino.setBackground(Color.cyan);

        cp.add(btGerarEstrutura);
        cp.add(btGerarClasseDeEntidade);
        cp.add(btGerarClasseDeControle);
        cp.add(btGerarGUI);
        cp.add(btGerarClasseMain);

        lbProjetoDestino.addMouseListener(new MouseAdapter() {
            private int clickCount = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Verifica se foi um clique

                    clickCount = 0; // Reseta o contador para futuros duplos cliques

//                        File up = new File(".");
                    File up = new File(caminhoProjetoDestino);

                    caixaDeDialogo.setCurrentDirectory(up);
                    caixaDeDialogo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                    if (caixaDeDialogo.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                        caminhoProjetoDestino = caixaDeDialogo.getSelectedFile().getAbsolutePath();
                        lbProjetoDestino.setText(caminhoProjetoDestino);
                    }

                }
            }
        });

        btAbrirDescricaoAtributos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File up = new File(caminhoDoTxtComAtributos);

                caixaDeDialogo.setCurrentDirectory(up);
                caixaDeDialogo.setFileSelectionMode(JFileChooser.FILES_ONLY);

                if (caixaDeDialogo.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                    caminhoDoTxtComAtributos = caixaDeDialogo.getSelectedFile().getAbsolutePath();
                    btAbrirDescricaoAtributos.setText(caminhoDoTxtComAtributos);

                    //carregar os dados
                    atributo.clear();
                    atributo = manipulaArquivo.abrirArquivo(caminhoDoTxtComAtributos);
                    ultimo.set(0, caminhoDoTxtComAtributos+System.lineSeparator());
                    manipulaArquivo.salvarArquivo("src/ultimoArquivoUsado.txt", ultimo);//salva para a próxima execução

                }
            }
        });

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
        btGerarClasseDeControle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GerarControle(atributo, nomeClasse, caminhoProjetoDestino);
            }
        });
        btGerarGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GerarGUI(atributo, nomeClasse, caminhoProjetoDestino);
            }
        });
        btGerarClasseMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GerarClasseMain(atributo, nomeClasse, caminhoProjetoDestino);
            }
        });

        setSize(800, 300);
        setLocation(new CentroDoMonitorMaior().getCentroMonitorMaior(this));
        setVisible(true);
    }

}
