package Main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.util.List;
import tools.Tools;

public class GUITexto {

    Tools tools = new Tools();

    Saida saida = new Saida();
    Controle controle = new Controle();

    public void telaListar() {
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

    public void telaBuscar() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        String cpf = entrada.lerString("Digite o CPF do trabalhador");
        Trabalhador trab = controle.buscar(cpf);
        if (trab!=null) {
            saida.rotuloString("CPF",trab.getCpf());
            saida.rotuloString("Nome:",trab.getNome());
            saida.imprimirNumeroDouble("Salário", trab.getSalario());
        }
        else {
            saida.rotuloString("Não encontrou esse cpf", cpf);
        }
        entrada.pausaEnter();
    }

    public void telaAdicionar() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("");
        entrada.teclado.reset();
        String cpf = entrada.lerString("Digite o CPF do trabalhador");
        Trabalhador trab = controle.buscar(cpf);
        if (trab == null) { //não achou, então pode adicionar
            Trabalhador trabalhador = new Trabalhador();
            trabalhador.setCpf(cpf);
            trabalhador.setNome(entrada.lerString("Digite o nome"));
            trabalhador.setSalario(entrada.lerNumeroDouble("Digite o salário"));
            trabalhador.setAposentado(false);
            controle.adicionar(trabalhador);
        } else {
            System.out.println(trab);
            System.out.println("Trabalhador já cadastrado");
            entrada.pausaEnter();
        }
    }

    public GUITexto() {
        Entrada entrada = new Entrada();
        int opcao = 0;
        while (opcao != 9) {
            tools.clearScreen();
            System.out.println("1 - Adicionar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar");
            System.out.println("9 - Sair");

            opcao = entrada.lerNumeroInteiro("Qual a opção");

            switch (opcao) {
                case 1:
                    telaAdicionar();
                    break;
                case 2:
                    telaListar();

                    break;
                case 3:
                    telaBuscar();
                    break;

                case 9:
                    System.out.println("Bye");

                    break;

                default:
                    System.out.println("Opção errada, tente novamente");
            }

        }

    }

}
