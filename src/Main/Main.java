package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String projetoDestino = "/home/radames/NetBeansProjects/Alvo";

        String nomeDaClasse = "Pessoa";
        List<String> atributo = new ArrayList<>();
        List<String> codigo = new ArrayList<>();
        atributo.add("String;id");
        atributo.add("String;nome");
        atributo.add("double;altura");
        atributo.add("String;esporte");
        atributo.add("double;peso");
        atributo.add("Date;dataNascimento");

        GerarClasseMain gerarClasseMain = new GerarClasseMain(nomeDaClasse, projetoDestino);
        GerarClasseEntidade gerarClasseEntidade = new GerarClasseEntidade(nomeDaClasse, atributo, codigo, projetoDestino);
        codigo.clear();
        GerarClasseControle gerarClasseControle = new GerarClasseControle(nomeDaClasse, atributo, codigo, projetoDestino);
        codigo.clear();
        GerarClasseGUI gerarClasseGUI = new GerarClasseGUI(nomeDaClasse, atributo, codigo, projetoDestino);

    }

}
