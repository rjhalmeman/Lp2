package Main;

import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author presa
 */
public class GerarClasseControle {

    public static String classeComMaius(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
    }
    String nomeDaClasse;
    List<String> atributo;
    List<String> codigo;

    public GerarClasseControle(String nomeDaClasse, List<String> atributo, List<String> codigo, String projetoDestino) {
        this.nomeDaClasse = nomeDaClasse;
        this.atributo = atributo;
        this.codigo = codigo;

        String nomeDaClasseMinuscula = nomeDaClasse.toLowerCase();

        //verificar se tem Date, para criar import
        String stringImports = "";
        for (int i = 0; i < atributo.size(); i++) {
            if (atributo.get(i).contains("Date")) {
                stringImports = "import java.util.Date;\n\n";
            }
        }

        codigo.add("package Main;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;" + "\n\n\n"
                + stringImports);
        codigo.add("public class " + nomeDaClasse + "Controle " + "{"
                + "");

        codigo.add("    private List<" + nomeDaClasse + "> " + "lista" + "=" + " new ArrayList<>();");
        codigo.add("    public " + nomeDaClasse + "Controle" + "(){ \n"
                + "}");
        codigo.add("    public void limparLista" + "(){ \n"
                + "        lista.clear();"
                + "}"
                + "\n");
        codigo.add("    public void adicionar" + "(" + nomeDaClasse + " " + nomeDaClasseMinuscula + "){ \n"
                + "        lista.add(" + nomeDaClasseMinuscula + ");"
                + "}");

        codigo.add("    public List <" + nomeDaClasse + ">" + " listar()" + "{"
                + "        return lista;"
                + "}"
                + "\n");

        String aux[] = atributo.get(0).split(";");
        if (aux[0].equals("int") || aux[0].equals("double") || aux[0].equals("float")) {
            codigo.add("public " + nomeDaClasse + " " + "buscar(" + aux[0] + " " + aux[1] + ") " + "{ \n"
                    + "        for (int i = 0; i < lista.size(); i++) {\n"
                    + "              if (lista.get(i).get" + classeComMaius(aux[1]) + "() == " + aux[1] + ")" + "{ \n"
                    + "                return lista.get(i);"
                    + "\n"
                    + "            }"
                    + "        }"
                    + "        return null;"
                    + "\n\n");
        } else {
            codigo.add("public " + nomeDaClasse + " " + "buscar(" + aux[0] + " " + aux[1] + ") " + "{ \n"
                    + "        for (int i = 0; i < lista.size(); i++) {\n"
                    + "if (lista.get(i).get" + classeComMaius(aux[1]) + "()" + ".equals(" + aux[1] + "))" + "{ \n"
                    + "return lista.get(i);"
                    + "\n"
                    + "            }"
                    + "        }"
                    + "        return null;"
                    + "\n\n");

        }
        codigo.add("}");

        codigo.add("public void alterar(" + nomeDaClasse + " " + nomeDaClasseMinuscula + ", " + nomeDaClasse + " " + nomeDaClasseMinuscula + "Antigo" + ") " + "{ \n"
                + "lista.set(lista.indexOf(" + nomeDaClasseMinuscula + "Antigo)" + ", " + nomeDaClasseMinuscula + ");"
                + "\n\n"
                + "}");

        codigo.add("public void excluir(" + nomeDaClasse + " " + nomeDaClasseMinuscula + ") " + "{ \n"
                + "        lista.remove(" + nomeDaClasseMinuscula + ");"
                + "    }"
                + "\n\n\n"
                + "}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(projetoDestino + "/src/Main/" + nomeDaClasse + "Controle.java", codigo);

    }

}
