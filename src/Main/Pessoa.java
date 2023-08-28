package Main;

import java.util.Date;

/**
 *
 * @author Radames
 */
public class Pessoa {

    private String cpf;
    private String nome;
    private Date dataNascimento;
    private double peso;
    private double altura;

    public Pessoa() {
    }

    public Pessoa(String cpf, String nome, Date dataNascimento, double peso, double altura) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
    }
//gets

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }
//sets

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return cpf + ";" + nome + ";" + dataNascimento + ";" + peso + ";" + altura;
    }
}//fim da classe
