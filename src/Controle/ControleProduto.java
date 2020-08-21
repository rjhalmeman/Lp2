package Controle;

import Entidade.Produto;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class ControleProduto {

    private List<Produto> lista = new ArrayList<>();

    public ControleProduto() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Produto produto) {
        lista.add(produto);
    }

    public List<Produto> listar() {
        return lista;
    }

    public Produto buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdProduto() == id) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(Produto produto, Produto produtoAntigo) {
        lista.set(lista.indexOf(produtoAntigo), produto);

    }

    public void excluir(Produto produto) {
        lista.remove(produto);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Produto produto : lista) {
            listaDeString.add(produto.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Produto
        Produto produto;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            produto = new Produto(Integer.valueOf(aux[0]), aux[1], Integer.valueOf(aux[2]), aux[3]);
            lista.add(produto);
        }
    }

}
