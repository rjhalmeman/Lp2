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
public class GerarClasseDeControle {

    public GerarClasseDeControle(String nomeDaClasse, List<String> atributo, String caminho) {
        StringTools st = new StringTools();
        String[] aux;
        List<String> cg = new ArrayList();//código gerado
        cg.add("package Controles;");

        cg.add("import Entidades." + nomeDaClasse + ";\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import tools.CaixaDeFerramentas;\n"
                + "import tools.ManipulaArquivo;");

        cg.add("/**\n"
                + " *\n"
                + " * @author radames\n" + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + " */");
        cg.add("public class " + nomeDaClasse + "Controle {\n");
        cg.add(" private List<" + nomeDaClasse + "> lista = new ArrayList<>();");
        cg.add(" public " + nomeDaClasse + "Controle() {\n"
                + "    }");

        cg.add("public void limparLista() {\n"
                + "        lista.clear();//zera a lista\n"
                + "    }");
        cg.add(" public void adicionar(" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ") {\n"
                + "        lista.add(" + st.plMinus(nomeDaClasse) + ");\n"
                + "    }");
        cg.add("public List<" + nomeDaClasse + "> listar() {\n"
                + "        return lista;\n"
                + "    }");

        aux = atributo.get(0).split(";");
        cg.add(" public " + nomeDaClasse + " buscar(" + aux[0] + " " + aux[1] + ") {\n"
                + "        for (int i = 0; i < lista.size(); i++) {\n");
        String s = "";
        switch (aux[0]) {
            case "int":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "short":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "double":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "String":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "().equals(" + aux[1] + ")) {\n";
                break;
            default:
                throw new AssertionError();
        }
        cg.add(s);

        cg.add("                return lista.get(i);\n"
                + "            }\n"
                + "        }\n"
                + "        return null;\n"
                + "    }");
        cg.add("public void alterar(" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ", " + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + "Antigo) {\n"
                + "        lista.set(lista.indexOf(" + st.plMinus(nomeDaClasse) + "Antigo), " + st.plMinus(nomeDaClasse) + ");\n"
                + "\n"
                + "    }");
        cg.add("public void excluir(" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ") {\n"
                + "        lista.remove(" + st.plMinus(nomeDaClasse) + ");\n"
                + "    }");
        cg.add(" public void gravarLista(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        List<String> listaDeString = new ArrayList<>();\n"
                + "        for (" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + " : lista) {\n"
                + "            listaDeString.add(" + st.plMinus(nomeDaClasse) + ".toString()+System.lineSeparator());\n"
                + "        }\n"
                + "        manipulaArquivo.salvarArquivo(caminho, listaDeString);\n"
                + "    }");

        s = "";
        for (int i = 0; i < atributo.size(); i++) {

            aux = atributo.get(i).split(";");
            //   aux[i]=aux[i].trim();            
            if (aux[0].equals("String")) {
                s = s + "aux[" + i + "], ";
            } else if (aux[0].equals("int")) {
                s = s + "Integer.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("double")) {
                s = s + "Double.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("Date")) {
                s = s + "cdf.converteDeStringParaDate(aux[" + i + "])),";
            }

        }
        s = s.substring(0, s.length() - 2);
        cg.add("public void carregarDados(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        if (!manipulaArquivo.existeOArquivo(caminho)) {\n"
                + "            manipulaArquivo.criarArquivoVazio(caminho);\n"
                + "        }\n"
                + "\n"
                + "        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);\n"
                + "        //converter de CSV para " + nomeDaClasse + "\n"
                + "        " + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ";\n"
                + "CaixaDeFerramentas cdf = new CaixaDeFerramentas(); \n"
                + "        for (String string : listaDeString) {\n"
                + "            String aux[] = string.split(\";\");\n"
                + "            " + st.plMinus(nomeDaClasse) + " = new " + nomeDaClasse + "(" + s + ");\n"
                + "            lista.add(" + st.plMinus(nomeDaClasse) + ");\n"
                + "        }\n"
                + "    }");
        cg.add("");
        cg.add("");

        cg.add("} //fim da classe");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho + "/src/Controles/" + nomeDaClasse + "Controle.java", cg);

    }

}
