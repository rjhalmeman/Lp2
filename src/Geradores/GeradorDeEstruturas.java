package Geradores;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import tools.CopiarArquivos;

/**
 *
 * @author radames
 */
public class GeradorDeEstruturas {

    public GeradorDeEstruturas(String caminhoProjetoDestino) {
        String src = caminhoProjetoDestino; //ajustar isso

        List<String> listaString = new ArrayList<>();
        listaString.clear();
        listaString.add("/src/" + "Entidades");
        listaString.add("/src/" + "Controles");
        listaString.add("/src/" + "GUIs");
        listaString.add("/src/" + "tools");
        listaString.add("/src/" + "icones");
        listaString.add("/src/" + "Main");

        for (String pacote : listaString) {
            File pac = new File(src + pacote);
            if (!pac.exists()) {
                new File(src + pacote).mkdir();//cria as pastas
            }
        }

        //copiar pacote de icones
        File listaIcones = new File("src/icones");

        if (listaIcones.exists()) {
            File[] arqs = listaIcones.listFiles();
            CopiarArquivos copiarArquivos = new CopiarArquivos();
            for (int i = 0; i < arqs.length; i++) {
                //  System.out.print(" A origem " + arqs[i].getAbsolutePath() + "  ---  ");
                //  System.out.println("destino " + caminhoProjetoDestino + "/src" + "/icones/" + arqs[i].getName());
                copiarArquivos.copiar(arqs[i].getAbsolutePath(),
                        caminhoProjetoDestino + "/src" + "/icones/" + arqs[i].getName());
            }
        }

        //copiar pacote de ferramentas
        File listaFerramentas = new File("src/tools");
        if (listaFerramentas.exists()) {
            File[] arqs = listaFerramentas.listFiles();
            CopiarArquivos copiarArquivos = new CopiarArquivos();
            for (int i = 0; i < arqs.length; i++) {
                copiarArquivos.copiar(arqs[i].getAbsolutePath(),
                        caminhoProjetoDestino + "/src" + "/tools/" + arqs[i].getName());
            }

        }
    }

}
