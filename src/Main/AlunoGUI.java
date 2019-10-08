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

public class AlunoGUI extends JFrame {

    private Container cp;

    private JLabel lbRA = new JLabel("RA");
    private JTextField tfRA = new JTextField();
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField();
    private JLabel lbDataIngresso = new JLabel("DataIngresso");
    private DateTextField dtDataIngresso = new DateTextField();
    private JLabel lbCoeficiente = new JLabel("Coeficiente");
    private JTextField tfCoeficiente = new JTextField();
    private JLabel lbPeriodoNoCurso = new JLabel("PeriodoNoCurso");
    private JTextField tfPeriodoNoCurso = new JTextField();
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

    private AlunoControle controle = new AlunoControle();
    private Aluno aluno = new Aluno();

    String[] colunas = {"RA", "Nome", "dataIngresso", "coeficiente", "periodoNoCurso"};
    String[][] dados = new String[0][5];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JPanel painel1 = new JPanel(new GridLayout(1, 1));
    private JPanel painel2 = new JPanel(new GridLayout(1, 1));
    private CardLayout cardLayout;

    Tools tools = new Tools();

    public AlunoGUI() {

        String caminhoENomeDoArquivo = "DadosAluno.csv";

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(600, 400);
        setTitle("CRUD Aluno");
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
        painelCentro.add(lbDataIngresso);
        painelCentro.add(dtDataIngresso);
        painelCentro.add(lbCoeficiente);
        painelCentro.add(tfCoeficiente);
        painelCentro.add(lbPeriodoNoCurso);
        painelCentro.add(tfPeriodoNoCurso);
        toolBar.add(lbRA);
        toolBar.add(tfRA);
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
        dtDataIngresso.setEnabled(false);
        dtDataIngresso.setEditable(false);

        tfCoeficiente.setEditable(false);
        tfPeriodoNoCurso.setEditable(false);

        btCarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                if (manipulaArquivo.existeOArquivo(caminhoENomeDoArquivo)) {
                    String aux[];
                    Aluno x;
                    List<String> listaStringCsv = manipulaArquivo.abrirArquivo(caminhoENomeDoArquivo);
                    for (String linha : listaStringCsv) {
                        aux = linha.split(";");
                        // public Aluno(String RA,String Nome,Date dataIngresso,float coeficiente,byte periodoNoCurso) {
                        x = new Aluno(aux[0], aux[1], tools.converteDeStringParaDate(aux[2]), Float.valueOf(aux[3]), Byte.valueOf(aux[4]));
                        controle.adicionar(x);
                    }
                    cardLayout.show(painelSul, "Listagem");
                }
            }
        });
        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Aluno> listaAluno = controle.listar();
                List<String> listaAlunoEmFormatoStringCSV = new ArrayList<>();
                for (Aluno x : listaAluno) {
                    listaAlunoEmFormatoStringCSV.add(x.toString());
                }
                new ManipulaArquivo().salvarArquivo(caminhoENomeDoArquivo, listaAlunoEmFormatoStringCSV);

            }
        });
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);

                cardLayout.show(painelSul, "Avisos");
                scrollTexto.setViewportView(texto);
                if (tfRA.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(cp, " RA nâo pode ser vazio");
                    tfRA.requestFocus();
                    tfRA.selectAll();
                } else {
                    chavePrimaria = tfRA.getText();
                    aluno = controle.buscar(String.valueOf(tfRA.getText()));
                    if (aluno == null) {//nao encontrou
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        dtDataIngresso.setText("");
                        tfCoeficiente.setText("");
                        tfPeriodoNoCurso.setText("");
                        texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");

                    } else {
                        tfNome.setText(aluno.getNome());
                        dtDataIngresso.setText(tools.converteDeDateParaString(aluno.getDataIngresso()));
                        tfCoeficiente.setText(String.valueOf(aluno.getCoeficiente()));
                        tfPeriodoNoCurso.setText(String.valueOf(aluno.getPeriodoNoCurso()));
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        texto.setText("Encontrou na Lista - pode Alterar ou Excluir\n\n\n ");
                        tfNome.setEditable(false);
                        dtDataIngresso.setEnabled(false);
                        dtDataIngresso.setEditable(false);
                        tfCoeficiente.setEditable(false);
                        tfPeriodoNoCurso.setEditable(false);
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfRA.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfRA.setEditable(false);
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
                dtDataIngresso.setEnabled(true);
                dtDataIngresso.setEditable(true);
                tfCoeficiente.setEditable(true);
                tfPeriodoNoCurso.setEditable(true);
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfRA.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfRA.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(true);
                dtDataIngresso.setEnabled(true);
                dtDataIngresso.setEditable(true);
                tfCoeficiente.setEditable(true);
                tfPeriodoNoCurso.setEditable(true);
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfRA.setEditable(true);
                tfNome.setText("");
                dtDataIngresso.setDate(aluno.getDataIngresso());
                tfCoeficiente.setText("");
                tfPeriodoNoCurso.setText("");
                tfRA.requestFocus();
                tfRA.selectAll();
                texto.setText("Cancelou\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(false);
                dtDataIngresso.setEnabled(false);
                dtDataIngresso.setEditable(false);
                tfCoeficiente.setEditable(false);
                tfPeriodoNoCurso.setEditable(false);
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("alterar")) {
                    Aluno alunoAntigo = aluno;
                    aluno.setNome(tfNome.getText());
                    aluno.setDataIngresso(dtDataIngresso.getDate());
                    aluno.setCoeficiente(Float.valueOf(tfCoeficiente.getText()));
                    aluno.setPeriodoNoCurso(Byte.valueOf(tfPeriodoNoCurso.getText()));
                    controle.alterar(aluno, alunoAntigo);
                    texto.setText("Registro alterado\n\n\n\n\n");
                } else {//adicionar
                    aluno = new Aluno();
                    aluno.setRA(tfRA.getText());
                    aluno.setNome(tfNome.getText());
                    aluno.setDataIngresso(dtDataIngresso.getDate());
                    aluno.setCoeficiente(Float.valueOf(tfCoeficiente.getText()));
                    aluno.setPeriodoNoCurso(Byte.valueOf(tfPeriodoNoCurso.getText()));
                    controle.adicionar(aluno);
                    texto.setText("Foi adicionado um novo registro\n\n\n\n\n");//limpa o campo texto
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfRA.setEditable(true);
                tfNome.setText("");

                tfCoeficiente.setText("");
                tfPeriodoNoCurso.setText("");
                tfRA.requestFocus();
                tfRA.selectAll();
                tfNome.setEditable(false);
             
                dtDataIngresso.setEnabled(false);
                dtDataIngresso.setEditable(false);
                tfCoeficiente.setEditable(false);
                tfPeriodoNoCurso.setEditable(false);
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfRA.setText(chavePrimaria);
                if (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <Nome = " + aluno.getNome() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.excluir(aluno);
                }
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfRA.setEditable(true);
                tfNome.setText("");

                tfCoeficiente.setText("");
                tfPeriodoNoCurso.setText("");
                dtDataIngresso.setDate(dtDataIngresso.getDate());
                dtDataIngresso.setEnabled(true);
                dtDataIngresso.setEditable(true);
                tfRA.requestFocus();
                tfRA.selectAll();
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                texto.setText("Excluiu o registro de " + aluno.getRA() + " - " + aluno.getNome() + "\n\n\n\n\n");//limpa o campo texto
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Aluno> lt = controle.listar();

                String[] colunas = {"RA", "Nome", "dataIngresso", "coeficiente", "periodoNoCurso"};
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
