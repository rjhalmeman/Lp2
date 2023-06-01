package Main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author radames
 */
class CarroGUIListar extends JDialog {

    Container cp;

    String[] colunas = {"Placa", "Nome", "Peso", "Lançamento"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
    JScrollPane scrollTabela = new JScrollPane();
    private String idSelecionado;

    public CarroGUIListar(CarroControle controle, Point coordenadas, Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        //  setTitle("Relatório de produto");

        setUndecorated(true);//sem barra de título
        cp = getContentPane();
        cp.setLayout(new GridLayout(1, 1));

        cp.add(scrollTabela);

        List<Carro> listaDados = controle.listar();

        Object[][] dados = new Object[listaDados.size()][colunas.length];
        String aux[];
        for (int i = 0; i < listaDados.size(); i++) {
            aux = listaDados.get(i).toString().split(";");
            for (int j = 0; j < colunas.length; j++) {
                dados[i][j] = aux[j];
            }
        }

        scrollTabela.setPreferredSize(tabela.getPreferredSize());

        scrollTabela.setViewportView(tabela);
        model.setDataVector(dados, colunas);

        tabela.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabela.getSelectedRow();
                if (selectedRow != -1) {
                    Object value = tabela.getValueAt(selectedRow, 0);
                    idSelecionado = String.valueOf(value);
                    dispose();
                }
            }
        });

//        tabela.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent me) {
//                System.out.println("linha ");
//                dispose();//ao clicar no table, fecha o relatório
//            }
//
//            @Override
//            public void mousePressed(MouseEvent me) {
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent me) {
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent me) {
//            }
//
//            @Override
//            public void mouseExited(MouseEvent me) {
//            }
//        });
        setSize(dimensao);
        setLocation((int) coordenadas.getX(), (int) coordenadas.getY() + 30);

//        setLocationRelativeTo(null);
        setVisible(true);

    }

    public String getIdSelecionado() {
        return idSelecionado;
    }

}
