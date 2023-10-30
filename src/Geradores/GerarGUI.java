package Geradores;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GerarGUI {

    public GerarGUI(List<String> atributo, String nomeClasse, String caminhoProjetoDestino) {

        StringTools st = new StringTools();
        List<String> codigo = new ArrayList<>();
        String aux[];
        String nl = System.lineSeparator();

        codigo.add("package GUIs;");
        codigo.add("import Controles." + nomeClasse + "Controle;\n"
                + "import Entidades." + nomeClasse + ";"
                + "import tools.CaixaDeFerramentas;\n"
                + "import tools.CentroDoMonitorMaior;\n"
                + "import tools.DateTextField;\n"
                + "import tools.ManipulaArquivo;\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JTextField;\n"
                + "import javax.swing.WindowConstants;\n"
                + "import java.awt.Dimension;\n"
                + "import java.awt.FlowLayout;\n"
                + "import java.awt.Font;\n"
                + "import java.awt.Point;\n"
                + "import java.awt.event.FocusEvent;\n"
                + "import java.awt.event.FocusListener;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.Arrays;\n"
                + "import java.util.Date;\n"
                + "import java.util.List;\n"
                + "import javax.swing.ImageIcon;\n"
                + "import javax.swing.JCheckBox;\n"
                + "import javax.swing.JComboBox;\n"
                + "import javax.swing.JToolBar;");

        codigo.add("/**\n"
                + " *\n"
                + " * @author Radames\n"
                + " */");

        codigo.add(nl);//pular linha

        codigo.add("class " + nomeClasse + "GUI extends JFrame {//");

        codigo.add(nl + "//atributos da entidade " + nomeClasse + nl);

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            codigo.add("    // " + aux[0] + ";" + aux[1] + ";" + aux[2] + nl);
        }
        codigo.add(nl);//pular linha
        codigo.add(nl);//pular linha
        codigo.add(nl);//pular linha

        codigo.add("//variáveis globais " + nl);

        codigo.add(" //carregar imagens dos icones\n"
                + "    ImageIcon iconeCreate = new ImageIcon(getClass().getResource(\"/icones/create.png\"));\n"
                + "    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource(\"/icones/retrieve.png\"));\n"
                + "    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource(\"/icones/update.png\"));\n"
                + "    ImageIcon iconeDelete = new ImageIcon(getClass().getResource(\"/icones/delete.png\"));\n"
                + "    ImageIcon iconeSave = new ImageIcon(getClass().getResource(\"/icones/save.png\"));\n"
                + "    ImageIcon iconeCancel = new ImageIcon(getClass().getResource(\"/icones/cancel.png\"));\n"
                + "    ImageIcon iconeListar = new ImageIcon(getClass().getResource(\"/icones/list.png\"));");

        codigo.add("   Container cp;\n"
                + "    JPanel pnNorte = new JPanel();\n"
                + "    JPanel pnCentro = new JPanel();\n"
                + "    JPanel pnSul = new JPanel();" + nl);

        codigo.add("//botoes e atribuição de ícones " + nl
                + "JButton btBuscar = new JButton(iconeRetrieve);\n"
                + "    JButton btAdicionar = new JButton(iconeCreate);\n"
                + "    JButton btSalvar = new JButton(iconeSave);\n"
                + "    JButton btAlterar = new JButton(iconeUpdate);\n"
                + "    JButton btExcluir = new JButton(iconeDelete);\n"
                + "    JButton btListar = new JButton(iconeListar);\n"
                + "    JButton btCancelar = new JButton(iconeCancel);");

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            String aMinusc = st.plMinus(aux[1].trim());
            String aMaiusc = st.plMaiusc(aux[1].trim());
            switch (aux[0]) {
                case "String":
                case "double":
                case "float":
                case "int":
                    codigo.add("         JLabel lb" + aMaiusc + " = new JLabel(\"" + aMaiusc + " \");" + nl);
                    codigo.add("         JTextField tf" + aMaiusc + " = new JTextField(" + aux[2] + ");" + nl);

                    break;
                case "Date":
                    codigo.add("         JLabel lb" + aMaiusc + " = new JLabel(\"" + aMaiusc + " \");" + nl);
                    codigo.add("         DateTextField tf" + aMaiusc + " = new DateTextField();" + nl);
                    break;
                case "boolean":
                    codigo.add("         JCheckBox checkBox" + aMaiusc + " = new JCheckBox(\"" + aMaiusc + "\");" + nl);

                    break;
                default:
                    codigo.add(aux[0] + " " + aux[1] + nl);
            }

        }

        codigo.add(" JLabel lbAviso = new JLabel(\"---\");");

        codigo.add("String acao;\n"
                + "    String caminho = \"" + nomeClasse + ".csv\";\n"
                + "    CaixaDeFerramentas cf = new CaixaDeFerramentas();\n"
                + "    JToolBar jToolbar = new JToolBar();\n"
                + "\n");

        codigo.add("// Instancia a classe de entidade " + nomeClasse + nl);
        codigo.add("" + nomeClasse + " " + st.plMinus(nomeClasse) + " = new " + nomeClasse + "();" + nl);

        codigo.add("// Instancia a classe de controle " + nomeClasse + "Controle.java" + nl);
        codigo.add("" + nomeClasse + "Controle " + st.plMinus(nomeClasse) + "Controle = new " + nomeClasse + "Controle();" + nl);

        codigo.add("String acao;\n"
                + "    String caminho = \"" + nomeClasse + ".csv\";\n"
                + "    CaixaDeFerramentas cf = new CaixaDeFerramentas();\n"
                + "    JToolBar jToolbar = new JToolBar();");

        codigo.add("ManipulaArquivo manipulaArquivo = new ManipulaArquivo();" + nl + nl);

        //procurar se tem combobox e declarar os lists para cada um que encontrar
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (aux[2].equals("ComboBox")) {
                codigo.add("    //list para os itens do combobox\n" + nl
                        + "    List<String> lista" + st.plMaiusc(aux[1]) + " = new ArrayList<>();" + nl);
                codigo.add("String caminho" + st.plMaiusc(aux[1]) + "=" + "\"" + st.plMaiusc(aux[1]) + ".csv\";" + nl);
            }
        }

        //construtor
        codigo.add("public " + nomeClasse + "GUI() {");

        //procurar se tem combobox e declarar os lists para cada um que encontrar
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (aux[2].equals("ComboBox")) {
                codigo.add("    //de onde vem os dados combobox\n" + nl
                        + "    lista" + st.plMaiusc(aux[1])
                        + " = manipulaArquivo.abrirArquivo(" + "caminho" + st.plMaiusc(aux[1])
                        + ");////busca no arquivo csv os dados para o combobox." + nl);

            }
        }

        codigo.add(" if (manipulaArquivo.existeOArquivo(caminho)) {\n"
                + st.plMinus(nomeClasse) + "Controle.preencherListaCarro(manipulaArquivo.abrirArquivo(caminho));\n"
                + "        } else {\n"
                + "            manipulaArquivo.criarArquivoVazio(caminho);\n"
                + "        }");

        codigo.add("//componentes visuais\n"
                + "        setTitle(\"CRUD " + nomeClasse + "\");\n"
                + "        cp = getContentPane();\n"
                + "\n"
                + "        cp.setLayout(new BorderLayout());\n"
                + "\n"
                + "        cp.add(pnNorte, BorderLayout.NORTH);\n"
                + "        cp.add(pnCentro, BorderLayout.CENTER);\n"
                + "        cp.add(pnSul, BorderLayout.SOUTH);\n"
                + "\n"
                + "        pnNorte.setBackground(Color.LIGHT_GRAY);\n"
                + "        pnCentro.setBackground(Color.white);\n"
                + "        pnSul.setBackground(Color.DARK_GRAY);\n"
                + "\n"
                + "        pnNorte.setLayout(new FlowLayout((int) LEFT_ALIGNMENT));\n"
                + "        pnNorte.add(jToolbar);\n"
                + "        jToolbar.add(lbPlaca);\n"
                + "        jToolbar.add(tfPlaca);\n"
                + "        jToolbar.add(btBuscar);\n"
                + "        jToolbar.add(btAdicionar);\n"
                + "        jToolbar.add(btAlterar);\n"
                + "        jToolbar.add(btExcluir);\n"
                + "        jToolbar.add(btListar);\n"
                + "        jToolbar.add(btSalvar);\n"
                + "        jToolbar.add(btCancelar);\n"
                + "\n"
                + "        btBuscar.setToolTipText(\"Buscar\");\n"
                + "        btAdicionar.setToolTipText(\"Adicionar novo registro\");\n"
                + "        btAlterar.setToolTipText(\"Alterar um registro\");\n"
                + "        btExcluir.setToolTipText(\"Excluir um registro\");\n"
                + "        btListar.setToolTipText(\"Listagem\");\n"
                + "        btSalvar.setToolTipText(\"Salvar dados do registro\");\n"
                + "        btCancelar.setToolTipText(\"Cancelar edição (sair sem salvar)\");");

        //calcular quantos atributos serão adicionados no gridCentral
        int contAtributos = atributo.size() - 1; // desconta 1, por causa da chave primária que fica no painel norte
        codigo.add("pnCentro.setLayout(new GridLayout(" + contAtributos + ", 2));");
        //adicionar os atributos visuais no painel Centro
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            String aMinusc = st.plMinus(aux[1].trim());
            String aMaiusc = st.plMaiusc(aux[1].trim());
            switch (aux[0]) {
                case "String":
                case "int":
                case "double":
                case "float":
                case "Date":
                    codigo.add("        pnCentro.add(lb" + aMaiusc + ");" + nl
                            + "        pnCentro.add(tf" + aMaiusc + ");" + nl);
                    break;
                case "boolean":
                    codigo.add("        pnCentro.add(new JLabel(\"\"));" + nl
                            + "        pnCentro.add(checkBox" + aMaiusc + ");" + nl);
                    break;
                default:
                    codigo.add("        pnCentro.add(tipoDesconhecido" + st.plMaiusc(aux[1]) + ");" + nl);
            }

        }

        codigo.add("  pnSul.add(lbAviso);");
        codigo.add("");

        codigo.add(" //status inicial dos botoes\n"
                + "        btAdicionar.setVisible(false);\n"
                + "        btSalvar.setVisible(false);\n"
                + "        btCancelar.setVisible(false);\n"
                + "        btAlterar.setVisible(false);\n"
                + "        btExcluir.setVisible(false);\n"
                + "        btListar.setVisible(true);");

        codigo.add(" //status inicial dos atributos\n");
        aux = atributo.get(0).split(";");
        String aMaiusc = st.plMaiusc(aux[1].trim());
        codigo.add("        tf" + aMaiusc + ".setEditable(true);" + nl);

        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            String aMinusc = st.plMinus(aux[1].trim());
            aMaiusc = st.plMaiusc(aux[1].trim());
            switch (aux[0]) {
                case "String":
                case "int":
                case "double":
                case "float":
                case "Date":
                    codigo.add("        tf" + aMaiusc + ".setEditable(false);" + nl);

                    break;
                case "boolean":
                    codigo.add("        checkBox" + aMaiusc + ".setEnabled(false);" + nl);
                    break;
                default:
                    codigo.add("        tipoDesconhecido" + st.plMaiusc(aux[1]) + ".setEditable(false);" + nl);
            }

        }
        codigo.add("//fonte e cor do aviso");
        codigo.add("lbAviso.setOpaque(true);\n"
                + "        lbAviso.setBackground(Color.BLACK);\n"
                + "        // Definir a cor da fonte como branca\n"
                + "        lbAviso.setForeground(Color.WHITE);\n"
                + "\n"
                + "        // Definir a fonte em negrito\n"
                + "        Font fonte = lbAviso.getFont();\n"
                + "        Font fonteNegrito = new Font(fonte.getFontName(), Font.BOLD, fonte.getSize());\n"
                + "        lbAviso.setFont(fonteNegrito);");

        aux = atributo.get(0).split(";");
        aMaiusc = st.plMaiusc(aux[1].trim());
        codigo.add("//listeners\n"
                + "        tf" + aMaiusc + ".addFocusListener(new FocusListener() {\n"
                + "            @Override\n"
                + "            public void focusGained(FocusEvent fe) {\n"
                + "                lbAviso.setText(\"Digite a " + aMaiusc + " de um carro\");\n"
                + "                tf" + aMaiusc + ".setBackground(Color.green);\n"
                + "                btAdicionar.setVisible(false);\n"
                + "                btAlterar.setVisible(false);\n"
                + "\n"
                + "                btExcluir.setVisible(false);\n"
                + "                if (!btSalvar.isVisible()) {\n"
                + "                    btListar.setVisible(true);\n"
                + "                }\n"
                + "            }\n"
                + "\n"
                + "            @Override\n"
                + "            public void focusLost(FocusEvent fe) {\n"
                + "                tf" + aMaiusc + ".setBackground(Color.white);\n"
                + "            }\n"
                + "        });");

        codigo.add(nl + "//botão buscar" + nl);
        codigo.add("        btBuscar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {");
        codigo.add("if (tf" + aMaiusc + ".getText().isEmpty()) {\n"
                + "                    tf" + aMaiusc + ".requestFocus();\n"
                + "                } else {");

        codigo.add(" " + st.plMinus(nomeClasse) + " = " + st.plMinus(nomeClasse) + "Controle.buscar(tf" + aMaiusc + ".getText());\n"
                + "                    if (" + st.plMinus(nomeClasse) + " == null) {//não achou na lista");
        codigo.add(" lbAviso.setText(\"Não achou na lista\");\n"
                + "                        btAdicionar.setVisible(true);\n"
                + "                        btAlterar.setVisible(false);\n"
                + "                        btExcluir.setVisible(false);");

        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            String aMinusc = st.plMinus(aux[1].trim());
            aMaiusc = st.plMaiusc(aux[1].trim());
            switch (aux[0]) {
                case "String":
                case "int":
                case "double":
                case "float":
                case "Date":
                    codigo.add("        tf" + aMaiusc + ".setText(\"\");" + nl);

                    break;
                case "boolean":
                    codigo.add("        checkBox" + aMaiusc + ".setSelected(false);" + nl);
                    break;
                default:
                    codigo.add("        tipoDesconhecido" + st.plMaiusc(aux[1]) + ".setEditable(false);" + nl);
            }
        }

        codigo.add("} else {//encontra na lista");

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            String aMinusc = st.plMinus(aux[1].trim());
            aMaiusc = st.plMaiusc(aux[1].trim());
            String oTipo = "String.valueOf(" + st.plMinus(nomeClasse) + ".get" + aMaiusc + "()));";
            switch (aux[0]) {
                case "String":
                      codigo.add("        tf" + aMaiusc + ".setText(" + st.plMinus(nomeClasse) + ".get" + aMaiusc + "());"+nl);
                    break;
                case "int":
                case "double":
                case "float":
                case "Date":
                   
                     codigo.add("        tf" + aMaiusc + ".setText("+oTipo);
                    break;
                case "boolean":
                    codigo.add("        checkBox" + aMaiusc + ".setSelected(false);" + nl);
                    break;
                default:
                    codigo.add("        tipoDesconhecido" + st.plMaiusc(aux[1]) + ".setEditable(false);" + nl);
            }
        }

        codigo.add("");
        codigo.add("");

        codigo.add("} //fim do construtor");
        codigo.add(nl + nl + "}//fim da classe GUI");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        String caminhoClasseEntidade = caminhoProjetoDestino + "/src/GUIs/" + nomeClasse + "GUI.java";
        //System.out.println("cce " + caminhoClasseEntidade);
        // System.exit(0);
        manipulaArquivo.salvarArquivo(caminhoClasseEntidade, codigo); //vai salvar a classe de entidade dentro da pasta entidades, no projeto destino.

    }

}
