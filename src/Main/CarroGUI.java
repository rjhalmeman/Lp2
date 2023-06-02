package Main;

import MyUtil.CaixaDeFerramentas;
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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

/**
 *
 * @author radames
 */
class CarroGUI extends JFrame {

    //variáreis globais
    //carregar imagens dos icones
    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbPlaca = new JLabel("Placa");
    JTextField tfPlaca = new JTextField(15);
    CarroControle controle = new CarroControle();
    Carro carro = new Carro();
    JLabel lbAviso = new JLabel("xxxx");

    JLabel lbNome = new JLabel("Nome");
    JTextField tfNome = new JTextField(40);
    JLabel lbPeso = new JLabel("Peso");
    JTextField tfPeso = new JTextField(40);
    JLabel lbDataLancamento = new JLabel("Data de lançamento");
    JTextField tfDataLancamento = new JTextField(40);
    JButton btBuscar = new JButton(iconeRetrieve);
    JButton btAdicionar = new JButton(iconeCreate);
    JButton btSalvar = new JButton(iconeSave);
    JButton btAlterar = new JButton(iconeUpdate);
    JButton btExcluir = new JButton(iconeDelete);
    JButton btListar = new JButton(iconeListar);
    JButton btCancelar = new JButton(iconeCancel);

    String acao;
    String caminho = "Carro.csv";
    CaixaDeFerramentas cf = new CaixaDeFerramentas();
    JToolBar jToolbar = new JToolBar();

    public CarroGUI() {
        //busca os dados no arquivo CSV e preenche a lista de produto
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (manipulaArquivo.existeOArquivo(caminho)) {
            controle.preencherListaCarro(manipulaArquivo.abrirArquivo(caminho));
        } else {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        //componentes visuais
        setTitle("CRUD Carro");
        cp = getContentPane();

        cp.setLayout(new BorderLayout());

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.setBackground(Color.white);
        pnSul.setBackground(Color.DARK_GRAY);

        pnNorte.setLayout(new FlowLayout((int) LEFT_ALIGNMENT));
        pnNorte.add(jToolbar);
        jToolbar.add(lbPlaca);
        jToolbar.add(tfPlaca);
        jToolbar.add(btBuscar);
        jToolbar.add(btAdicionar);
        jToolbar.add(btAlterar);
        jToolbar.add(btExcluir);
        jToolbar.add(btListar);
        jToolbar.add(btSalvar);
        jToolbar.add(btCancelar);

        btBuscar.setToolTipText("Buscar");
        btAdicionar.setToolTipText("Adicionar novo registro");
        btAlterar.setToolTipText("Alterar um registro");
        btExcluir.setToolTipText("Excluir um registro");
        btListar.setToolTipText("Listagem");
        btSalvar.setToolTipText("Salvar dados do registro");
        btCancelar.setToolTipText("Cancelar edição (sair sem salvar)");

        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbPeso);
        pnCentro.add(tfPeso);
        pnCentro.add(lbDataLancamento);
        pnCentro.add(tfDataLancamento);

        pnSul.add(lbAviso);

        //status inicial
        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btListar.setVisible(true);
        tfPlaca.setEditable(true);
        tfNome.setEditable(false);
        tfPeso.setEditable(false);
        tfDataLancamento.setEditable(false);

        lbAviso.setOpaque(true);
        lbAviso.setBackground(Color.BLACK);
        // Definir a cor da fonte como branca
        lbAviso.setForeground(Color.WHITE);

        // Definir a fonte em negrito
        Font fonte = lbAviso.getFont();
        Font fonteNegrito = new Font(fonte.getFontName(), Font.BOLD, fonte.getSize());
        lbAviso.setFont(fonteNegrito);

        //listeners
        tfPlaca.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                lbAviso.setText("Digite a placa de um carro");
                tfPlaca.setBackground(Color.green);
                btAdicionar.setVisible(false);
                btAlterar.setVisible(false);

                btExcluir.setVisible(false);
                if (!btSalvar.isVisible()) {
                    btListar.setVisible(true);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfPlaca.setBackground(Color.white);
            }
        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tfPlaca.getText().isEmpty()) {
                    tfPlaca.requestFocus();
                } else {
                    carro = controle.buscar(tfPlaca.getText());
                    if (carro == null) {//não achou na lista
                        lbAviso.setText("Não achou na lista");
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);

                        tfNome.setText("");
                        tfPeso.setText("");
                        tfDataLancamento.setText("");
                    } else {//encontra na lista
                        tfPlaca.setText(String.valueOf(carro.getPlacaCarro()));
                        tfNome.setText(carro.getNomeCarro());
                        tfPeso.setText(String.valueOf(carro.getPesoCarro()));
                        tfDataLancamento.setText(cf.converteDeDateParaString(carro.getDataLancamento()));
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btListar.setVisible(false);
                        lbAviso.setText("Encontrou o registro");
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                tfPlaca.setEditable(false);
                tfNome.setEditable(true);
                tfPeso.setEditable(true);
                tfDataLancamento.setEditable(true);

                tfNome.requestFocus();
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btExcluir.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionando";
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfNome.requestFocus();
                tfPlaca.setEditable(false);
                tfNome.setEditable(true);
                tfPeso.setEditable(true);
                tfDataLancamento.setEditable(true);
                btAlterar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btExcluir.setVisible(false);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "alterando";
                lbAviso.setText("Alterando o registro");
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuErro = false;
                Carro original = carro;
                if (acao.equals("adicionando")) {
                    carro = new Carro();
                }

                carro.setPlacaCarro(tfPlaca.getText());
                carro.setNomeCarro(tfNome.getText());
                try {
                    carro.setPesoCarro(Double.valueOf(tfPeso.getText()));

                } catch (Exception e) {
                    tfPeso.setBackground(Color.yellow);
                    deuErro = true;
                }
                Date dt = cf.converteDeStringParaDate(tfDataLancamento.getText());
                if (dt != null) {
                    carro.setDataLancamento(dt);

                } else {
                    tfDataLancamento.setBackground(Color.yellow);
                    deuErro = true;
                }

                if (!deuErro) {
                    if (acao == "adicionando") {
                        controle.inserir(carro);
                        lbAviso.setText("Inseriu o registro");
                    } else {
                        controle.atualizar(original, carro);
                        lbAviso.setText("Alterou o registro");
                    }

                    tfPeso.setBackground(Color.white);
                    tfDataLancamento.setBackground(Color.white);

                    tfPlaca.setText("");
                    tfNome.setText("");
                    tfPeso.setText("");
                    tfDataLancamento.setText("");
                    tfPlaca.requestFocus();
                    tfPlaca.setEditable(true);
                    tfNome.setEditable(false);
                    tfPeso.setEditable(false);
                    tfDataLancamento.setEditable(false);

                    btBuscar.setVisible(true);
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btListar.setVisible(true);
                }
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfPlaca.setText("");
                tfNome.setText("");
                tfPeso.setText("");
                tfDataLancamento.setText("");
                tfPlaca.requestFocus();
                tfPlaca.setEditable(true);
                tfNome.setEditable(false);
                tfPeso.setEditable(false);
                tfDataLancamento.setEditable(false);

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
                    controle.excluir(carro);
                }
                tfPlaca.setText("");
                tfNome.setText("");
                tfPeso.setText("");
                tfDataLancamento.setText("");
                tfPlaca.requestFocus();
                tfPlaca.setEditable(true);
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
                String idSelecionado
                        = new CarroGUIListar(controle, coordenadas, dimensao).getIdSelecionado();
                if (idSelecionado != null) { //se selecionou algo no listagem, abre.
                    tfPlaca.setText(idSelecionado);
                    btBuscar.doClick();
                }
                tfPlaca.requestFocus();
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //antes de sair do sistema, grava os dados da lista de forma permanente (persiste os dados)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // gravar a lista em dispositivo de armazenamento permanente
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                manipulaArquivo.salvarArquivo(caminho, controle.listaString());
                System.exit(0);

            }
        });

        //setSize(800, 300);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
