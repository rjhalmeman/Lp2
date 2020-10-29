package Controle;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GerarClasseMain {

    public GerarClasseMain(String nomeDaClasse, List<String> atributo, String caminho) {
        StringTools st = new StringTools();
        List<String> cg = new ArrayList();//código gerado
        cg.add("package Main;\n"
                
                + "import GUIs."+nomeDaClasse+"GUI;\n"
                + "/**\n"
                + " *\n"
                + " * @author radames\n"
                + " */\n"
                + "public class Main {\n"
                + "\n"
                + "    /**\n"
                + "     * @param args the command line arguments\n"
                + "     */\n"
                + "    public static void main(String[] args) {\n"
                + "        "+nomeDaClasse+"GUI guiDoGerador = new "+nomeDaClasse+"GUI();\n"
                + "    }\n"
                + "\n"
                + "}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho + "/src/Main/Main.java", cg);

    }

}
