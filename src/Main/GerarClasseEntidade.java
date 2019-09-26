package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.Tools;

/**
 *
 * @author a1903853
 */
public class GerarClasseEntidade {

    String nomeDaClasse;
    List<String> atributo;
    List<String> codigo;
    Tools st = new Tools();

    public GerarClasseEntidade(String nomeDaClasse, List<String> atributo, List<String> codigo, String projetoDestino) {
        this.nomeDaClasse = nomeDaClasse;
        this.atributo = atributo;
        this.codigo = codigo;

        //verificar se tem Date, para criar import
        String stringImports = "";
        for (int i = 0; i < atributo.size(); i++) {
            if (atributo.get(i).contains("Date")) {
                stringImports = "import java.util.Date;\n\n";
            }
        }
        
        codigo.add("package Main;\n"
                + stringImports
                + "public class " + nomeDaClasse + " {");
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("private " + aux[0] + " " + aux[1] + ";");
        }

        codigo.add("\n"
                + " public " + nomeDaClasse + "() {\n"
                + "    }");

        
        codigo.add("\n"
                + " public " + nomeDaClasse + "( "
                + "    ");
        String w = "";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            w = w + aux[0] + " " + aux[1] + ",";

        }
        w = w.substring(0, w.length() - 1);
        codigo.add(w);
        codigo.add(") {\n");
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("this." + aux[1] + "=" + aux[1] + ";");
        }
        codigo.add("}");

        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("public " + aux[0] + " get" + st.primeiraLetraMaiscula(aux[1]) + "(){\nreturn " + aux[1] + ";\n }");
            codigo.add("public void " + " set" + st.primeiraLetraMaiscula(aux[1]) + "(" + aux[0] + "  " + aux[1] + ") {\n this." + aux[1] + " = " + aux[1] + ";\n }");
        }
        String x = "";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            x += aux[1] + "+\";\"+";

        }
        x = x.substring(0,x.length()-5);
        codigo.add("@Override\n"
                + "    public String toString() {");
        codigo.add("return " + x +";");

        codigo.add("}\n}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(projetoDestino + "/src/Main/" + nomeDaClasse + ".java", codigo);//adicionou a classe attlea no pacote cobaia
//        manipulaArquivo.salvarArquivo("/home/todos/alunos/cm/a1903853/NetBeansProjects/Cobaia/src/Main/" + nomeDaClasse + ".java", codigo);//adicionou a classe attlea no pacote cobaia

    }

}
