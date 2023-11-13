package GUIs;

import Controles.CorDosOlhosControle;
import Entidades.Pessoa;
import Controles.PessoaControle;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.net.URI;
import javax.swing.BorderFactory;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import tools.CaixaDeFerramentas;
import tools.DateTextField;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import tools.CentroDoMonitorMaior;
import tools.DiretorioDaAplicacao;
import tools.ImagemAjustada;
import tools.CopiarArquivos;
import tools.JanelaPesquisar;

/**
 *
 * @author belly 05/12/2022 - 15:32:59
 */
public class PessoaGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JPanel pnLeste = new JPanel(new BorderLayout());
    JPanel pnLesteA = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel pnLesteB = new JPanel(new GridLayout(1, 1));
    JPanel pnFK = new JPanel(new GridLayout(1, 2));
    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btFechar = new JButton("Fechar");
    JButton btCancelar = new JButton("Cancelar");
    JCheckBox cbMorto = new JCheckBox("Morto", false);
    JButton btAdicionarFoto = new JButton("Adicionar/alterar foto");
    JButton btRemoverFoto = new JButton("Remover");
    JButton btBuscarImgOnline = new JButton("Imagem Online");
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

//////////////////// - mutável - /////////////////////////
    JLabel lbId = new JLabel("RA:");
    JTextField tfId = new JTextField(10);
    JLabel lbNome = new JLabel("NOME:");
    JTextField tfNome = new JTextField(20);
    JLabel lbDatanascimento = new JLabel("Data de Nascimento:");
    DateTextField tfDatanascimento = new DateTextField();
    JLabel lbCaminhoFt = new JLabel();
    JLabel lbFt = new JLabel();
    JLabel lbCorDosOlhos = new JLabel(" Cor dos Olhos");
    JButton localizarCorDosOlhos = new JButton("Localizar");
    JTextField tfCorDosOlhos = new JTextField(20);
    PessoaControle controle = new PessoaControle();
    Pessoa pessoa = new Pessoa();
    String[] colunas = new String[]{"id", "nome", "datanascimento", "morto", "ft", "CorDosOlhos"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
    ImagemAjustada imagemAjustada = new ImagemAjustada();
    DiretorioDaAplicacao diretorioDaAplicacao = new DiretorioDaAplicacao();
    String dirApp = diretorioDaAplicacao.getDiretorioDaAplicacao();
    String origem = dirApp + "/src/fotos/silhueta_pessoa.png";
    int tamX = 150;
    int tamY = 150;
    String temFoto = "";

    public PessoaGUI() {
        CopiarArquivos copiarArquivos = new CopiarArquivos();
        lbFt.setIcon(imagemAjustada.getImagemAjustada(origem, tamX, tamY));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Pessoa");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        cp.add(pnLeste, BorderLayout.EAST);

        pnLeste.add(pnLesteA, BorderLayout.NORTH);
        pnLeste.add(pnLesteB, BorderLayout.CENTER);
        pnLesteA.add(btAdicionarFoto);
        pnLesteA.add(btBuscarImgOnline);
        pnLesteA.add(btRemoverFoto);
        pnLesteB.add(lbFt);
        btAdicionarFoto.setVisible(false);
        btBuscarImgOnline.setVisible(false);
        btRemoverFoto.setVisible(false);
        pnNorte.add(lbId);
        pnNorte.add(tfId);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btFechar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.setBackground(new Color(255, 51, 102));
        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btFechar.setVisible(false);
        btCancelar.setVisible(false);
        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbDatanascimento);
        pnCentro.add(tfDatanascimento);
        //pnCentro.add(new JLabel(""));
        pnCentro.add(cbMorto);
        pnCentro.add(lbCaminhoFt);
      //  pnCentro.add(new JLabel(""));
        pnFK.add(lbCorDosOlhos);
        pnFK.add(localizarCorDosOlhos);
        pnCentro.add(pnFK);
        pnCentro.add(tfCorDosOlhos);
        lbId.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfId.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbId.setBorder(BorderFactory.createLineBorder(Color.black));
        tfId.setBorder(BorderFactory.createLineBorder(Color.black));
        lbNome.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfNome.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbNome.setBorder(BorderFactory.createLineBorder(Color.black));
        tfNome.setBorder(BorderFactory.createLineBorder(Color.black));
        lbDatanascimento.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfDatanascimento.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbDatanascimento.setBorder(BorderFactory.createLineBorder(Color.black));
        tfDatanascimento.setBorder(BorderFactory.createLineBorder(Color.black));
        cbMorto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        cbMorto.setBorder(BorderFactory.createLineBorder(Color.black));
        cbMorto.setBackground(new Color(255, 51, 102));
        lbCaminhoFt.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbCaminhoFt.setBorder(BorderFactory.createLineBorder(Color.black));
        lbCorDosOlhos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfCorDosOlhos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        localizarCorDosOlhos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        localizarCorDosOlhos.setBorder(BorderFactory.createLineBorder(Color.black));
        localizarCorDosOlhos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbId.setBackground(new Color(255, 51, 102));
        lbId.setForeground(Color.BLACK);
        lbNome.setBackground(new Color(255, 51, 102));
        lbNome.setForeground(Color.BLACK);
        lbDatanascimento.setBackground(new Color(255, 51, 102));
        lbDatanascimento.setForeground(Color.BLACK);
        cbMorto.setForeground(Color.BLACK);
        lbCaminhoFt.setBackground(new Color(255, 51, 102));
        lbCaminhoFt.setForeground(Color.BLACK);
        btAdicionarFoto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btRemoverFoto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btBuscarImgOnline.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAdicionarFoto.setBorder(BorderFactory.createLineBorder(Color.black));
        btRemoverFoto.setBorder(BorderFactory.createLineBorder(Color.black));
        btBuscarImgOnline.setBorder(BorderFactory.createLineBorder(Color.black));
        pnFK.setBackground(new Color(255, 51, 102));
        pnFK.setForeground(Color.BLACK);
        pnFK.setForeground(Color.BLACK);
        pnCentro.setBackground(new Color(255, 51, 102));
        btBuscar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAdicionar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btSalvar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAlterar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btExcluir.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btListar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btFechar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btCancelar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btBuscar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAdicionar.setBorder(BorderFactory.createLineBorder(Color.black));
        btSalvar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAlterar.setBorder(BorderFactory.createLineBorder(Color.black));
        btExcluir.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btFechar.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btBuscar.setBackground(Color.white);
        btCancelar.setBackground(Color.white);
        btAdicionar.setBackground(Color.white);
        btSalvar.setBackground(Color.white);
        btAlterar.setBackground(Color.white);
        btExcluir.setBackground(Color.white);
        btListar.setBackground(Color.white);
        btFechar.setBackground(Color.white);
        tabela.setFont(new Font("Times New Rowman", Font.PLAIN, 18));
        tabela.setRowHeight(40);
        tfNome.setEditable(false);
        tfDatanascimento.setText("");
        tfDatanascimento.setEditable(false);
        cbMorto.setEnabled(false);
        tfCorDosOlhos.setEditable(false);
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));
        String caminho = "src/Pessoa.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pessoa = controle.buscar(tfId.getText());
                    if (pessoa != null) {//achou o pessoa na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        cbMorto.setSelected(pessoa.isMorto());
                        tfNome.setText(pessoa.getNome());
                        tfNome.setEditable(false);
                        tfNome.setEnabled(true);
                        tfDatanascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(pessoa.getDatanascimento()));
                        tfDatanascimento.setEditable(false);
                        tfCorDosOlhos.setText(String.valueOf(pessoa.getCorDosOlhos()));
                        tfCorDosOlhos.setEditable(false);
                        tfCorDosOlhos.setEnabled(true);
                        String c = dirApp + "/src/fotos/" + pessoa.getId() + ".png";

                        if (pessoa.getFt().equals("Sim")) { //tem foto
                            ImageIcon ii = imagemAjustada.getImagemAjustada(c, tamY, tamY);
                            lbFt.setIcon(ii);
                        } else {
                            c = dirApp + "/src/fotos/silhueta.png";
                            ImageIcon ii = imagemAjustada.getImagemAjustada(c, tamY, tamY);
                            lbFt.setIcon(ii);
                        }
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfNome.setEditable(false);
                        tfDatanascimento.setText("");
                        tfDatanascimento.setEditable(false);
                        tfCorDosOlhos.setText("");
                        tfCorDosOlhos.setEditable(false);
                        ImageIcon ii = imagemAjustada.getImagemAjustada(dirApp + "/src/fotos/silhueta_pessoa.png", tamX, tamY);
                        lbFt.setIcon(ii);

                        lbCaminhoFt.setText(dirApp + "/src/fotos/silhueta.png");
                    }
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Chave Inválida", "Erro Ao Buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Adicionar
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfId.setEnabled(false);
                tfNome.requestFocus();
                tfNome.setEditable(true);
                tfNome.setEnabled(true);
                tfDatanascimento.setEditable(true);
                tfDatanascimento.setEnabled(true);
                cbMorto.setEnabled(true);
                tfCorDosOlhos.setEditable(true);
                tfCorDosOlhos.setEnabled(true);
                btAdicionarFoto.setVisible(true);
                btRemoverFoto.setVisible(true);
                btBuscarImgOnline.setVisible(true);
                temFoto = "Não";
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

// listener Salvar
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (acao.equals("adicionar")) {
                        pessoa = new Pessoa();
                    }
                    Pessoa pessoaAntigo = pessoa;
                    pessoa.setId(tfId.getText());
                    pessoa.setNome(tfNome.getText());
                    try {
                        pessoa.setDatanascimento(new SimpleDateFormat("dd/MM/yyyy").parse(tfDatanascimento.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pessoa.setMorto(cbMorto.isSelected());
                    pessoa.setCorDosOlhos(tfCorDosOlhos.getText());
                    pessoa.setFt(temFoto);
                    //copiar a foto da origem para a pasta destino no projeto
                    String destino = dirApp + "/src/fotos/";
                    destino = destino + pessoa.getId() + ".png";
//                    System.out.println("origem =>" + origem);
//                    System.out.println("destino =>" + destino);

                    copiarArquivos.copiar(lbCaminhoFt.getText(), destino);
                    if (acao.equals("adicionar")) {
                        controle.adicionar(pessoa);
                    } else {
                        controle.alterar(pessoa, pessoaAntigo);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfId.setEnabled(true);
                    tfId.setEditable(true);
                    tfId.requestFocus();
                    tfId.setText("");
                    tfNome.setEnabled(false);
                    tfNome.setEditable(false);
                    tfNome.setText("");
                    tfDatanascimento.setEnabled(false);
                    tfDatanascimento.setEditable(false);
                    tfDatanascimento.setText("");
                    cbMorto.setEnabled(false);
                    cbMorto.setSelected(false);
                    tfCorDosOlhos.setEnabled(false);
                    tfCorDosOlhos.setEditable(false);
                    tfCorDosOlhos.setText("");
                    btAdicionarFoto.setVisible(false);
                    btRemoverFoto.setVisible(false);
                    btBuscarImgOnline.setVisible(false);
                    ImageIcon ii = imagemAjustada.getImagemAjustada(dirApp + "/src/fotos/silhueta_pessoa.png", tamX, tamY);
                    lbFt.setIcon(ii);

                    lbCaminhoFt.setText(dirApp + "/src/fotos/silhueta.png");
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Tente Novamente", "Erro Ao Salvar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfId.setEditable(false);
                tfNome.requestFocus();
                tfNome.setEditable(true);
                tfNome.setEnabled(true);
                tfDatanascimento.setEditable(true);
                tfDatanascimento.setEnabled(true);
                cbMorto.setEnabled(true);
                tfCorDosOlhos.setEditable(true);
                tfCorDosOlhos.setEnabled(true);
                btAdicionarFoto.setVisible(true);
                btBuscarImgOnline.setVisible(true);
                btRemoverFoto.setVisible(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfId.setEnabled(true);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

// listener Excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfId.setEnabled(true);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfNome.setText("");
                tfNome.setEditable(false);
                tfDatanascimento.setText("");
                tfDatanascimento.setEditable(false);
                cbMorto.setSelected(false);
                cbMorto.setEnabled(true);
                tfCorDosOlhos.setText("");
                tfCorDosOlhos.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(pessoa);
                }
            }
        });//excluir a foto
        String cc = lbCaminhoFt.getText();
        //  System.out.println("arq > " +cc );
        File oArquivo = new File(cc.trim());
        if (oArquivo.exists()) {
            // System.out.println(oArquivo.getAbsolutePath());
            oArquivo.delete();//exclui a foto
            origem = dirApp + "/src/fotos/silhueta.png";
            ImageIcon ii = imagemAjustada.getImagemAjustada(dirApp + "/src/fotos/silhueta.png", tamX, tamY);
            lbFt.setIcon(ii);
        }

// listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Pessoa> listaPessoa = controle.listar();
                String[] colunas = new String[]{"id", "nome", "datanascimento", "morto", "ft", "CorDosOlhos"};
                String[][] dados = new String[listaPessoa.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaPessoa.size(); i++) {
                    aux = listaPessoa.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                scrollTabela.setPreferredSize(new Dimension(1000, 180));
                pack();
                btFechar.setVisible(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);

            }
        });
        localizarCorDosOlhos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CorDosOlhosControle controle = new CorDosOlhosControle();
                controle.carregarDados("src/CorDosOlhos.csv");

                List<String> listaAuxiliar = controle.listStrings();
                if (listaAuxiliar.size() > 0) {
                    Point lc = localizarCorDosOlhos.getLocationOnScreen();
                    lc.x = lc.x + localizarCorDosOlhos.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        tfCorDosOlhos.setText(aux[1]);
                    } else {
                        tfCorDosOlhos.requestFocus();
                        tfCorDosOlhos.selectAll();
                    }
                }
                btAdicionar.setVisible(false);
            }
        });

// listener Cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfId.setText("");
                tfId.requestFocus();
                tfId.setEnabled(true);
                tfId.setEditable(true);
                tfNome.setText("");
                tfNome.setEditable(false);
                tfDatanascimento.setText("");
                tfDatanascimento.setEditable(false);
                cbMorto.setEnabled(false);
                cbMorto.setSelected(false);
                tfCorDosOlhos.setText("");
                tfCorDosOlhos.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });//BOTAO ADICIONAR FOTO
        btAdicionarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                    File img = fc.getSelectedFile();
                    origem = fc.getSelectedFile().getAbsolutePath();

                    try {
                        lbFt.setIcon(imagemAjustada.getImagemAjustada(origem, tamX, tamY));
                        lbCaminhoFt.setText(origem);
                        temFoto = "Sim";
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(cp, "Erro ao carregar a imagem");
                    }
                }
            }
        });

        //BOTAO REMOVER FOTO
        btRemoverFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirma a remoção da foto?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    //excluir a foto
                    System.out.println("arq > " + lbCaminhoFt.getText());
                    File oArquivo = new File(lbCaminhoFt.getText().trim());
                    if (oArquivo.exists()) {
                        new File(lbCaminhoFt.getText()).delete();//exclui a foto
                        origem = dirApp + "/src/fotos/silhueta.png";
                        ImageIcon ii = imagemAjustada.getImagemAjustada(dirApp + "/src/fotos/silhueta.png", tamX, tamY);
                        lbFt.setIcon(ii);
                        temFoto = "Não";
                    }
                }
            }
        });
//BOTAO BUSCAR FOTO
        btBuscarImgOnline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String prefixBusca = "http://www.google.com.br/search?q=";
                String pesquisa = "Pessoa";
                String uri = prefixBusca + pesquisa;
                try {
                    Desktop.getDesktop().browse(new URI(uri));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro Ao Buscar, Tente Outra Pesquisa");

                }
            }
        });
        btFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnListagem.remove(scrollTabela);
                pack();
                btFechar.setVisible(false);
            }
        });

// listener ao fechar o programa
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em armazenamento permanente
                controle.gravarLista(caminho);
                // Sai da classe
                dispose();
            }
        });

        setModal(true);
        pack();
        setLocation( new CentroDoMonitorMaior().getCentroMonitorMaior(this));
        setVisible(true);
    }//fim do contrutor de GUI
} //fim da classe
