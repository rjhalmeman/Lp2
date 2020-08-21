
package Entidade;

/**
 *
 * @author radames
 */
public class Produto {
    private int idProduto;//pk
    private String nomeProduto;
    private int quantidadeProduto;
    private String id_UM;//fk

    public Produto() {
    }

    public Produto(int idProduto, String nomeProduto, int quantidadeProduto, String id_UM) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.id_UM = id_UM;
    }

    public String getId_UM() {
        return id_UM;
    }

    public void setId_UM(String id_UM) {
        this.id_UM = id_UM;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    @Override
    public String toString() {
        return  idProduto + ";" + nomeProduto + ";" + quantidadeProduto + ";" + id_UM;
    }
    
    
    
}
