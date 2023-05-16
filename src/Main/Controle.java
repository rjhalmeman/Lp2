package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {
    private List<Produto> listaProduto = new ArrayList<>();
    
    
    public void inserir(Produto produto){
        listaProduto.add(produto);        
    }
    
    public Produto buscar(int idProduto){
        for (int i = 0; i < listaProduto.size(); i++) {
            if (listaProduto.get(i).getIdProduto()==idProduto) {
                return listaProduto.get(i);
            }
        }
        return null;
    }
    
    public void atualizar(Produto original, Produto modificado){
        int qualOIndiceDoProduto = listaProduto.indexOf(original);
        listaProduto.set(qualOIndiceDoProduto, modificado);
    }
    
    
    public List<Produto> listar(){
        return listaProduto;
    }
    
    public void excluir(Produto produto){
        listaProduto.remove(produto);
    }
    
}
