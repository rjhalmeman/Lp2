package entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
public class TipoConta {

    private Integer idTipoConta;
    private String nomeTipoConta;

    public TipoConta() {
    }

    public TipoConta(Integer idTipoConta, String nomeTipoConta) {
        this.idTipoConta = idTipoConta;
        this.nomeTipoConta = nomeTipoConta;
    }

    public Integer getIdTipoConta() {
        return idTipoConta;
    }

    public void setIdTipoConta(Integer idTipoConta) {
        this.idTipoConta = idTipoConta;
    }

    public String getNomeTipoConta() {
        return nomeTipoConta;
    }

    public void setNomeTipoConta(String nomeTipoConta) {
        this.nomeTipoConta = nomeTipoConta;
    }

    @Override
    public String toString() {
        return idTipoConta + ";" + nomeTipoConta;
    }
    
    

}
