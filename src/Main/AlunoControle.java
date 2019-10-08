package Main;

import java.util.ArrayList;
import java.util.List;


public class AlunoControle {

    private List<Aluno> lista = new ArrayList<>();

    public AlunoControle() {

    }

    public void limparLista() {
        lista.clear();
    }

    public void adicionar(Aluno aluno) {
        lista.add(aluno);
    }

    public List<Aluno> listar() {
        return lista;
    }

    public Aluno buscar(String RA) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getRA().equals(RA)) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(Aluno aluno, Aluno alunoAntigo) {
        lista.set(lista.indexOf(alunoAntigo), aluno);
    }

    public void excluir(Aluno aluno) {
        lista.remove(aluno);
    }
}
