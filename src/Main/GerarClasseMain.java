package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.Tools;

/**
 *
 * @author a1903853
 */
public class GerarClasseMain {

    String nomeDaClasse;
    List<String> codigo = new ArrayList<>();
    Tools st = new Tools();

    public GerarClasseMain(String nomeDaClasse, String projetoDestino) {
        this.nomeDaClasse = nomeDaClasse;

        codigo.add("package Main;\n"
                + "public class Main {");

        codigo.add("public static void main(String[] args) {");
        codigo.add("" + nomeDaClasse + "GUI " + st.primeiraLetraMinuscula(nomeDaClasse) + "Gui = new " + nomeDaClasse + "GUI();");
        codigo.add("}");
        codigo.add("}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(projetoDestino + "/src/Main/Main.java", codigo);//adicionou a classe attlea no pacote cobaia
        //manipulaArquivo.salvarArquivo("/home/todos/alunos/cm/a1903853/NetBeansProjects/Cobaia/src/Main/Main.java", codigo);//adicionou a classe attlea no pacote cobaia

    }

}
