package GUIs;

import daos.ControleTipoConta;
import tools.CentroDoMonitorMaior;

import entidades.TipoConta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.AbstractCellEditor;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

public class TipoContaGUI_JTable extends JDialog {

    private final Container cp;
    private final JPanel painelAvisos = new JPanel();
    private final JButton btnAdd = new JButton("Adicionar");
    private final JButton btnRem = new JButton("Remover");
    private final JButton btnCarregar = new JButton("Carregar dados");

    private JTable table = new JTable();
    private TipoContaTableModel tableModel;
    
    String caminho = "TipoConta.csv";

   
    private ControleTipoConta controleTipoConta = new ControleTipoConta();

    public TipoContaGUI_JTable() {

        setTitle("CRUD Tipo Conta");
        setLayout(new FlowLayout());
        setSize(600, 500);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.NORTH, painelAvisos);

        List<TipoConta> lista = new ArrayList<>();
        tableModel = new TipoContaTableModel(lista);
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane);

//        painelBotoes.add(btnAdd);
//        painelBotoes.add(btnRem);
        painelAvisos.add(new JLabel("Tecla INS = Insere novo registro"));
        painelAvisos.add(new JLabel("   --   "));
        painelAvisos.add(new JLabel("Tecla DEL = Exclui registro selecionado"));
        painelAvisos.setBackground(Color.cyan);       
             
        btnCarregar.doClick();
        
        table.setDefaultEditor(Date.class, new DateEditor());
        table.setDefaultRenderer(Date.class, new DateRenderer());
       
        // É necessário clicar antes na tabela para o código funcionar
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = table.getActionMap();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0);
        im.put(enterKey, "Action.insert");

        actionMap.put("Action.insert", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnAdd.doClick();
            }
        });

        KeyStroke delKey = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        im.put(delKey, "Action.delete");

        actionMap.put("Action.delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (table.getSelectedRow()>=0) {

                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(cp,
                            "Confirma a exclusão desse tipo de conta [" + tableModel.getValue(table.getSelectedRow()).getIdTipoConta()+ " - "
                            + tableModel.getValue(table.getSelectedRow()).getNomeTipoConta()+ "]?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                        btnRem.doClick();
                    }
                } else {
                     JOptionPane.showMessageDialog(cp, "Escolha na tabela a conta a ser excluída");
                }
            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                controleTipoConta.gravarLista(caminho);                
                dispose();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoConta tipoConta = new TipoConta();               
                tipoConta.setIdTipoConta(controleTipoConta.autoIdTipoConta());
                tipoConta.setNomeTipoConta("nova");               
                try {
                    TipoConta c = controleTipoConta.buscar(tipoConta.getIdTipoConta());
                    if (c == null) {                       
                        controleTipoConta.adicionar(tipoConta);
                       // tableModel.onAdd(tipoConta);
                        tableModel.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(cp, "Já existe esse tipo conta");
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(cp,"Erro ao inserir a conta =>" + tipoConta.getIdTipoConta()
                            + " com o erro=>" + err.getMessage());
                }

            }
        });

        btnRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    TipoConta c = tableModel.getValue(table.getSelectedRow());
                    controleTipoConta.excluir(c);
                    tableModel.onRemove(table.getSelectedRows());

                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela o tipo de conta a ser excluído");
                    table.requestFocus();
                }
                tableModel.fireTableDataChanged();
            }
        });

        btnCarregar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                
                try {                 
                    controleTipoConta.carregarDados(caminho);  
                    List<TipoConta> lc = controleTipoConta.listar();
                    tableModel.setDados(lc);
                    tableModel.fireTableDataChanged();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro ao carregar os dados..." + ex.getMessage());
                }
            }

        });

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // if (tableModel.mudou) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    TipoConta c = tableModel.getValue(table.getSelectedRow());
                   // controleTipoConta.alterar(c);
                }
                //}
            }
        }
        );

        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();

        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        btnCarregar.doClick();//carrega os dados 

        setModal(true);
        setVisible(true);

    } //fim do construtor da GUI

    private static class DateRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!(value instanceof Date)) {
                return this;
            }
            DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            setText(DATE_FORMAT.format((Date) value));
            return this;
        }
    }

    private static class DateEditor extends AbstractCellEditor implements TableCellEditor {

        private static final long serialVersionUID = 1L;
        private final JSpinner theSpinner;
        private Object value;

        DateEditor() {
            theSpinner = new JSpinner(new SpinnerDateModel());
            theSpinner.setOpaque(true);
            theSpinner.setEditor(new JSpinner.DateEditor(theSpinner, "dd/MM/yyyy"));
        }

        @Override
        public Object getCellEditorValue() {
            return theSpinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            theSpinner.setValue(value);
            if (isSelected) {
                theSpinner.setBackground(table.getSelectionBackground());
            } else {
                theSpinner.setBackground(table.getBackground());
            }
            return theSpinner;
        }
    }
}
