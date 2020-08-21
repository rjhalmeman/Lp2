package GUI;

import Controle.ControleProduto;
import Entidade.Produto;
import Entidade.UnidadeDeMedida;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class GUIProduto extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbIdProduto = new JLabel("Id");
    JTextField tfIdProduto = new JTextField(20);

    JLabel lbNomeProduto = new JLabel("Nome");
    JTextField tfNomeProduto = new JTextField(50);
    JLabel lbQuantidadeProduto = new JLabel("Quantidade");
    JTextField tfQuantidadeProduto = new JTextField(10);
    JLabel lbUnidadeDeMedida = new JLabel("Unidade de Medida");
    DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    JComboBox comboBoxUM = new JComboBox(comboBoxModel);

    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");

    ControleProduto controle = new ControleProduto();
    Produto produto = new Produto();
    String acao = "";

    /////////////////////////////////////////////////
    String[] colunas = new String[]{"Id", "Nome", "Quantidade", "Unid Medida"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

    public GUIProduto() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Produto");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.cyan);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdProduto);
        pnNorte.add(tfIdProduto);
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
        pnCentro.add(lbNomeProduto);
        pnCentro.add(tfNomeProduto);
        pnCentro.add(lbQuantidadeProduto);
        pnCentro.add(tfQuantidadeProduto);
        pnCentro.add(lbUnidadeDeMedida);
        pnCentro.add(comboBoxUM);

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

        //inicia com atributos bloqueados
        tfNomeProduto.setEditable(false);
        tfQuantidadeProduto.setEditable(false);
        comboBoxUM.setEnabled(false);

        String caminho = "Produto.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

        //carregar dados da entidade relacionada (UnidadeDeMedida)
        //esses dados serão usados na FK
        List<String> umCSV = new ManipulaArquivo().abrirArquivo("UnidadeDeMedida.csv");
        for (String string : umCSV) {
            String[] aux = string.split(";");
            comboBoxModel.addElement(string.replace(";", "-"));
        }

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                int valor = 0;
                try {
                    valor = Integer.valueOf(tfIdProduto.getText());

                    produto = controle.buscar(valor);
                    if (produto != null) {//achou o produto na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNomeProduto.setText(produto.getNomeProduto());
                        tfQuantidadeProduto.setText(String.valueOf(produto.getQuantidadeProduto()));

                        int i = 0;
                        for (i = 0; i < umCSV.size(); i++) {
                            if (umCSV.get(i).split(";")[0].equals(produto.getId_UM())) {
                                break;
                            }
                        }
                        if (i < umCSV.size()) {
                            comboBoxUM.setSelectedIndex(i);
                        }

                        tfNomeProduto.setEditable(false);
                        tfQuantidadeProduto.setEditable(false);
                        comboBoxUM.setEnabled(false);

                    } else {//não achou na lista
                        //mostrar botão incluir
                        tfNomeProduto.setText("");
                        tfQuantidadeProduto.setText("");

                        tfNomeProduto.setEditable(false);
                        tfQuantidadeProduto.setEditable(false);
                        comboBoxUM.setEnabled(false);
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                    }
                } catch (Exception ex) {
                     JOptionPane.showMessageDialog(cp,"Erro no tipo de dados","Erro ao buscar",JOptionPane.PLAIN_MESSAGE);
                     tfIdProduto.selectAll();
                     tfIdProduto.requestFocus();
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNomeProduto.requestFocus();
                tfIdProduto.setEnabled(false);
                tfNomeProduto.setEditable(true);
                tfQuantidadeProduto.setEditable(true);
                comboBoxUM.setEnabled(true);
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
                    produto = new Produto();
                }
                Produto produtoAntigo = produto;

                produto.setIdProduto(Integer.valueOf(tfIdProduto.getText()));
                produto.setNomeProduto(tfNomeProduto.getText());
                produto.setQuantidadeProduto(Integer.valueOf(tfQuantidadeProduto.getText()));

                produto.setId_UM(String.valueOf(comboBoxUM.getSelectedItem()).split("-")[0]);

                if (acao.equals("adicionar")) {
                    controle.adicionar(produto);
                } else {
                    controle.alterar(produto, produtoAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfIdProduto.setEnabled(true);
                tfIdProduto.setEditable(true);
                tfIdProduto.requestFocus();

                tfIdProduto.setText("");
                tfNomeProduto.setText("");
                tfQuantidadeProduto.setText("");

                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfNomeProduto.setEditable(false);
                tfQuantidadeProduto.setEditable(false);
                comboBoxUM.setEnabled(false);

            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfNomeProduto.requestFocus();
                tfIdProduto.setEditable(false);
                tfNomeProduto.setEditable(true);
                tfQuantidadeProduto.setEditable(true);
                comboBoxUM.setEnabled(true);

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
                tfIdProduto.setEnabled(true);
                tfIdProduto.setEditable(true);
                tfIdProduto.requestFocus();
                tfIdProduto.setText("");
                tfNomeProduto.setText("");
                tfQuantidadeProduto.setText("");

                btBuscar.setVisible(true);

                tfNomeProduto.setEditable(false);
                tfQuantidadeProduto.setEditable(false);
                comboBoxUM.setEnabled(false);

                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(produto);
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Produto> listaProduto = controle.listar();
                String[] colunas = {"Id", "Nome", "Quantidade", "Unid Medida"};
                Object[][] dados = new Object[listaProduto.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaProduto.size(); i++) {
                    aux = listaProduto.get(i).toString().split(";");
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
                tfIdProduto.setText("");
                tfIdProduto.requestFocus();
                tfIdProduto.setEnabled(true);
                tfIdProduto.setEditable(true);
                tfNomeProduto.setText("");
                tfQuantidadeProduto.setText("");

                tfNomeProduto.setEditable(false);
                tfQuantidadeProduto.setEditable(false);

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

        setModal(true);
        setSize(700, 300);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    }

    public static void main(String[] args) {
        GUIProduto guiProduto = new GUIProduto();
    }

}
