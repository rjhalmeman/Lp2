package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {

    private List<Produto> listaProduto = new ArrayList<>();

    public void inserir(Produto produto) {
        listaProduto.add(produto);
    }

    public Produto buscar(int idProduto) {
        for (int i = 0; i < listaProduto.size(); i++) {
            if (listaProduto.get(i).getIdProduto() == idProduto) {
                return listaProduto.get(i);
            }
        }
        return null;
    }

    public void atualizar(Produto original, Produto modificado) {
        int qualOIndiceDoProduto = listaProduto.indexOf(original);
        listaProduto.set(qualOIndiceDoProduto, modificado);
    }

    public void excluir(Produto produto) {
        listaProduto.remove(produto);
    }

    public List<Produto> listar() {
        return listaProduto;
    }

    public List<String> listaDeProdutosString() {
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < listaProduto.size(); i++) {
            ls.add(listaProduto.get(i).toString() + System.lineSeparator());
        }
        return ls;
    }

    public void preencherListaProduto(List<String> listaString) {
        for (String string : listaString) {
            String aux[] = string.split(";");
            Produto produto = new Produto();
            produto.setIdProduto(Integer.valueOf(aux[0]));
            produto.setNomeProduto(aux[1]);
            produto.setPrecoProduto(Double.valueOf(aux[2]));
            produto.setUnidadeDeMedida(aux[3]);
            listaProduto.add(produto);           
        }
 
    }

}
