package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author radames
 */
public class GUI extends JFrame {

    private Container cp;

    private JLabel lbCpf = new JLabel("CPF");
    private JTextField tfCpf = new JTextField(20);
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField(50);
    private JLabel lbSalario = new JLabel("Salário");
    private JTextField tfSalario = new JTextField(20);
    private JCheckBox cbAposentado = new JCheckBox("Aposentado", false);
    private JButton btAdicionar = new JButton("Adicionar");
    private JButton btListar = new JButton("Listar");
    private JButton btBuscar = new JButton("Buscar");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JToolBar toolBar = new JToolBar();
    private JPanel painelNorte = new JPanel();
    private JPanel painelCentro = new JPanel();
    private JPanel painelSul = new JPanel();
    private JTextArea texto = new JTextArea();
    private JScrollPane scroll = new JScrollPane();

    private String acao = "";
    private String chavePrimaria = "";

    private Controle controle = new Controle();
    private Trabalhador trabalhador = new Trabalhador();

    public GUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(800, 500);
        setTitle("CRUD Trabalhador - V5");
        setLocationRelativeTo(null);//centro do monitor

        cp = getContentPane();

        cp.setLayout(new BorderLayout());
        cp.add(painelNorte, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelSul.setLayout(new GridLayout(1, 1));
        texto.setText("\n\n\n\n\n");//5 linhas de tamanho
        scroll.setViewportView(texto);
        painelSul.add(scroll);

        painelNorte.setLayout(new GridLayout(1, 1));
        painelNorte.add(toolBar);

        painelCentro.setLayout(new GridLayout(3, 2));

        painelCentro.add(lbNome);
        painelCentro.add(tfNome);
        painelCentro.add(lbSalario);
        painelCentro.add(tfSalario);
        painelCentro.add(cbAposentado);

        toolBar.add(lbCpf);
        toolBar.add(tfCpf);
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
        tfSalario.setEditable(false);//atributos começam bloqueados
        cbAposentado.setEnabled(false);
        texto.setEditable(false);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);
                if (tfCpf.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(cp, "CPF nâo pode ser vazio");
                    tfCpf.requestFocus();
                    tfCpf.selectAll();
                } else {
                    chavePrimaria = tfCpf.getText();//para uso no adicionar
                    trabalhador = controle.buscar(tfCpf.getText());
                    if (trabalhador == null) {//nao encontrou
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfSalario.setText("");
                        cbAposentado.setSelected(false);
                        texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");//limpa o campo texto

                    } else {//encontrou
                        tfNome.setText(trabalhador.getNome());
                        tfSalario.setText(String.valueOf(trabalhador.getSalario()));
                        cbAposentado.setSelected(trabalhador.isAposentado());
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        texto.setText("Encontrou na lista - pode Alterar ou Excluir\n\n\n");//limpa o campo texto
                        tfNome.setEditable(false);
                        tfSalario.setEditable(false);//atributos começam bloqueados
                        cbAposentado.setEnabled(false);
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfCpf.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfCpf.setEditable(false);
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
                tfSalario.setEditable(true);
                cbAposentado.setEnabled(true);
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfCpf.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfCpf.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(true);
                tfSalario.setEditable(true);
                cbAposentado.setEnabled(true);
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfCpf.setEditable(true);
                tfNome.setText("");
                tfSalario.setText("");
                cbAposentado.setSelected(false);
                tfCpf.requestFocus();
                tfCpf.selectAll();
                texto.setText("Cancelou\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                cbAposentado.setEnabled(false);
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("alterar")) {
                    Trabalhador trabalhadorAntigo = trabalhador;
                    trabalhador.setNome(tfNome.getText());
                    trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                    trabalhador.setAposentado(cbAposentado.isSelected());
                    controle.alterar(trabalhador, trabalhadorAntigo);
                    texto.setText("Registro alterado\n\n\n\n\n");//limpa o campo texto
                } else {//adicionar
                    trabalhador = new Trabalhador();
                    trabalhador.setCpf(tfCpf.getText());
                    trabalhador.setNome(tfNome.getText());
                    trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                    trabalhador.setAposentado(cbAposentado.isSelected());
                    controle.adicionar(trabalhador);
                    texto.setText("Foi adicionado um novo registro\n\n\n\n\n");//limpa o campo texto
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfCpf.setEditable(true);
                tfNome.setText("");
                tfSalario.setText("");
                tfCpf.requestFocus();
                tfCpf.selectAll();
                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                cbAposentado.setSelected(false);
                cbAposentado.setEnabled(true);
            }

        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfCpf.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                if (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <Nome = " + trabalhador.getNome() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.excluir(trabalhador);
                }
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfCpf.setEditable(true);
                tfNome.setText("");
                tfSalario.setText("");
                cbAposentado.setSelected(false);
                cbAposentado.setEnabled(true);
                tfCpf.requestFocus();
                tfCpf.selectAll();
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                texto.setText("Excluiu o registro de " + trabalhador.getCpf() + " - " + trabalhador.getNome() + "\n\n\n\n\n");//limpa o campo texto
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Trabalhador> lt = controle.listar();
                texto.setText("Id - Nome - Salário - Aposentado\n");//limpa o textArea
                for (int i = 0; i < lt.size(); i++) {
                    texto.append(lt.get(i).getCpf() + "-"
                            + lt.get(i).getNome() + "-"
                            + lt.get(i).getSalario() + " - " + (lt.get(i).isAposentado() ? "Sim" : "Não") + "\n");
                }
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
            }
        });
        setVisible(true);

    }

}
