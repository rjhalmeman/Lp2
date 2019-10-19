package Main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.util.List;
import tools.Tools;

public class GUITexto {

    Tools tools = new Tools();

    Saida saida = new Saida();
    Controle controle = new Controle();
    
  

    public void telaList() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        List<Trabalhador> lt = controle.listar();

        System.out.println("CPF;NOME;SALARIO;APOSENTADO");
        for (Trabalhador trabalhador : lt) {
            System.out.println(trabalhador);
        }

        entrada.pausaEnter();
    }

    public void telaRetrieve() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("RETRIEVE\n");
        String cpf = entrada.lerString("Digite o CPF do trabalhador");
        Trabalhador trabalhador = controle.buscar(cpf);
        if (trabalhador != null) {
            saida.rotuloString("CPF", trabalhador.getCpf());
            saida.rotuloString("Nome:", trabalhador.getNome());
            saida.imprimirNumeroDouble("Salário", trabalhador.getSalario());
            saida.rotuloString("Aposentado", trabalhador.isAposentado() ? "SIM" : "NÃO");
        } else {
            saida.rotuloString("Não encontrou esse cpf", cpf);
        }
        entrada.pausaEnter();
    }

    public void telaCreate() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("INSERT\n");
        entrada.teclado.reset();
        String cpf = entrada.lerString("Digite o CPF do trabalhador");
        Trabalhador trab = controle.buscar(cpf);
        if (trab == null) { //não achou, então pode adicionar
            Trabalhador trabalhador = new Trabalhador();
            trabalhador.setCpf(cpf);
            trabalhador.setNome(entrada.lerString("Digite o nome"));
            trabalhador.setSalario(entrada.lerNumeroDouble("Digite o salário"));
            trabalhador.setAposentado(entrada.lerConfirmacao("Aposentado"));
            controle.adicionar(trabalhador);
        } else {
            System.out.println(trab);
            System.out.println("Trabalhador já cadastrado");
            entrada.pausaEnter();
        }
    }

    public void telaUpdate() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("UPDATE\n");
        entrada.teclado.reset();
        String cpf = entrada.lerString("Digite o CPF do trabalhador");
        Trabalhador trabalhador = controle.buscar(cpf);
        if (trabalhador != null) { //achou, então pode alterar
            Trabalhador trabalhadorAntigo = trabalhador; //guarda dados para pesquisa no controle
            trabalhador.setCpf(cpf);
            saida.rotuloString("Nome atual: ", trabalhador.getNome());
            trabalhador.setNome(entrada.lerString("Digite o novo nome"));
            saida.imprimirNumeroDouble("Salário atual", trabalhador.getSalario());
            trabalhador.setSalario(entrada.lerNumeroDouble("Digite o novo salário"));
            saida.rotuloString("Aposentado", trabalhador.isAposentado() ? "SIM" : "NÃO");
            trabalhador.setAposentado(entrada.lerConfirmacao("Aposentado"));
            controle.alterar(trabalhador, trabalhadorAntigo);
        } else {
            System.out.println("Trabalhador não cadastrado, impossível alterar");
            entrada.pausaEnter();
        }
    }

    public void telaDelete() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("DELETE\n");
        entrada.teclado.reset();
        String cpf = entrada.lerString("Digite o CPF do trabalhador");
        Trabalhador trabalhador = controle.buscar(cpf);
        if (trabalhador != null) { //achou, então pode excluir
            trabalhador.setCpf(cpf);
            saida.rotuloString("CPF", cpf);
            saida.rotuloString("Nome: ", trabalhador.getNome());
            saida.imprimirNumeroDouble("Salário", trabalhador.getSalario());
            saida.rotuloString("Aposentado", trabalhador.isAposentado() ? "SIM" : "NÃO");
            if (entrada.lerConfirmacao("Excluir esse trabalhador?")) {
                controle.excluir(trabalhador);
            }
        } else {
            System.out.println("Trabalhador não cadastrado, exclusão impossível");
            entrada.pausaEnter();
        }
    }

    public GUITexto() {
        Entrada entrada = new Entrada();
        String caminho = "Trabalhador.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);
        
        
        int opcao = 0;
        while (opcao != 9) {
            tools.clearScreen();
            System.out.println("CRUD - Trabalhador\n");
            System.out.println("\nMenu Principal\n");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Listar");
            System.out.println("9 - Sair");

            opcao = entrada.lerNumeroInteiro("Qual a opção");

            switch (opcao) {
                case 1:
                    telaCreate();
                    break;
                case 2:
                    telaRetrieve();
                    break;
                case 3:
                    telaUpdate();
                    break;
                case 4:
                    telaDelete();
                    break;
                case 5:
                    telaList();
                    break;
                case 9:
                    //antes de sair, transferir para o HD
                    controle.gravarLista(caminho);
                    System.out.println("\n\nBye");

                    break;

                default:
                    System.out.println("Opção errada, tente novamente");
            }

        }

    }

}
