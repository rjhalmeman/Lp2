package Geradores;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GerarClasseMain {

    public GerarClasseMain(List<String> atributo, String nomeClasse, String caminhoProjetoDestino) {
        StringTools st = new StringTools();
        List<String> codigo = new ArrayList<>();
        String aux[];

       

        codigo.add("package Main;\n"
                + "\n"
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
                + "        new PessoaGUI();"
                + "    }\n"
                + "    \n"
                + "}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        String caminhoClasseEntidade = caminhoProjetoDestino + "/src/Main/Main.java";

        manipulaArquivo.salvarArquivo(caminhoClasseEntidade, codigo); //vai salvar a classe Main dentro da pasta Main, no projeto destino.

    }
}
