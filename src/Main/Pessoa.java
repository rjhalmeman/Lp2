package Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {

    private String cpf;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;

    public Pessoa() {
    }

    public Pessoa(String cpf, String nome, String sobrenome, Date dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return cpf + "|" + nome + "|" + sobrenome + "|" + sdf.format(dataNascimento);
    }

}
