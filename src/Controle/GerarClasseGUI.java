package Controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GerarClasseGUI {

    public GerarClasseGUI(String nomeDaClasse, List<String> atributo, String caminho) {
        StringTools st = new StringTools();
        String[] aux;
        List<String> cg = new ArrayList();//código gerado
        String nomeDaClasseMin = st.plMinus(nomeDaClasse);
        cg.add("package GUIs;");

        cg.add("import Entidades." + nomeDaClasse + ";\n"
                + "import Controles." + nomeDaClasse + "Controle;\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.CardLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.FlowLayout;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.util.List;\n"
                + "import javax.swing.BorderFactory;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JDialog;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JTextField;\n"
                + "import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;\n"
                + "import javax.swing.table.DefaultTableModel;");

        cg.add("/**\n"
                + " *\n"
                + " * @author radames\n" + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + " */");

        cg.add("public class " + nomeDaClasse + "GUI extends JDialog{\n");
        cg.add("Container cp;\n"
                + "    JPanel pnNorte = new JPanel();\n"
                + "    JPanel pnCentro = new JPanel();\n"
                + "    JPanel pnSul = new JPanel();");
        cg.add("JButton btBuscar = new JButton(\"Buscar\");\n"
                + "    JButton btAdicionar = new JButton(\"Adicionar\");\n"
                + "    JButton btSalvar = new JButton(\"Salvar\");\n"
                + "    JButton btAlterar = new JButton(\"Alterar\");\n"
                + "    JButton btExcluir = new JButton(\"Excluir\");\n"
                + "    JButton btListar = new JButton(\"Listar\");\n"
                + "    JButton btCancelar = new JButton(\"Cancelar\");");
        cg.add("String acao = \"\";");
        cg.add("private JScrollPane scrollTabela = new JScrollPane();\n"
                + "\n"
                + "    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));\n"
                + "    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));\n"
                + "    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));\n"
                + "\n"
                + "    private CardLayout cardLayout;");
        cg.add("\n\n//////////////////// - mutável - /////////////////////////");
        cg.add("\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("JLabel lb" + st.plMaiusc(aux[1]) + " = new JLabel(\"" + st.plMaiusc(aux[1]) + "\");\n"
                    + "JTextField tf" + st.plMaiusc(aux[1]) + " = new JTextField(" + aux[2] + ");\n");
        }
        cg.add(nomeDaClasse + "Controle" + " controle = new " + nomeDaClasse + "Controle();\n"
                + "    " + nomeDaClasse + " " + nomeDaClasseMin + " = new " + nomeDaClasse + "();");
        cg.add("");
        String s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            s += "\"" + aux[1] + "\",";
        }
        s = s.substring(0, s.length() - 1);//retira a vírgula que está sobrando
        cg.add("String[] colunas = new String[]{" + s + "};");
        cg.add("String[][] dados = new String[0][colunas.length];\n"
                + "\n"
                + "    DefaultTableModel model = new DefaultTableModel(dados, colunas);\n"
                + "    JTable tabela = new JTable(model);");
        cg.add("");

        cg.add(" public " + nomeDaClasse + "GUI() {\n");
        cg.add(" setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n"
                + "        cp = getContentPane();\n"
                + "        cp.setLayout(new BorderLayout());\n"
                + "        setTitle(\"CRUD - " + nomeDaClasse + "\");\n"
                + "\n"
                + "        cp.add(pnNorte, BorderLayout.NORTH);\n"
                + "        cp.add(pnCentro, BorderLayout.CENTER);\n"
                + "        cp.add(pnSul, BorderLayout.SOUTH);\n"
                + "\n"
                + "        pnNorte.setBackground(Color.cyan);\n"
                + "        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));\n"
                + "\n"
                + "        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));");
        aux = atributo.get(0).split(";");
        cg.add(" pnNorte.add(lb" + st.plMaiusc(aux[1]) + ");\n"
                + "        pnNorte.add(tf" + st.plMaiusc(aux[1]) + ");\n"
                + "        pnNorte.add(btBuscar);\n"
                + "        pnNorte.add(btAdicionar);\n"
                + "        pnNorte.add(btAlterar);\n"
                + "        pnNorte.add(btExcluir);\n"
                + "        pnNorte.add(btListar);\n"
                + "        pnNorte.add(btSalvar);\n"
                + "        pnNorte.add(btCancelar);\n"
                + "\n"
                + "        btSalvar.setVisible(false);\n"
                + "        btAdicionar.setVisible(false);\n"
                + "        btAlterar.setVisible(false);\n"
                + "        btExcluir.setVisible(false);\n"
                + "        btCancelar.setVisible(false);");
        cg.add("pnCentro.setLayout(new GridLayout(colunas.length-1, 2));");
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("pnCentro.add(lb" + st.plMaiusc(aux[1]) + ");\n"
                    + "pnCentro.add(tf" + st.plMaiusc(aux[1]) + ");");

        }

        cg.add(" cardLayout = new CardLayout();\n"
                + "        pnSul.setLayout(cardLayout);\n"
                + "\n"
                + "        for (int i = 0; i < 5; i++) {\n"
                + "            pnVazio.add(new JLabel(\" \"));\n"
                + "        }\n"
                + "        pnSul.add(pnVazio, \"vazio\");\n"
                + "        pnSul.add(pnAvisos, \"avisos\");\n"
                + "        pnSul.add(pnListagem, \"listagem\");\n"
                + "        tabela.setEnabled(false);\n"
                + "\n"
                + "        pnAvisos.add(new JLabel(\"Avisos\"));");
        cg.add("String caminho = \"" + nomeDaClasse + ".csv\";\n"
                + "        //carregar dados do HD para memória RAM\n"
                + "        controle.carregarDados(caminho);");
        cg.add("\n\n// listener Buscar\n");
        cg.add(" btBuscar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                cardLayout.show(pnSul, \"avisos\");");

        aux = atributo.get(0).split(";");
        switch (aux[0]) {
            case "String":
                cg.add("  " + nomeDaClasseMin + " = controle.buscar(tf" + st.plMaiusc(aux[1]) + ".getText());");
                break;
            case "int":
                cg.add("  " + nomeDaClasseMin + " = controle.buscar(Integer.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");
                break;
            default:
                cg.add("  " + nomeDaClasseMin + " = controle.buscar(NaoSeiOTipo.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");

        }

        cg.add(" if (" + nomeDaClasseMin + " != null) {//achou o " + nomeDaClasseMin + " na lista\n"
                + "                    //mostrar\n"
                + "                    btAdicionar.setVisible(false);\n"
                + "                    btAlterar.setVisible(true);\n"
                + "                    btExcluir.setVisible(true);");

        String abre = "";
        String fecha = "";
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            switch (aux[0]) {
                case "String":
                    abre = "";
                    fecha = "";
                    break;
                default:
                    abre = "String.valueOf(";
                    fecha = ")";
            }

            cg.add("tf" + st.plMaiusc(aux[1]) + ".setText(" + abre
                    + nomeDaClasseMin + ".get" + st.plMaiusc(aux[1]) + "())" + fecha + ";\n"
                    + "tf" + st.plMaiusc(aux[1]) + ".setEditable(false);\n");

        }
        cg.add(" } else {//não achou na lista\n"
                + "                    //mostrar botão incluir\n"
                + "                    btAdicionar.setVisible(true);\n"
                + "                    btAlterar.setVisible(false);\n"
                + "                    btExcluir.setVisible(false);");
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\");                 \n"
                    + "                    tf" + st.plMaiusc(aux[1]) + ".setEditable(false); ");
        }
        cg.add("             }\n"
                + "            }\n"
                + "        });");

        cg.add("\n\n// listener Adicionar\n");
        cg.add(" btAdicionar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {");

        aux = atributo.get(0).split(";");
        cg.add("tf" + st.plMaiusc(aux[1]) + ".setEnabled(false);");
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (i == 1) {
                cg.add("tf" + st.plMaiusc(aux[1]) + ".requestFocus();");
            }
            cg.add(" tf" + st.plMaiusc(aux[1]) + ".setEditable(true);");

        }

        cg.add(" btAdicionar.setVisible(false);\n"
                + "                btSalvar.setVisible(true);\n"
                + "                btCancelar.setVisible(true);\n"
                + "                btBuscar.setVisible(false);\n"
                + "                btListar.setVisible(false);\n"
                + "                acao = \"adicionar\";\n"
                + "            }\n"
                + "        });");

        cg.add("\n\n// listener Salvar\n");
        cg.add(" btSalvar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                if (acao.equals(\"adicionar\")) {\n"
                + "                    " + nomeDaClasseMin + " = new " + nomeDaClasse + "();\n"
                + "                }\n"
                + "                " + nomeDaClasse + " " + nomeDaClasseMin + "Antigo = " + nomeDaClasseMin + ";");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            fecha = ")";
            switch (aux[0]) {
                case "String":
                    abre = "";
                    fecha = "";
                    break;
                case "int":
                    abre = "Integer.valueOf(";
                    break;
                case "double":
                    abre = "Double.valueOf(";
                    break;
                default:
                    abre = "DESCONHECIDO.valueOf(";

            }
            cg.add("" + nomeDaClasseMin + ".set" + st.plMaiusc(aux[1]) + "(" + abre + "tf" + st.plMaiusc(aux[1]) + ".getText())" + fecha + ";");
        }

        cg.add("if (acao.equals(\"adicionar\")) {\n"
                + "                    controle.adicionar(" + nomeDaClasseMin + ");\n"
                + "                } else {\n"
                + "                    controle.alterar(" + nomeDaClasseMin + ", " + nomeDaClasseMin + "Antigo);\n"
                + "                }\n"
                + "                btSalvar.setVisible(false);\n"
                + "                btCancelar.setVisible(false);\n"
                + "                btBuscar.setVisible(true);\n"
                + "                btListar.setVisible(true);");
        aux = atributo.get(0).split(";");
        cg.add(" tf" + st.plMaiusc(aux[1]) + ".setEnabled(true);\n"
                + "                tf" + st.plMaiusc(aux[1]) + ".setEditable(true);\n"
                + "                tf" + st.plMaiusc(aux[1]) + ".requestFocus();\n"
                + "                tf" + st.plMaiusc(aux[1]) + ".setText(\"\");");

        cg.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\");\n");
        cg.add("\n"
                + "            }\n"
                + "        });");
        cg.add("\n\n// listener Alterar\n");
        cg.add(" btAlterar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                btBuscar.setVisible(false);\n"
                + "                btAlterar.setVisible(false);");

        aux = atributo.get(0).split(";");
        cg.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(false);\n");
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (i == 1) {
                cg.add(" tf" + st.plMaiusc(aux[1]) + ".requestFocus();");
            }
            cg.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(true);\n");
        }
        aux = atributo.get(0).split(";");
        cg.add(" btSalvar.setVisible(true);\n"
                + "                btCancelar.setVisible(true);\n"
                + "                btListar.setVisible(false);\n"
                + "tf" + st.plMaiusc(aux[1]) + ".setEnabled(true);"
                + "                btExcluir.setVisible(false);\n"
                + "                acao = \"alterar\";\n"
                + "\n"
                + "            }\n"
                + "        });");
        cg.add("");
        cg.add("\n\n// listener Excluir\n");
        cg.add("  btExcluir.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "\n"
                + "                int response = JOptionPane.showConfirmDialog(cp, \"Confirme a exclusão?\", \"Confirm\",\n"
                + "                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);\n"
                + "\n"
                + "                btExcluir.setVisible(false);");
        aux = atributo.get(0).split(";");

        cg.add("tf" + st.plMaiusc(aux[1]) + ".setEnabled(true);\n");
        cg.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(true);\n");
        cg.add("tf" + st.plMaiusc(aux[1]) + ".requestFocus();\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");

            cg.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\"); ");
            if (i > 0) {
                cg.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(false);");
            }
        }

        cg.add(" btAlterar.setVisible(false);\n"
                + "                if (response == JOptionPane.YES_OPTION) {\n"
                + "                    controle.excluir(" + nomeDaClasseMin + ");\n"
                + "                }\n"
                + "            }\n"
                + "        });");

        cg.add("\n\n// listener Listar\n");
        cg.add(" btListar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                List<" + nomeDaClasse + "> lista" + nomeDaClasse + " = controle.listar();");
        s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            s += "\"" + aux[1] + "\",";
        }
        s = s.substring(0, s.length() - 1);//retira a vírgula que está sobrando
        cg.add("String[] colunas = new String[]{" + s + "};");
        cg.add("String[][] dados = new String[lista" + nomeDaClasse + ".size()][colunas.length];\n");

        cg.add("String aux[];\n"
                + "                for (int i = 0; i < lista" + nomeDaClasse + ".size(); i++) {\n"
                + "                    aux = lista" + nomeDaClasse + ".get(i).toString().split(\";\");\n"
                + "                    for (int j = 0; j < colunas.length; j++) {\n"
                + "                        dados[i][j] = aux[j];\n"
                + "                    }\n"
                + "                }");
        cg.add(" cardLayout.show(pnSul, \"listagem\");\n"
                + "                scrollTabela.setPreferredSize(tabela.getPreferredSize());\n"
                + "                pnListagem.add(scrollTabela);\n"
                + "                scrollTabela.setViewportView(tabela);\n"
                + "                model.setDataVector(dados, colunas);\n"
                + "\n"
                + "                btAlterar.setVisible(false);\n"
                + "                btExcluir.setVisible(false);\n"
                + "                btAdicionar.setVisible(false);\n"
                + "\n"
                + "            }\n"
                + "        });");

        cg.add("\n\n// listener Cancelar\n");
        cg.add(" btCancelar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                btCancelar.setVisible(false);");
        aux = atributo.get(0).split(";");

        cg.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\");\n"
                + "                tf" + st.plMaiusc(aux[1]) + ".requestFocus();\n"
                + "tf" + st.plMaiusc(aux[1]) + ".setEnabled(true);\n"
                + "tf" + st.plMaiusc(aux[1]) + ".setEditable(true);");
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\"); ");
            cg.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(false);");
        }
        cg.add(" btBuscar.setVisible(true);\n"
                + "                btListar.setVisible(true);\n"
                + "                btSalvar.setVisible(false);\n"
                + "                btCancelar.setVisible(false);\n"
                + "\n"
                + "            }\n"
                + "        });");
        cg.add("\n\n// listener ao fechar o programa\n");
        cg.add(" addWindowListener(new WindowAdapter() {\n"
                + "            @Override\n"
                + "            public void windowClosing(WindowEvent e) {\n"
                + "                //antes de sair, salvar a lista em armazenamento permanente\n"
                + "                controle.gravarLista(caminho);\n"
                + "                // Sai da classe\n"
                + "                dispose();\n"
                + "            }\n"
                + "        });\n"
                + "\n"
                + "        setModal(true);\n"
                + "        pack();\n"
                + "        setLocationRelativeTo(null);//centraliza na tela\n"
                + "        setVisible(true);");
        cg.add("");

        cg.add("}//fim do contrutor de GUI\n");

        cg.add("} //fim da classe");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho+"/src/GUIs/" + nomeDaClasse + "GUI.java", cg);

    }

}
