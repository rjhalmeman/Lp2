package Entidade;

/**
 *
 * @author radames
 */
public class UnidadeDeMedida {

    private String id_UM;//pk
    private String nome_UM;
    

    public UnidadeDeMedida() {
    }

    public UnidadeDeMedida(String id_UM, String nome_UM) {
        this.id_UM = id_UM;
        this.nome_UM = nome_UM;
    }

    
    @Override
    public String toString() {
        return id_UM + ";" + nome_UM ;
    }

    public String getId_UM() {
        return id_UM;
    }

    public void setId_UM(String id_UM) {
        this.id_UM = id_UM;
    }

    public String getNome_UM() {
        return nome_UM;
    }

    public void setNome_UM(String nome_UM) {
        this.nome_UM = nome_UM;
    }

}
