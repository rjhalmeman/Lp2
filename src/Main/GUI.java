package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author radames
 */
public class GUI extends JFrame {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();

    JLabel lbCpf = new JLabel("CPF");
    JTextField tfCpf = new JTextField(15);
    JButton btBuscar = new JButton("Buscar");
    Controle controle = new Controle();

    JLabel lbAviso = new JLabel("");

    JLabel lbNome = new JLabel("Nome");
    JTextField tfNome = new JTextField(30);
    JLabel lbSobrenome = new JLabel("Sobrenome");
    JTextField tfSobrenome = new JTextField(30);
    JLabel lbDataNascimento = new JLabel("Nascimento");
    JTextField tfDataNascimento = new JTextField(30);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btSalvar = new JButton("Salvar");
    Pessoa pessoa;

    public GUI() {
        //////////////////// insere dados para testes /////////////
        pessoa = new Pessoa();
        pessoa.setCpf("111");
        pessoa.setNome("Berola");
        pessoa.setSobrenome("silva");
        pessoa.setDataNascimento(new Date());

        controle.inserir(pessoa);
        pessoa = new Pessoa("555", "Timocréia", "Soutier", new Date());
        controle.inserir(pessoa);
        pessoa = new Pessoa("333", "Reduzina", "Soutier", new Date());
        controle.inserir(pessoa);
        pessoa = new Pessoa("222", "Zulida", "Soutier", new Date());
        controle.inserir(pessoa);

        List<Pessoa> lp = controle.listar();

        for (Pessoa p : lp) {
            System.out.println(p);
        }
        ////////////////////////////

        cp = getContentPane();
        cp.setLayout(new BorderLayout());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.red);
        pnCentro.setBackground(Color.white);
        pnSul.setBackground(Color.yellow);

        pnNorte.add(lbCpf);
        pnNorte.add(tfCpf);
        pnNorte.add(btBuscar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btSalvar);

        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btSalvar.setVisible(false);

        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbSobrenome);
        pnCentro.add(tfSobrenome);
        pnCentro.add(lbDataNascimento);
        pnCentro.add(tfDataNascimento);

        pnSul.add(lbAviso);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pessoa = controle.buscar(tfCpf.getText());
                if (pessoa == null) {//não achou
                    lbAviso.setText("Pessoa não cadastrada");
                    tfNome.setText("");
                    tfSobrenome.setText("");
                    tfDataNascimento.setText("");
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);
                } else {//achou
                    lbAviso.setText("Achou");
                    tfNome.setText(pessoa.getNome());
                    tfSobrenome.setText(pessoa.getSobrenome());
                    tfDataNascimento.setText(sdf.format(pessoa.getDataNascimento()));
                    btAlterar.setVisible(true);
                    btExcluir.setVisible(true);
                    tfNome.setEditable(false);
                    tfSobrenome.setEditable(false);
                    tfDataNascimento.setEditable(false);
                }

            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfCpf.setEditable(false);//travei o atributo
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btSalvar.setVisible(true);
                btBuscar.setVisible(false);
                tfNome.setEditable(true);
                tfSobrenome.setEditable(true);
                tfDataNascimento.setEditable(true);

            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Pessoa atual = pessoa;
                //daqui para frente alteramos os valores da pessoa (pessoaAlterada)
                pessoa.setCpf(tfCpf.getText());
                pessoa.setNome(tfNome.getText());
                pessoa.setSobrenome(tfSobrenome.getText());
                try {
                    pessoa.setDataNascimento(sdf.parse(tfDataNascimento.getText()));
                } catch (ParseException ex) {
                    lbAviso.setText("erro na data");
                }
                controle.alterar(atual, pessoa);
                List<Pessoa> lp = controle.listar();
                for (Pessoa p : lp) {
                    System.out.println(p);
                }
            }
        });

        //setSize(800, 300);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);

    }

}
