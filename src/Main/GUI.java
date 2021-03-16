package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author radames
 */
public class GUI extends JFrame {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbPK = new JLabel("CPF");
    JTextField tfPK = new JTextField(20);

    JLabel lbNome = new JLabel("Nome");
    JTextField tfNome = new JTextField(50);
    JLabel lbSalario = new JLabel("Salario");
    JTextField tfSalario = new JTextField(15);
    JCheckBox checkBoxAposentado = new JCheckBox("Aposentado", false);

    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");

    Controle controle = new Controle();
    Trabalhador trabalhador = new Trabalhador();
    String acao = "";

    /////////////////////////////////////////////////
    String[] colunas = new String[]{"CPF", "Nome", "Salario", "Aposentado"};
    String[][] dados = new String[0][4];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

    public GUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Trabalhador");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.cyan);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbPK);
        pnNorte.add(tfPK);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);

        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbSalario);
        pnCentro.add(tfSalario);
        pnCentro.add(checkBoxAposentado);

        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));

        String caminho = "Trabalhador.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfPK.getText().isEmpty()) {

                    cardLayout.show(pnSul, "avisos");
                    trabalhador = controle.buscar(tfPK.getText());
                    if (trabalhador != null) {//achou o trabalhador na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNome.setText(trabalhador.getNome());
                        tfSalario.setText(String.valueOf(trabalhador.getSalario()));
                        checkBoxAposentado.setSelected(trabalhador.isAposentado());

                        tfNome.setEditable(false);
                        tfSalario.setEditable(false);
                        checkBoxAposentado.setEnabled(false);

                    } else {//não achou na lista
                        //mostrar botão incluir
                        tfNome.setText("");
                        tfSalario.setText("");
                        checkBoxAposentado.setSelected(false);
                        btAdicionar.setVisible(true);
                        tfNome.setEditable(false);
                        tfSalario.setEditable(false);
                        checkBoxAposentado.setEnabled(false);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfPK.setEnabled(false);
                tfNome.setEditable(true);
                tfNome.requestFocus();
                tfSalario.setEditable(true);
                checkBoxAposentado.setEnabled(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("adicionar")) {
                    trabalhador = new Trabalhador();
                }
                Trabalhador trabalhadorAntigo = trabalhador;

                trabalhador.setCpf(tfPK.getText());
                trabalhador.setNome(tfNome.getText());
                trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                trabalhador.setAposentado(checkBoxAposentado.isSelected());
                if (acao.equals("adicionar")) {
                    controle.adicionar(trabalhador);
                } else {
                    controle.alterar(trabalhador, trabalhadorAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfPK.setEnabled(true);
                tfPK.setEditable(true);
                tfPK.requestFocus();
                tfPK.setText("");

                tfNome.setText("");
                tfSalario.setText("");
                checkBoxAposentado.setSelected(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);

                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                checkBoxAposentado.setEnabled(false);

            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfPK.setEditable(false);
                tfNome.setEditable(true);
                tfSalario.setEditable(true);
                checkBoxAposentado.setEnabled(true);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfPK.setEnabled(true);
                tfPK.setEditable(true);
                tfPK.requestFocus();
                tfPK.setText("");

                tfNome.setText("");
                tfSalario.setText("");
                checkBoxAposentado.setSelected(false);
                btBuscar.setVisible(true);

                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                checkBoxAposentado.setEnabled(false);

                btAlterar.setVisible(false);

                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(trabalhador);
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Trabalhador> listaTrabalhador = controle.listar();
                String[] colunas = {"CPF", "Nome", "Salário", "Aposentado"};
                Object[][] dados = new Object[listaTrabalhador.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaTrabalhador.size(); i++) {
                    aux = listaTrabalhador.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);

            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfPK.setText("");
                tfPK.requestFocus();
                tfPK.setEnabled(true);
                tfPK.setEditable(true);

                tfNome.setText("");
                tfSalario.setText("");
                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                checkBoxAposentado.setEnabled(false);

                checkBoxAposentado.setSelected(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                controle.gravarLista(caminho);
                // Sai da classe
                dispose();
            }
        });

        setSize(700, 300);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    }

}
