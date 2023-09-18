package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GerarControle {
    
    public GerarControle(List<String> atributo, String nomeClasse, String caminhoDoProjetoDestino) {
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

       
        codigo.add("}//fim da classe");

//        for (String c : codigo) {
//            System.out.println(c);
//        }
        
        ManipulaArquivo destino = new ManipulaArquivo();
        if (destino.existeOArquivo(caminhoDoProjetoDestino)){
            destino.salvarArquivo(caminhoDoProjetoDestino+"/src/Controle/Controle.java", codigo);
        }

        //codigo.add("");
    }
    
}
