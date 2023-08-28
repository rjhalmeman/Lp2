package Main;

import java.util.ArrayList;
import java.util.List;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StringTools st = new StringTools();
        List<String> atributo = new ArrayList<>();
        

        atributo.add("String;cpf;20");
        atributo.add("String;nome;30");
        atributo.add("Date;dataNascimento;10");
        atributo.add("double;peso;5");
        atributo.add("double;altura;5");
   

        String nomeClasse = "Pessoa";
        
      //  GerarEntidade gerarEntidade = new GerarEntidade(atributo,nomeClasse);
        GerarControle gerarControle = new GerarControle(atributo,nomeClasse);
        

    }

}
