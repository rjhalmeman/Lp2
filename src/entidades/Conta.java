package entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
public class Conta {

    private Integer idConta;
    private String nomeConta;
    private Double saldo;
    private Double limite;

    public Conta() {
    }

    public Conta(Integer idConta, String nomeConta, Double saldo, Double limite) {
        this.idConta = idConta;
        this.nomeConta = nomeConta;
        this.saldo = saldo;
        this.limite = limite;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return idConta + ";" + nomeConta + ";" + saldo + ";" + limite;
    }

}
