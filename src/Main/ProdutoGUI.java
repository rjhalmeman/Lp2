package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import tools.ManipulaArquivo;
import tools.DateTextField;
import tools.Tools;

public class ProdutoGUI extends JFrame {

    private Container cp;

    private JLabel lbId = new JLabel("Id");
    private JTextField tfId = new JTextField();
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField();
    private JLabel lbDataFabricacao = new JLabel("DataFabricacao");
    private DateTextField tfDataFabricacao = new DateTextField();
    private JLabel lbDataValidade = new JLabel("DataValidade");
    private DateTextField tfDataValidade = new DateTextField();
    private JLabel lbAtivo = new JLabel("Ativo");
    private JCheckBox cbAtivo = new JCheckBox("Ativo");
    private JButton btAdicionar = new JButton("Adicionar");
    private JButton btListar = new JButton("Listar");
    private JButton btBuscar = new JButton("Buscar");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btCarregarDados = new JButton("Carregar");
    private JButton btGravar = new JButton("Gravar");
    private JToolBar toolBar = new JToolBar();
    private JPanel painelNorte = new JPanel();
    private JPanel painelCentro = new JPanel();
    private JPanel painelSul = new JPanel();
    private JTextArea texto = new JTextArea();
    private JScrollPane scrollTexto = new JScrollPane();
    private JScrollPane scrollTabela = new JScrollPane();

    private String acao = "";
    private String chavePrimaria = "";

    private ProdutoControle controle = new ProdutoControle();
    private Produto produto = new Produto();

    String[] colunas = {"id", "nome", "dataFabricacao", "dataValidade", "ativo"};
    String[][] dados = new String[0][5];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JPanel painel1 = new JPanel(new GridLayout(1, 1));
    private JPanel painel2 = new JPanel(new GridLayout(1, 1));
    private CardLayout cardLayout;
    private Tools tools = new Tools();

    public ProdutoGUI() {

        String caminhoENomeDoArquivo = "DadosProduto.csv";

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(600, 400);
        setTitle("CRUD Produto");
        setLocationRelativeTo(null);//centro do monitor

        cp = getContentPane();

        cp.setLayout(new BorderLayout());
        cp.add(painelNorte, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);
        cardLayout = new CardLayout();
        painelSul.setLayout(cardLayout);

        painel1.add(scrollTexto);
        painel2.add(scrollTabela);

        texto.setText("\n\n\n\n\n\n");//5 linhas de tamanho
        scrollTexto.setViewportView(texto);

        painelSul.add(painel1, "Avisos");
        painelSul.add(painel2, "Listagem");

        painelNorte.setLayout(new GridLayout(1, 1));
        painelNorte.add(toolBar);

        painelCentro.setLayout(new GridLayout(4, 2));

        painelCentro.add(lbNome);
        painelCentro.add(tfNome);
        painelCentro.add(lbDataFabricacao);
        painelCentro.add(tfDataFabricacao);
        painelCentro.add(lbDataValidade);
        painelCentro.add(tfDataValidade);
        painelCentro.add(lbAtivo);
        painelCentro.add(cbAtivo);
        toolBar.add(lbId);
        toolBar.add(tfId);
        toolBar.add(btAdicionar);
        toolBar.add(btBuscar);
        toolBar.add(btListar);
        toolBar.add(btAlterar);
        toolBar.add(btExcluir);
        toolBar.add(btSalvar);
        toolBar.add(btCancelar);

        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        tfNome.setEditable(false);
        tfDataFabricacao.setEnabled(false);
        tfDataFabricacao.setEditable(false);
        tfDataValidade.setEnabled(false);
        tfDataValidade.setEditable(false);
        cbAtivo.setEnabled(false);
        btCarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                if (manipulaArquivo.existeOArquivo(caminhoENomeDoArquivo)) {
                    String aux[];
                    Produto x;
                    List<String> listaStringCsv = manipulaArquivo.abrirArquivo(caminhoENomeDoArquivo);
                    for (String linha : listaStringCsv) {
                        aux = linha.split(";");
                        x = new Produto(Long.valueOf(aux[0]), String.valueOf(aux[1]), tools.converteDeStringParaDate(aux[2]), tools.converteDeStringParaDate(aux[3]), Boolean.valueOf(aux[4].equals("Sim") ? true : false));
                        controle.adicionar(x);
                    }
                    cardLayout.show(painelSul, "Listagem");
                }
            }
        });
        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Produto> listaProduto = controle.listar();
                List<String> listaProdutoEmFormatoStringCSV = new ArrayList<>();
                for (Produto x : listaProduto) {
                    listaProdutoEmFormatoStringCSV.add(x.toString());
                }
                new ManipulaArquivo().salvarArquivo(caminhoENomeDoArquivo, listaProdutoEmFormatoStringCSV);
                System.out.println("gravou");
            }
        });
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);

                cardLayout.show(painelSul, "Avisos");
                scrollTexto.setViewportView(texto);
                if (tfId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(cp, " ID nâo pode ser vazio");
                    tfId.requestFocus();
                    tfId.selectAll();
                } else {
                    chavePrimaria = tfId.getText();
                    produto = controle.buscar(Long.valueOf(tfId.getText()));
                    if (produto == null) {//nao encontrou
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfDataFabricacao.setText("");
                        tfDataValidade.setText("");
                        cbAtivo.setSelected(false);
                        texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");

                    } else {
                        tfNome.setText(produto.getNome());
                        tfDataFabricacao.setText(tools.converteDeDateParaString(produto.getDataFabricacao()));
                        tfDataValidade.setText(tools.converteDeDateParaString(produto.getDataValidade()));
                        cbAtivo.setSelected(produto.getAtivo());
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        texto.setText("Encontrou na Lista - pode Alterar ou Excluir\n\n\n ");
                        tfNome.setEditable(false);
                        tfDataFabricacao.setEnabled(false);
                        tfDataFabricacao.setEditable(false);
                        tfDataValidade.setEnabled(false);
                        tfDataValidade.setEditable(false);
                        cbAtivo.setEnabled(false);
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfId.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfId.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);

                btAdicionar.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(true);
                tfDataFabricacao.setEnabled(true);
                tfDataFabricacao.setEditable(true);
                tfDataValidade.setEditable(true);
                tfDataValidade.setEnabled(true);
                cbAtivo.setEnabled(true);
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfId.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfId.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(true);
                tfDataFabricacao.setEnabled(true);
                tfDataFabricacao.setEditable(true);
                tfDataValidade.setEditable(true);
                tfDataValidade.setEnabled(true);
                cbAtivo.setEnabled(true);
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfId.setEditable(true);
                tfNome.setText("");
                tfDataFabricacao.setDate(produto.getDataFabricacao());
                tfDataValidade.setText("");
                cbAtivo.setSelected(false);
                tfId.requestFocus();
                tfId.selectAll();
                texto.setText("Cancelou\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(false);
                tfDataFabricacao.setEnabled(false);
                tfDataFabricacao.setEditable(false);
                tfDataValidade.setEditable(false);
                tfDataValidade.setEnabled(false);
                cbAtivo.setEnabled(false);
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("alterar")) {
                    Produto produtoAntigo = produto;
                    produto.setNome(tfNome.getText());
                    produto.setDataFabricacao(tfDataFabricacao.getDate());
                    produto.setDataValidade(tools.converteDeStringParaDate(tfDataValidade.getText()));
                    produto.setAtivo(cbAtivo.isSelected());
                    controle.alterar(produto, produtoAntigo);
                    texto.setText("Registro alterado\n\n\n\n\n");
                } else {//adicionar
                    produto = new Produto();
                    produto.setId(Long.valueOf(tfId.getText()));
                    produto.setNome(tfNome.getText());
                    produto.setDataFabricacao(tfDataFabricacao.getDate());
                    produto.setDataValidade(tools.converteDeStringParaDate(tfDataValidade.getText()));
                    produto.setAtivo(cbAtivo.isSelected());
                    controle.adicionar(produto);
                    texto.setText("Foi adicionado um novo registro\n\n\n\n\n");//limpa o campo texto
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfId.setEditable(true);
                tfNome.setText("");

                tfDataValidade.setText("");

                tfId.requestFocus();
                tfId.selectAll();
                tfNome.setEditable(false);

                tfDataFabricacao.setEnabled(false);
                tfDataFabricacao.setEditable(false);
                tfDataValidade.setEditable(false);
                tfDataValidade.setEnabled(false);
                cbAtivo.setSelected(false);
                cbAtivo.setEnabled(true);
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfId.setText(chavePrimaria);
                if (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <Nome = " + produto.getNome() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.excluir(produto);
                }
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfId.setEditable(true);
                tfNome.setText("");

                tfDataValidade.setText("");

                tfDataFabricacao.setDate(tfDataFabricacao.getDate());
                tfDataFabricacao.setEnabled(true);
                cbAtivo.setSelected(false);
                cbAtivo.setEnabled(true);
                tfId.requestFocus();
                tfId.selectAll();
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                texto.setText("Excluiu o registro de " + produto.getId() + " - " + produto.getNome() + "\n\n\n\n\n");//limpa o campo texto
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Produto> lt = controle.listar();

                String[] colunas = {"id", "nome", "dataFabricacao", "dataValidade", "ativo"};
                Object[][] dados = new Object[lt.size()][colunas.length];
                String aux[];
                for (int i = 0; i < lt.size(); i++) {
                    aux = lt.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(painelSul, "Listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                painel2.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                btGravar.doClick();
                // Sai da classe
                dispose();
            }
        });

        setVisible(true);

        //depois que a tela ficou visível, clic o botão automaticamente.
        btCarregarDados.doClick();//execute o listener do btCarregarDados
    }

}
