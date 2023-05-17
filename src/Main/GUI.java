/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author radames
 */
class GUI extends JFrame {

    //variáreis globais
    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbId = new JLabel("Id");
    JTextField tfId = new JTextField(10);
    JButton btBuscar = new JButton("Buscar");
    Controle controle = new Controle();
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
  //  JButton btSalvar = new JButton("");

    public GUI() {

        cp = getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        pnNorte.add(btSalvar);

        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbPreco);
        pnCentro.add(tfPreco);
        pnCentro.add(lbUnidadeDeMedida);
        pnCentro.add(tfUnidadeDeMedida);

        pnSul.add(lbAviso);

        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                produto = controle.buscar(Integer.valueOf(tfId.getText()));
                if (produto == null) {//não achou
                    lbAviso.setText("Não achou na lista");
                    btAdicionar.setVisible(true);
                } else {//encontra na lista
                    tfId.setText(String.valueOf(produto.getIdProduto()));
                    tfNome.setText(produto.getNomeProduto());
                    tfPreco.setText(String.valueOf(produto.getPrecoProduto()));
                    tfUnidadeDeMedida.setText(produto.getUnidadeDeMedida());
                }

            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfId.setEditable(false);
                tfNome.requestFocus();
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btBuscar.setVisible(false);
            }
        });
        
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                produto = new Produto();
                produto.setIdProduto(Integer.valueOf(tfId.getText()));
                produto.setNomeProduto(tfNome.getText());
                produto.setPrecoProduto(Double.valueOf(tfPreco.getText()));
                produto.setUnidadeDeMedida(tfUnidadeDeMedida.getText());
                controle.inserir(produto);
                
                tfId.setText("");
                tfNome.setText("");
                tfPreco.setText("");
                tfUnidadeDeMedida.setText("");
                tfId.requestFocus();
                tfId.setEditable(true);
                
                btBuscar.setVisible(true);
                btSalvar.setVisible(false);
                
            }
        });
        

        //setSize(800, 300);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
