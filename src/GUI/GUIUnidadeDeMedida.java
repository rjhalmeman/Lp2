package GUI;

import Controle.ControleUnidadeDeMedida;
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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author radames
 */
public class GUIUnidadeDeMedida extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbPK = new JLabel("Sigla");
    JTextField tfSiglaPk = new JTextField(20);

    JLabel lbNome = new JLabel("Nome");
    JTextField tfNomeUnidadeDeMedida = new JTextField(50);
  
    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");

    ControleUnidadeDeMedida controle = new ControleUnidadeDeMedida();
    UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
    String acao = "";

    /////////////////////////////////////////////////
    String[] colunas = new String[]{"Sigla", "Nome"};
    String[][] dados = new String[0][2];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

    public GUIUnidadeDeMedida() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - UnidadeDeMedida");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.cyan);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbPK);
        pnNorte.add(tfSiglaPk);
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

        pnCentro.setLayout(new GridLayout(1, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNomeUnidadeDeMedida);
      

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

        String caminho = "UnidadeDeMedida.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                unidadeDeMedida = controle.buscar(tfSiglaPk.getText());
                if (unidadeDeMedida != null) {//achou o unidadeDeMedida na lista
                    //mostrar
                    btAdicionar.setVisible(false);
                    btAlterar.setVisible(true);
                    btExcluir.setVisible(true);
                    tfNomeUnidadeDeMedida.setText(unidadeDeMedida.getNome_UM());                  
                    tfNomeUnidadeDeMedida.setEditable(false);                  

                } else {//não achou na lista
                    //mostrar botão incluir
                    tfNomeUnidadeDeMedida.setText("");                 
                    btAdicionar.setVisible(true);
                    tfNomeUnidadeDeMedida.setEditable(false);                 
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfSiglaPk.setEnabled(false);
                tfNomeUnidadeDeMedida.setEditable(true);
                tfNomeUnidadeDeMedida.requestFocus();
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
                    unidadeDeMedida = new UnidadeDeMedida();
                }
                UnidadeDeMedida unidadeDeMedidaAntigo = unidadeDeMedida;

                unidadeDeMedida.setId_UM(tfSiglaPk.getText());
                unidadeDeMedida.setNome_UM(tfNomeUnidadeDeMedida.getText());
               
                if (acao.equals("adicionar")) {
                    controle.adicionar(unidadeDeMedida);
                } else {
                    controle.alterar(unidadeDeMedida, unidadeDeMedidaAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfSiglaPk.setEnabled(true);
                tfSiglaPk.setEditable(true);
                tfSiglaPk.requestFocus();
                tfSiglaPk.setText("");

                tfNomeUnidadeDeMedida.setText("");              
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfNomeUnidadeDeMedida.setEditable(false);
           

            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfSiglaPk.setEditable(false);
                tfNomeUnidadeDeMedida.setEditable(true);
            
                tfNomeUnidadeDeMedida.requestFocus();
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
                tfSiglaPk.setEnabled(true);
                tfSiglaPk.setEditable(true);
                tfSiglaPk.requestFocus();
                tfSiglaPk.setText("");

                tfNomeUnidadeDeMedida.setText(""); 
                btBuscar.setVisible(true);
                tfNomeUnidadeDeMedida.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(unidadeDeMedida);
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UnidadeDeMedida> listaUnidadeDeMedida = controle.listar();
                String[] colunas = {"Sigla", "Nome"};
                Object[][] dados = new Object[listaUnidadeDeMedida.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaUnidadeDeMedida.size(); i++) {
                    aux = listaUnidadeDeMedida.get(i).toString().split(";");
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
                tfSiglaPk.setText("");
                tfSiglaPk.requestFocus();
                tfSiglaPk.setEnabled(true);
                tfSiglaPk.setEditable(true);
                tfNomeUnidadeDeMedida.setText("");                
                tfNomeUnidadeDeMedida.setEditable(false);               
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
        setSize(700, 200);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    }

}
