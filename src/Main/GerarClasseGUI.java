package Main;

import java.util.List;

import tools.ManipulaArquivo;
import tools.Tools;

/**
 * import java.util.ArrayList; import java.util.List; import
 * tools.ManipulaArquivo;
 *
 * @author presa
 */
public class GerarClasseGUI {

    String nomeDaClasse;
    List<String> atributo;
    List<String> codigo;
    Tools st = new Tools();

    public GerarClasseGUI(String nomeDaClasse, List<String> atributo, List<String> codigo, String projetoDestino) {
        this.nomeDaClasse = nomeDaClasse;
        this.atributo = atributo;
        this.codigo = codigo;

        Tools t = new Tools();
        String nomeDaClasseMinuscula = nomeDaClasse.toLowerCase();

        //verificar se tem Date, para criar import
        String stringImports = "";
        for (int i = 0; i < atributo.size(); i++) {
            if (atributo.get(i).contains("Date")) {
                stringImports = "import java.util.Date;\n"
                        + "import tools.DateTextField;\n";
            }
        }

        codigo.add("package Main; \n"
                + "import java.awt.BorderLayout; \n"
                + "import java.awt.CardLayout; \n"
                + "import java.awt.Container;\n"
                + "import java.awt.GridLayout; \n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JCheckBox;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JTextArea;\n"
                + "import javax.swing.JTextField;\n"
                + "import javax.swing.JToolBar;\n"
                + "import javax.swing.table.DefaultTableModel;\n"
                + stringImports);

        codigo.add("public class " + nomeDaClasse + "GUI extends JFrame {");

        codigo.add("private Container cp; \n");
        String s = "";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("private JLabel lb" + t.primeiraLetraMaiscula(aux[1]) + " = " + "new JLabel(" + "\"" + aux[1] + "\"" + ")" + ";"
            );
        }

        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean")) {
                codigo.add("private JCheckBox cb" + t.primeiraLetraMaiscula(aux[1]) + "=" + "new JCheckBox(" + "\"" + t.primeiraLetraMaiscula(aux[1]) + "\"" + "," + "false" + ");");
            }
            if (aux[0].equals("Date")) {
                codigo.add("private DateTextField tf" + t.primeiraLetraMaiscula(aux[1]) + "= new DateTextField();");
            } else {
                //fazer o if que vai envolver string boolean double etc
                codigo.add("private JTextField tf" + t.primeiraLetraMaiscula(aux[1]) + "= new JTextField(50);");
            }
        }
        codigo.add("private JButton btAdicionar = new JButton(\"Adicionar\"); \n"
                + "private JButton btListar = new JButton(\"Listar\"); \n"
                + "private JButton btBuscar = new JButton(\"Buscar\"); \n"
                + "private JButton btAlterar = new JButton(\"Alterar\"); \n"
                + "private JButton btExcluir = new JButton(\"Excluir\"); \n"
                + "private JButton btSalvar = new JButton(\"Salvar\"); \n"
                + "private JButton btCancelar = new JButton(\"Cancelar\"); \n"
                + "private JButton btCarregarDados = new JButton(\"Carregar\"); \n"
                + "private JButton btGravar = new JButton(\"Gravar\"); \n"
                + "private JToolBar toolBar = new JToolBar(); \n"
                + "private JPanel painelNorte = new JPanel(); \n"
                + "private JPanel painelCentro = new JPanel(); \n"
                + "private JPanel painelSul = new JPanel(); \n"
                + "private JTextArea texto = new JTextArea();\n"
                + "private JScrollPane scrollTexto = new JScrollPane(); \n"
                + " private JScrollPane scrollTabela = new JScrollPane(); \n");

        codigo.add("private String acao = \"\"; \n"
                + "private String chavePrimaria = \"\"; \n"
                + "private " + nomeDaClasse + "Controle controle = new " + nomeDaClasse + "Controle(); \n"
                + "private" + " " + nomeDaClasse + " " + nomeDaClasseMinuscula + " " + " = " + "new " + nomeDaClasse + "(); \n"
                + "");

//        for (int i = 0; i < atributo.size(); i++) {
//            String aux[] = atributo.get(i).split(";");
//            }
        codigo.add("String[][] dados = new String[0]" + "[" + atributo.size() + "]" + ";\n\n");

//     String [] colunas = {"id","nome","altura","dataNasc"};    
        String x = "";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");          
                x +="\""+aux[1]+"\"" + ",";
        }
        x = x.substring(0, x.length() - 1);
        codigo.add("String [] colunas = {"+x+"};\n");

        codigo.add("\n "
                + "DefaultTableModel model = new DefaultTableModel(dados, colunas);\n");

        codigo.add("JTable tabela = new JTable(model);\n");
        codigo.add("private JPanel painel1 = new JPanel(new GridLayout(1, 1));\n");
        codigo.add("private JPanel painel2 = new JPanel(new GridLayout(1, 1));\n");
        codigo.add("\n private CardLayout cardLayout;\n");

        codigo.add("public " + nomeDaClasse + "GUI(){ \n"
                + "\n"
                + "String caminhoENomeDoArquivo = " + "\"" + "\"" + ";"
                + "\nsetDefaultCloseOperation(DISPOSE_ON_CLOSE);"
                + "setSize(600, 400); \n"
                + "setTitle(\"CRUD Canguru - V6a\"); \n"
                + "setLocationRelativeTo(null);  \n");

        codigo.add("cp = getContentPane(); \n"
                + "cp.setLayout(new BorderLayout()); \n"
                + "cp.add(painelNorte, BorderLayout.NORTH); \n"
                + "cp.add(painelCentro, BorderLayout.CENTER); \n"
                + "cp.add(painelSul, BorderLayout.SOUTH); \n");

        codigo.add("cardLayout = new CardLayout(); \n"
                + "painelSul.setLayout(cardLayout); \n"
                + "painel1.add(scrollTexto); \n"
                + "painel2.add(scrollTabela); \n");

        codigo.add("texto.setText(\"\\n\\n\\n\\n\\n\\n\"); \n"
                + "scrollTexto.setViewportView(texto); \n"
                + "painelSul.add(painel1, \"Avisos\"); \n"
                + "painelSul.add(painel2, \"Listagem\"); \n"
                + "tabela.setEnabled(false);");

        codigo.add("        painelNorte.add(toolBar);\n"
                + "painelNorte.setLayout(new GridLayout(1, 1)); \n"
                + "painelCentro.setLayout(new GridLayout(3, 2)); \n");

        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean")) {
                codigo.add("painelCentro.add(cb" + t.primeiraLetraMaiscula(aux[1]) + ");");
            }
            if (aux[0].equals("int") || aux[0].equals("double") || aux[0].equals("float") || aux[0].equals("String")) {
                //conferir o date
                codigo.add("painelCentro.add(lb" + t.primeiraLetraMaiscula(aux[1]) + ");" + "\n"
                        + "painelCentro.add(tf" + t.primeiraLetraMaiscula(aux[1]) + "); \n");
            }

        }
        String aux[] = atributo.get(0).split(";");
        codigo.add("toolBar.add(" + "tf" + t.primeiraLetraMaiscula(aux[1]) + "); \n"
                + "toolBar.add(btAdicionar); \n"
                + " toolBar.add(btBuscar); \n"
                + "toolBar.add(btListar); \n"
                + "toolBar.add(btAlterar); \n"
                + "toolBar.add(btExcluir); \n"
                + "toolBar.add(btSalvar); \n"
                + "toolBar.add(btCancelar); \n");

        codigo.add("btAdicionar.setVisible(false); \n"
                + "btAlterar.setVisible(false); \n"
                + "btExcluir.setVisible(false); \n"
                + "btSalvar.setVisible(false); \n");

        for (int i = 0; i < atributo.size(); i++) {
            String auxx[] = atributo.get(i).split(";");
            if (auxx[0].equals("boolean")) {
                codigo.add("cb" + t.primeiraLetraMaiscula(auxx[1]) + ".setEnabled" + "(false)" + "; \n");
            }
            if (auxx[0].equals("int") || auxx[0].equals("double") || auxx[0].equals("float") || auxx[0].equals("String")) {
                //conferir o date
                codigo.add("tf" + t.primeiraLetraMaiscula(auxx[1]) + ".setEditable" + "(false)" + "; \n");
            }

        }
        codigo.add("texto.setEditable(false);");
        String auxx[] = atributo.get(0).split(";");
        String tipo = auxx[1];
        if (auxx[0].equals("int")) {
            tipo = "Integer";
        }
        codigo.add("\n\n //-------------------botao buscar----------------\n");
        codigo.add("btBuscar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                btAdicionar.setVisible(false);\n"
                + "                if (tf" + st.primeiraLetraMaiscula(auxx[1]) + ".getText().trim().isEmpty()) {\n"
                + "                    JOptionPane.showMessageDialog(cp, \"" + auxx[1] + " nâo pode ser vazio\");\n"
                + "                    tf" + st.primeiraLetraMaiscula(auxx[1]) + ".requestFocus();\n"
                + "                    tf" + st.primeiraLetraMaiscula(auxx[1]) + ".selectAll();\n"
                + "                } else {\n"
                + "                    chavePrimaria = tf" + st.primeiraLetraMaiscula(auxx[1]) + ".getText();//para uso no adicionar\n"
                + "                    " + nomeDaClasseMinuscula + " = controle.buscar(" + st.primeiraLetraMaiscula(tipo) + ".valueOf(tf" + st.primeiraLetraMaiscula(auxx[1]) + ".getText()));\n"
                + "                    if (" + nomeDaClasseMinuscula + " == null) {//nao encontrou\n"
                + "                        btAdicionar.setVisible(true);\n"
                + "                        btAlterar.setVisible(false);\n"
                + "                        btExcluir.setVisible(false);\n");
        for (int i = 1; i < atributo.size(); i++) {
            String auxxx[] = atributo.get(i).split(";");
            codigo.add("tf" + st.primeiraLetraMaiscula(auxxx[1]) + ".setText(\"\");\n");

        }
        codigo.add("                        texto.setText(\"Não encontrou na lista - pode Adicionar\\n\\n\\n\");//limpa o campo texto\n");
        codigo.add(""
                + "                    } else {//encontrou\n");
        for (int i = 1; i < atributo.size(); i++) {
            String auxxx[] = atributo.get(i).split(";");
            codigo.add("tf" + st.primeiraLetraMaiscula(auxxx[1]) + ".setText(String.valueOf(" + nomeDaClasseMinuscula + ".get" + st.primeiraLetraMaiscula(auxxx[1]) + "()));\n");

        }
        codigo.add(
                "                        btAlterar.setVisible(true);\n"
                + "                        btExcluir.setVisible(true);\n"
                + "                        texto.setText(\"Encontrou na lista - pode Alterar ou Excluir\\n\\n\\n\");//limpa o campo texto\n"
                + "                        "
        );
        for (int i = 1; i < atributo.size(); i++) {
            String auxxx[] = atributo.get(i).split(";");
            codigo.add("tf" + st.primeiraLetraMaiscula(auxxx[1]) + ".setEditable(false);\n");

        }
        codigo.add(
                "                    }\n"
                + "                }\n"
                + "            }\n"
                + "        });");
        codigo.add("}\n");
        
        
        
        
        codigo.add("\n}//fim da classe");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(projetoDestino + "/src/Main/" + nomeDaClasse + "GUI.java", codigo);

    }

}
