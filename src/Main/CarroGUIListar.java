package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author radames
 */
class CarroGUIListar extends JDialog {

    private Container cp;

    private String[] colunas = {"Placa", "Nome", "Peso", "Lançamento","Cor","Seguro"};
    private String[][] dados = new String[0][colunas.length];
    private DefaultTableModel model = new DefaultTableModel(dados, colunas);
    private JTable tabela = new JTable(model);
    private JScrollPane scrollTabela = new JScrollPane();
    private String idSelecionado;
    private JPanel pnCentro = new JPanel();
    private JPanel pnSul = new JPanel();
    private JButton btFechar = new JButton("Fechar");
    
    

    public CarroGUIListar(CarroControle controle, Point coordenadas, Dimension dimensao, List <String> cores) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        
        setUndecorated(true);//sem barra de título
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnCentro,BorderLayout.CENTER);
        cp.add(pnSul,BorderLayout.SOUTH);
        
        
        pnCentro.setLayout(new GridLayout(1, 1));
        pnCentro.add(scrollTabela);

        pnSul.add(btFechar);
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
        
        btFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                idSelecionado = null;
                dispose();//fecha sem escolher nenhum da lista
            }
        });
        

        setSize(dimensao);
        setLocation((int) coordenadas.getX(), (int) coordenadas.getY() + 30);

//        setLocationRelativeTo(null);
        setVisible(true);

    }

    public String getIdSelecionado() {
        return idSelecionado;
    }

}
