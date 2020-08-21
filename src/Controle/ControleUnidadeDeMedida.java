package Controle;

import Entidade.UnidadeDeMedida;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class ControleUnidadeDeMedida {

    private List<UnidadeDeMedida> lista = new ArrayList<>();

    public ControleUnidadeDeMedida() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(UnidadeDeMedida unidadeDeMedida) {
        lista.add(unidadeDeMedida);
    }

    public List<UnidadeDeMedida> listar() {
        return lista;
    }

    public UnidadeDeMedida buscar(String cpf) {
        if (cpf != null) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId_UM().equals(cpf)) {
                    return lista.get(i);
                }
            }
        }  
        return null;

    }

    public void alterar(UnidadeDeMedida unidadeDeMedida, UnidadeDeMedida unidadeDeMedidaAntigo) {
        lista.set(lista.indexOf(unidadeDeMedidaAntigo), unidadeDeMedida);

    }

    public void excluir(UnidadeDeMedida unidadeDeMedida) {
        lista.remove(unidadeDeMedida);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (UnidadeDeMedida unidadeDeMedida : lista) {
            listaDeString.add(unidadeDeMedida.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para UnidadeDeMedida
        UnidadeDeMedida unidadeDeMedida;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            unidadeDeMedida = new UnidadeDeMedida(aux[0], aux[1]);
            lista.add(unidadeDeMedida);
        }
    }

}
