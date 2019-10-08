package Main;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

public class ProdutoControle {

    private List<Produto> lista = new ArrayList<>();

    public ProdutoControle() {
    }

    public void limparLista() {
        lista.clear();
    }

    public void adicionar(Produto produto) {
        lista.add(produto);
    }

    public List<Produto> listar() {
        return lista;
    }

    public Produto buscar(long id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId()==(id)) {
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

}
