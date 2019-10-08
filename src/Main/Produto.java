package Main;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Produto {

    private long id;
    private String nome;
    private Date dataFabricacao;
    private Date dataValidade;
    private boolean ativo;

    public Produto() {
    }

    public Produto(long id, String nome, Date dataFabricacao, Date dataValidade, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.ativo = ativo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return id + ";" + nome + ";" + new SimpleDateFormat("dd/MM/yyyy").format(dataFabricacao) + ";" + new SimpleDateFormat("dd/MM/yyyy").format(dataValidade) + ";" + (ativo?"Sim":"Não");
    }
}
