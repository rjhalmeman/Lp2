package Main;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

    Controle controle = new Controle();
    Trabalhador trabalhador = new Trabalhador();

    public GUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(400, 300);
        setTitle("CRUD Trabalhador");
        setLocationRelativeTo(null);//centro do monitor

        cp = getContentPane();
        cp.setLayout(new GridLayout(7, 2));
        cp.add(lbCpf);
        cp.add(tfCpf);
        cp.add(lbNome);
        cp.add(tfNome);
        cp.add(lbSalario);
        cp.add(tfSalario);
        cp.add(btAdicionar);
        cp.add(btListar);
        cp.add(btBuscar);
        cp.add(btAlterar);
        cp.add(btExcluir);
        cp.add(btSalvar);
        cp.add(btCancelar);

        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                trabalhador = new Trabalhador();
                trabalhador.setCpf(tfCpf.getText());
                trabalhador.setNome(tfNome.getText());
                trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                controle.adicionar(trabalhador);
                btAdicionar.setVisible(false);
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                Trabalhador trabalhadorAntigo = trabalhador;
                trabalhador.setNome(tfNome.getText());
                trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                controle.alterar(trabalhador, trabalhadorAntigo);
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
                controle.listar();
            }
        });

        setVisible(true);

    }

}
