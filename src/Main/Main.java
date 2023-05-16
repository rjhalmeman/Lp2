package Main;

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
        Controle controle = new Controle();
        Produto produto = new Produto();

        produto.setIdProduto(1);
        produto.setNomeProduto("Arroz");
        produto.setPrecoProduto(10.00);
        produto.setUnidadeDeMedida("Pacote 5 kg");
        controle.inserir(produto);

        produto = new Produto(2, "Feijão", 8.0, "Pc 1kg");
        controle.inserir(produto);
        produto = new Produto(3, "Leite", 5.0, "l");
        controle.inserir(produto);
        produto = new Produto(9, "Maça", 8.0, "kg");
        controle.inserir(produto);

        List<Produto> lp = controle.listar();
        for (Produto p : lp) {
            System.out.println(p);
        }
        System.out.println("--------------------");
        Produto p = controle.buscar(33);
        if (p==null) {
            System.out.println("Não está na lista");
        } else {
            System.out.println("Encontrou o produto => "+p);
        }
        System.out.println("vai modificar");
        
        Produto original = controle.buscar(1);
        Produto modificado = new Produto(1,"Arroz Inteiro",11.00,"Pc");
        controle.atualizar(original, modificado);
        
        p = controle.buscar(3);
        controle.excluir(p);
        
        lp = controle.listar();
        for (Produto px : lp) {
            System.out.println(px);
        }
        

    }

}
