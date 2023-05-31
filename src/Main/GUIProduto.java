package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import MyUtil.ManipulaArquivo;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * @author radames
 */
class GUIProduto extends JFrame {

    //variáreis globais
    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbId = new JLabel("Id");
    JTextField tfId = new JTextField(10);
    JButton btBuscar = new JButton("Buscar");
    ControleProduto controle = new ControleProduto();
    Produto produto = new Produto();
    JLabel lbAviso = new JLabel("xxxx");

    JLabel lbNome = new JLabel("Nome");
    JTextField tfNome = new JTextField(40);
    JLabel lbPreco = new JLabel("Preço");
    JTextField tfPreco = new JTextField(40);
    JLabel lbUnidadeDeMedida = new JLabel("Unidade de Medida");
    JTextField tfUnidadeDeMedida = new JTextField(40);
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");

    String acao;
    String caminho = "Produto.csv";

    public GUIProduto() {
        //busca os dados no arquivo CSV e preenche a lista de produto
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        controle.preencherListaProduto(manipulaArquivo.abrirArquivo(caminho));

        //componentes visuais
        setTitle("Produto - Crud base");
        cp = getContentPane();

        cp.setLayout(new BorderLayout());

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.cyan);
        pnCentro.setBackground(Color.white);
        pnSul.setBackground(Color.yellow);

        pnNorte.add(lbId);
        pnNorte.add(tfId);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbPreco);
        pnCentro.add(tfPreco);
        pnCentro.add(lbUnidadeDeMedida);
        pnCentro.add(tfUnidadeDeMedida);

        pnSul.add(lbAviso);

        //status inicial
        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btListar.setVisible(true);
        tfId.setEditable(true);
        tfNome.setEditable(false);
        tfPreco.setEditable(false);
        tfUnidadeDeMedida.setEditable(false);

        //listeners
        tfId.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                lbAviso.setText("Digite o id de um produto");
            }

            @Override
            public void focusLost(FocusEvent fe) {

            }
        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tfId.getText().isEmpty()) {
                    tfId.requestFocus();
                } else {
                    produto = controle.buscar(Integer.valueOf(tfId.getText()));
                    if (produto == null) {//não achou
                        lbAviso.setText("Não achou na lista");
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                    } else {//encontra na lista
                        tfId.setText(String.valueOf(produto.getIdProduto()));
                        tfNome.setText(produto.getNomeProduto());
                        tfPreco.setText(String.valueOf(produto.getPrecoProduto()));
                        tfUnidadeDeMedida.setText(produto.getUnidadeDeMedida());
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        lbAviso.setText("Encontrou o registro");
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                tfId.setEditable(false);
                tfNome.setEditable(true);
                tfPreco.setEditable(true);
                tfUnidadeDeMedida.setEditable(true);

                tfNome.requestFocus();
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btExcluir.setVisible(false);
                acao = "adicionando";
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfId.setEditable(false);
                tfNome.setEditable(true);
                tfPreco.setEditable(true);
                tfUnidadeDeMedida.setEditable(true);
                btAlterar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btExcluir.setVisible(false);
                btBuscar.setVisible(false);
                acao = "alterando";
                lbAviso.setText("Alterando o registro");
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Produto original = produto;
                if (acao.equals("adicionando")) {
                    produto = new Produto();
                }

                produto.setIdProduto(Integer.valueOf(tfId.getText()));
                produto.setNomeProduto(tfNome.getText());
                produto.setPrecoProduto(Double.valueOf(tfPreco.getText()));
                produto.setUnidadeDeMedida(tfUnidadeDeMedida.getText());
                if (acao == "adicionando") {
                    controle.inserir(produto);
                    lbAviso.setText("Inseriu o registro");
                } else {
                    controle.atualizar(original, produto);
                    lbAviso.setText("Alterou o registro");
                }

                tfId.setText("");
                tfNome.setText("");
                tfPreco.setText("");
                tfUnidadeDeMedida.setText("");
                tfId.requestFocus();
                tfId.setEditable(true);
                tfNome.setEditable(false);
                tfPreco.setEditable(false);
                tfUnidadeDeMedida.setEditable(false);

                btBuscar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfId.setText("");
                tfNome.setText("");
                tfPreco.setText("");
                tfUnidadeDeMedida.setText("");
                tfId.requestFocus();
                tfId.setEditable(true);
                tfNome.setEditable(false);
                tfPreco.setEditable(false);
                tfUnidadeDeMedida.setEditable(false);

                btBuscar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                lbAviso.setText("");
            }
        });

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.
                        showConfirmDialog(cp, "Confirma a exclusão?", "Excluindo", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                if (opcao == JOptionPane.YES_NO_OPTION) {
                    controle.excluir(produto);
                }
                tfId.setText("");
                tfNome.setText("");
                tfPreco.setText("");
                tfUnidadeDeMedida.setText("");
                tfId.requestFocus();
                tfId.setEditable(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                lbAviso.setText("");
            }
        });
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lbAviso.setText("Relatório");
                Point coordenadas = getLocation();//pega as coordenadas da guiPai
                Dimension dimensao = getSize();
                GUIListarProduto guiListarProduto
                        = new GUIListarProduto(controle, coordenadas, dimensao);
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //antes de sair do sistema, grava os dados da lista de forma permanente (persiste os dados)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // gravar a lista em dispositivo de armazenamento permanente
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                manipulaArquivo.salvarArquivo(caminho, controle.listaDeProdutosString());
                System.exit(0);

            }
        });

        //setSize(800, 300);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
