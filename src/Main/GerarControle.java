package Main;

import java.util.ArrayList;
import java.util.List;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GerarControle {
    
    public GerarControle(List<String> atributo, String nomeClasse) {
        StringTools st = new StringTools();
        List<String> codigo = new ArrayList<>();
        String auxiliaSplit[];
        
        codigo.add("package Main;");
        codigo.add("import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import tools.CaixaDeFerramentas;\n"
                + "import java.text.SimpleDateFormat;\n"
                + "import tools.ManipulaArquivo;");
        codigo.add("/**\n"
                + " *\n"
                + " * @author Radames\n"
                + " */");
        
        codigo.add("public class " + nomeClasse + "Controle {");
        
        codigo.add(" private List<" + nomeClasse + "> lista = new ArrayList<>();");
        codigo.add("SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");");
        codigo.add(" public " + nomeClasse + "Controle() {\n"
                + "    }");
        
        codigo.add(" public void adicionar(" + nomeClasse + " " + st.plMinus(nomeClasse) + ") {\n"
                + "        lista.add(" + st.plMinus(nomeClasse) + ");\n"
                + "    }");
        
        codigo.add("public List<" + nomeClasse + "> listar() {\n"
                + "        return lista;\n"
                + "    }");
        
        auxiliaSplit = atributo.get(0).split(";");
        
        codigo.add("public " + nomeClasse + " buscar(" + auxiliaSplit[0] + " " + auxiliaSplit[1] + ") {\n"
                + "        for (int i = 0; i < lista.size(); i++) {\n");
        if (auxiliaSplit[0].equals("String")) {
            codigo.add("if (lista.get(i).get" + st.plMaiusc(auxiliaSplit[1]) + "().equals(" + auxiliaSplit[1] + ")) {");
        } else {
            codigo.add("if (lista.get(i).get" + st.plMaiusc(auxiliaSplit[1]) + "()==" + st.plMinus(auxiliaSplit[1]) + ") {");
        }
        codigo.add("                return lista.get(i);\n"
                + "            }\n"
                + "        }\n"
                + "        return null;\n"
                + "    }");
        
        codigo.add("public void alterar(" + nomeClasse + " " + st.plMinus(nomeClasse) + ", " + nomeClasse + " " + st.plMinus(nomeClasse) + "Antigo) {\n"
                + "        lista.set(lista.indexOf(" + st.plMinus(nomeClasse) + "Antigo), " + st.plMinus(nomeClasse) + ");\n"
                + "\n"
                + "    }");
        
        codigo.add(" public void excluir(" + nomeClasse + " " + st.plMinus(nomeClasse) + ") {\n"
                + "        lista.remove(" + st.plMinus(nomeClasse) + ");\n"
                + "    }");
        
        codigo.add(" public void gravarLista(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        List<String> listaDeString = new ArrayList<>();\n"
                + "        for (" + nomeClasse + " " + st.plMinus(nomeClasse) + " : lista) {\n"
                + "            listaDeString.add(" + st.plMinus(nomeClasse) + ".toString() + System.lineSeparator());\n"
                + "        }\n"
                + "        manipulaArquivo.salvarArquivo(caminho, listaDeString);\n"
                + "    }\n"
                + "");
        
        codigo.add(" public void carregarDados(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        if (!manipulaArquivo.existeOArquivo(caminho)) {\n"
                + "            manipulaArquivo.criarArquivoVazio(caminho);\n"
                + "        }\n"
                + "\n"
                + "        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);\n"
                + "        //converter de CSV para Carro\n"
                + "        " + nomeClasse + " " + st.plMinus(nomeClasse) + ";\n"
                + "        CaixaDeFerramentas cdf = new CaixaDeFerramentas();\n"
                + "        for (String string : listaDeString) {\n"
                + "            String aux[] = string.split(\";\");\n");
        
        codigo.add("" + st.plMinus(nomeClasse) + " = new " + nomeClasse + "(");
        String ss = "";
        for (int i = 0; i < atributo.size(); i++) {            
            auxiliaSplit = atributo.get(i).split(";");
            if (auxiliaSplit[0].equals("String")) {
                ss = ss+ "aux["+i+"],";
            } else if (auxiliaSplit[0].equals("int")) {
                ss = ss+ "Integer.valueOf(aux["+i+"]),";
            } else if (auxiliaSplit[0].equals("double")) {
                ss = ss+ "Double.valueOf(aux["+i+"]),";
            } else if (auxiliaSplit[0].equals("Date")) {
                ss = ss+ "sdf.format(aux["+i+"]),";
            }
        }
        ss = ss.substring(0,ss.length()-1);
        codigo.add(ss);
        
        codigo.add(" );           \n"
                + "            lista.add(" + st.plMinus(nomeClasse) + ");\n"
                + "        }\n"
                + "    }");

        ///////////////////////////////////////
//        codigo.add("public " + nomeClasse + "() {\n"
//                + "    }");
//
//        String parametros = "";
//        for (int i = 0; i < atributo.size(); i++) {
//            aux = atributo.get(i).split(";");
//            parametros += (aux[0] + " " + aux[1] + ",");
//        }
//        parametros = parametros.substring(0, parametros.length() - 1);
//        codigo.add("public " + nomeClasse + "(" + parametros + "){");
//        for (int i = 0; i < atributo.size(); i++) {
//            aux = atributo.get(i).split(";");
//            codigo.add("this." + aux[1] + "=" + aux[1] + ";");
//        }
//        codigo.add("}");
//        codigo.add("//gets");
//
//        for (int i = 0; i < atributo.size(); i++) {
//            aux = atributo.get(i).split(";");
//            codigo.add("public " + aux[0] + " get" + st.plMaiusc(aux[1]) + "() {");
//            codigo.add("return " + aux[1] + ";");
//            codigo.add("}");
//        }
//
//        codigo.add("//sets");
//
//        for (int i = 0; i < atributo.size(); i++) {
//            aux = atributo.get(i).split(";");
//            codigo.add("public void set" + st.plMaiusc(aux[1]) + "( " + aux[0] + " " + aux[1] + ") {");
//            codigo.add("this." + aux[1] + "=" + aux[1] + ";");
//            codigo.add("}");
//        }
//        //toString
//        codigo.add(" @Override");
//        boolean umDate = false;
//
//        parametros = "";
//        for (int i = 0; i < atributo.size(); i++) {
//            aux = atributo.get(i).split(";");
//            if (aux[0].equals("Date")) {
//                parametros += ("sdf.format(" + aux[1] + ")+\";\"+");
//                umDate = true;
//            } else {
//                parametros += (aux[1] + "+\";\"+");
//            }
//
//        }
//        parametros = parametros.substring(0, parametros.length() - 5);
//        codigo.add("public String toString() {");
//        if (umDate) {
//            codigo.add("SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");");
//        }
//        codigo.add("return " + parametros + ";");
//        codigo.add("}");
        codigo.add("}//fim da classe");

//        System.out.println("parametros "+parametros);
//        System.exit(0);
        //mostra o código gerado
        for (String c : codigo) {
            System.out.println(c);
        }

        //codigo.add("");
    }
    
}
