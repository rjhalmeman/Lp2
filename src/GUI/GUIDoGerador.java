package GUI;

import Controle.GerarClasseDeControle;
import Controle.GerarClasseDeEntidade;
import Controle.GerarClasseGUI;
import Controle.GerarClasseMain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import tools.CopiarArquivos;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class GUIDoGerador extends JFrame {

    private Container cp;
    private final JPanel pnNorte = new JPanel();
    private final JPanel pnCentro = new JPanel();
    private final JPanel pnCentroN = new JPanel();
    private final JPanel pnCentroC = new JPanel();

    private final JPanel pnSul = new JPanel();
    private final JLabel lbDestino = new JLabel("Projeto destino");
    private JTextField tfDestino = new JTextField(50);
    private final JButton btEscolherDestino = new JButton("Escolher");
    private final JButton btGerarEstrutura = new JButton("Gerar Estrutura");
    private final JLabel lbNomeClasse = new JLabel("Nome da classe de entidade");
    private final JTextField tfNomeClasse = new JTextField(50);
    private final JTextArea txtArea = new JTextArea();
    private final JScrollPane barraDeRolagem = new JScrollPane(txtArea);

    private final JButton btGerarEntidade = new JButton("Entidade");
    private final JButton btGerarControle = new JButton("Controle");
    private final JButton btGerarGUI = new JButton("GUI");
    private final JButton btGerarMain = new JButton("Main");
    private final JButton btGerarTodos = new JButton("Todos");
    private final JButton btAbrirEntidade = new JButton("Abrir");
    private final JButton btSalvarEntidade = new JButton("Salvar");
    private JFileChooser caixaDeDialogo = new JFileChooser();
    private String caminho = "";
    private ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    private List<String> texto = new ArrayList();

    public GUIDoGerador() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.CYAN);
        pnCentro.setBackground(Color.LIGHT_GRAY);
        pnSul.setBackground(Color.gray);

        pnNorte.add(lbDestino);
        pnNorte.add(tfDestino);
        pnNorte.add(btEscolherDestino);
        pnNorte.add(btGerarEstrutura);

        pnCentro.setLayout(new BorderLayout());

        pnCentro.add(pnCentroN, BorderLayout.NORTH);
        pnCentro.add(pnCentroC, BorderLayout.CENTER);
        pnCentroN.setBackground(Color.YELLOW);
        pnCentroC.setBackground(Color.white);

        pnCentroN.add(lbNomeClasse);
        pnCentroN.add(tfNomeClasse);
        pnCentroN.add(btAbrirEntidade);
        pnCentroN.add(btSalvarEntidade);

        pnCentroC.setLayout(new GridLayout(1, 1));//força o textArea a ocupar todo o espaço

        txtArea.setForeground(Color.BLUE);
        txtArea.setFont(new Font("Verdana", Font.BOLD, 14));
        barraDeRolagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.txtArea.setEditable(true);

        pnCentroC.add(barraDeRolagem); //É aqui que coloco no meu Painel

        pnSul.setLayout(new GridLayout(1, 6));
        pnSul.add(new JLabel("Gerar =>"));
        pnSul.add(btGerarEntidade);
        pnSul.add(btGerarControle);
        pnSul.add(btGerarGUI);
        pnSul.add(btGerarMain);
        pnSul.add(btGerarTodos);

        //ao iniciar, carrega o último projeto
        texto = manipulaArquivo.abrirArquivo("ProjetoEscolhido.txt");
        if (texto.size() > 0) {
            caminho = texto.get(0);
            tfDestino.setText(caminho);
        }

        btEscolherDestino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("DIRETÓRIO", "..", "..");
                caixaDeDialogo.setFileFilter(filter);
                caixaDeDialogo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                File file = new File(caminho);
                if (file.exists()) {
                    caixaDeDialogo.setCurrentDirectory(file);
                } else {
                    file = new File("/home/radames/NetBeansProjects");
                    if (file.exists()) {
                        caixaDeDialogo.setCurrentDirectory(file);
                    } else {
                        caixaDeDialogo.setCurrentDirectory(null);
                    }

                }
                if (caixaDeDialogo.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                    caminho = caixaDeDialogo.getSelectedFile().getAbsolutePath();
                    tfDestino.setText(caminho);
                    texto.clear();
                    texto.add(caminho);
                    manipulaArquivo.salvarArquivo("ProjetoEscolhido.txt", texto);
                }
            }

        });

        //criar packages
        btGerarEstrutura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = tfDestino.getText();
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
//                        System.out.print("origem " + arqs[i].getAbsolutePath() + "  ---  ");
//                        System.out.println("destino " + caminho + "/src" + ww + "/myUtil/" + arqs[i].getName());
                        copiarArquivos.copiar(arqs[i].getAbsolutePath(),
                                caminho + "/src" + "/icones/" + arqs[i].getName());
                    }
                }

                //copiar pacote de ferramentas
                File listaFerramentas = new File("src/tools");
                if (listaFerramentas.exists()) {
                    File[] arqs = listaFerramentas.listFiles();
                    CopiarArquivos copiarArquivos = new CopiarArquivos();
                    for (int i = 0; i < arqs.length; i++) {
                        copiarArquivos.copiar(arqs[i].getAbsolutePath(),
                                caminho + "/src" + "/tools/" + arqs[i].getName());
                    }

                }

            }
        });

        btSalvarEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = txtArea.getText();
                StringTokenizer st = new StringTokenizer(s, System.lineSeparator());
                texto.clear();
                while (st.hasMoreTokens()) {
                    String line = st.nextToken();
                    line = line.trim();
                    if (!line.equals("")) {//para evitar que sejam gravadas linhas vazias
                        texto.add(line + System.lineSeparator());
                    }

                }
                manipulaArquivo.salvarArquivo("src/txts/" + tfNomeClasse.getText() + ".txt", texto);
            }
        });
        btAbrirEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("src/txts/" + tfNomeClasse.getText() + ".txt");
                texto.clear();
                txtArea.setText("");
                if (file.exists()) {
                    texto = manipulaArquivo.abrirArquivo("src/txts/" + tfNomeClasse.getText() + ".txt");
                    for (String string : texto) {
                        txtArea.append(string + System.lineSeparator());
                    }
                }
                // System.out.println("linhas "+texto.size());
            }

        });
        tfNomeClasse.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                tfNomeClasse.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) {
                String s = tfNomeClasse.getText();
                if (!s.trim().equals("")) {
                    String nomeAjustado = String.valueOf(s.charAt(0)).toUpperCase()
                            + s.substring(1, s.length());
                    tfNomeClasse.setText(nomeAjustado);
                }
                tfNomeClasse.setBackground(Color.white);
            }
        });

        btGerarEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNomeClasse.getText();
                btSalvarEntidade.doClick();
                caminho = tfDestino.getText();
                String oArquivo = "src/txts/" + tfNomeClasse.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseDeEntidade gerarClasseDeEntidade = new GerarClasseDeEntidade(nomeDaClasse, atributo, caminho);
                }

            }
        });
        btGerarControle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNomeClasse.getText();
                btSalvarEntidade.doClick();
                caminho = tfDestino.getText();
                String oArquivo = "src/txts/" + tfNomeClasse.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseDeControle gerarClasseDeEntidade = new GerarClasseDeControle(nomeDaClasse, atributo, caminho);
                }

            }
        });
        btGerarGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNomeClasse.getText();
                btSalvarEntidade.doClick();
                caminho = tfDestino.getText();
                String oArquivo = "src/txts/" + tfNomeClasse.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseGUI gerarClasseDeEntidade = new GerarClasseGUI(nomeDaClasse, atributo, caminho);
                }

            }
        });
        btGerarMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNomeClasse.getText();
                btSalvarEntidade.doClick();
                caminho = tfDestino.getText();
                String oArquivo = "src/txts/" + tfNomeClasse.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseMain gerarClasseDeEntidade = new GerarClasseMain(nomeDaClasse, atributo, caminho);
                }
            }
        });

        btGerarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btGerarEntidade.doClick();
                btGerarControle.doClick();
                btGerarGUI.doClick();
                btGerarMain.doClick();            
            }
        });

        setTitle("Gerador de código Iguana");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
