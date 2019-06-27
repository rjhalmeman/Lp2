
package Main;

/**
 *
 * @author radames
 */
public class Trabalhador {
    private String cpf;
    private String nome;
    private double salario;

    public Trabalhador() {
    }

    public Trabalhador(String cpf, String nome, double salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
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

    @Override
    public String toString() {
        return "Trabalhador{" + "cpf=" + cpf + ", nome=" + nome + ", salario=" + salario + '}';
    }
     
    

    
    
}
