package Main;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Aluno {

    private String RA;
    private String Nome;
    private Date dataIngresso;
    private float coeficiente;
    private byte periodoNoCurso;

    public Aluno() {
    }

    public Aluno(String RA, String Nome, Date dataIngresso, float coeficiente, byte periodoNoCurso) {
        this.RA = RA;
        this.Nome = Nome;
        this.dataIngresso = dataIngresso;
        this.coeficiente = coeficiente;
        this.periodoNoCurso = periodoNoCurso;
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public float getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(float coeficiente) {
        this.coeficiente = coeficiente;
    }

    public byte getPeriodoNoCurso() {
        return periodoNoCurso;
    }

    public void setPeriodoNoCurso(byte periodoNoCurso) {
        this.periodoNoCurso = periodoNoCurso;
    }

    @Override
    public String toString() {
        return RA + ";" + Nome + ";" + new SimpleDateFormat("dd/MM/yyyy").format(dataIngresso) + ";" + coeficiente + ";" + periodoNoCurso;
    }
}
