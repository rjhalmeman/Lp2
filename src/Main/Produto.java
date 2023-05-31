package Main;

/**
 *
 * @author radames
 */
public class Produto {
   private int idProduto;
   private String nomeProduto;
   private double precoProduto;
   private String unidadeDeMedida;

    public Produto() {
    }

    public Produto(int idProduto, String nomeProduto, double precoProduto, String unidadeDeMedida) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
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

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    @Override
    public String toString() {
        return idProduto + ";" + nomeProduto + ";" + precoProduto + ";" + unidadeDeMedida ;
    }
   
   
}
