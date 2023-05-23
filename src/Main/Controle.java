package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {

    private List<Pessoa> listaPessoa = new ArrayList<>();

    public void adicionar(Pessoa pessoa) {
        listaPessoa.add(pessoa);
    }

    public Pessoa buscar(String cpf) {
        Pessoa pessoa;
       
        for (int i = 0; i < listaPessoa.size(); i++) {
            pessoa = listaPessoa.get(i);
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return null;
    }

    public void alterar(Pessoa pessoa, Pessoa pessoaAlterada) {
        int qualPessoa = listaPessoa.indexOf(pessoa);
        listaPessoa.set(qualPessoa, pessoaAlterada);
    }
    
    public void excluir(Pessoa pessoa){
        listaPessoa.remove(pessoa);
    }

    public List<Pessoa> listar() {
        return listaPessoa;
    }

}
