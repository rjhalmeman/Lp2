package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author radames
 */
public class GUI extends JFrame {

    Container cp;

    JLabel lbCpf = new JLabel("CPF");
    JTextField tfCpf = new JTextField(20);
    JLabel lbNome = new JLabel("Nome");
    JTextField tfNome = new JTextField(50);
    JLabel lbSalario = new JLabel("Salário");
    JTextField tfSalario = new JTextField(20);
    JButton btAdicionar = new JButton("Adicionar");
    JButton btListar = new JButton("Listar");
    JButton btBuscar = new JButton("Buscar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btSalvar = new JButton("Salvar");
    JButton btCancelar = new JButton("Cancelar");
    JToolBar toolBar = new JToolBar();
    JPanel painelNorte = new JPanel();
    JPanel painelCentro = new JPanel();
    JPanel painelSul = new JPanel();
    JTextArea texto = new JTextArea();

    String acao = "";

    Controle controle = new Controle();
    Trabalhador trabalhador = new Trabalhador();

    public GUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(400, 300);
        setTitle("CRUD Trabalhador");
        setLocationRelativeTo(null);//centro do monitor

        cp = getContentPane();

        cp.setLayout(new BorderLayout());
        cp.add(painelNorte, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelSul.setLayout(new GridLayout(1, 1));
        //   JScrollBar scrollBar = new JScrollBar();
        painelSul.add(texto);

        painelNorte.add(toolBar);

        painelCentro.setLayout(new GridLayout(3, 2));
        painelCentro.add(lbCpf);
        painelCentro.add(tfCpf);
        painelCentro.add(lbNome);
        painelCentro.add(tfNome);
        painelCentro.add(lbSalario);
        painelCentro.add(tfSalario);

        toolBar.add(btAdicionar);
        toolBar.add(btListar);
        toolBar.add(btBuscar);
        toolBar.add(btAlterar);
        toolBar.add(btExcluir);
        toolBar.add(btSalvar);
        toolBar.add(btCancelar);

        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);
                if (tfCpf.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(cp,
                            "CPF nâo pode ser vazio");
                    tfCpf.requestFocus();
                    tfCpf.selectAll();
                } else {
                    trabalhador = controle.buscar(tfCpf.getText());
                    if (trabalhador == null) {//nao encontrou
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfSalario.setText("");

                    } else {//encontrou
                        tfNome.setText(trabalhador.getNome());
                        tfSalario.setText(String.valueOf(trabalhador.getSalario()));
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);

                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfCpf.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);

                btAdicionar.setVisible(false);
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfCpf.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
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
                tfCpf.requestFocus();
                tfCpf.selectAll();
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("alterar")) {
                    Trabalhador trabalhadorAntigo = trabalhador;
                    trabalhador.setNome(tfNome.getText());
                    trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                    controle.alterar(trabalhador, trabalhadorAntigo);
                } else {//adicionar
                    trabalhador = new Trabalhador();
                    trabalhador.setCpf(tfCpf.getText());
                    trabalhador.setNome(tfNome.getText());
                    trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                    controle.adicionar(trabalhador);
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
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                tfCpf.requestFocus();
                tfCpf.selectAll();
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);

            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Trabalhador> lt = controle.listar();
                texto.setText("");//limpa o textArea
                for (int i = 0; i < lt.size(); i++) {
                    texto.append(lt.get(i).getCpf() + "-"
                            + lt.get(i).getNome() + "-"
                            + lt.get(i).getSalario() + "\n");
                }
            }
        });

        setVisible(true);

    }

}
